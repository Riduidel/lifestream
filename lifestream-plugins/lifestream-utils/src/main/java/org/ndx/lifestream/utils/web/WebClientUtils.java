package org.ndx.lifestream.utils.web;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebClientUtils {
	public static final String BROWSER_DOWNLOAD_DIR = "browser.download.dir";
	private static WebDriver webClient;
	private static File downloadPath;

	public static File getDownloadFolder() {
		if (downloadPath == null) {
			synchronized (WebClientUtils.class) {
				if (downloadPath == null) {
					downloadPath = new File(".download");
				}
			}
		}
		return downloadPath;
	}

	public static WebDriver getWebClient() {
		if (webClient == null) {
			synchronized (WebClientUtils.class) {
				if (webClient == null) {
					webClient = createWebClient();
				}
			}
		}
		return createWebClient();
	}

	/**
	 * @see https://stackoverflow.com/a/52911310/15619
	 * @see https://stackoverflow.com/a/23514381/15619
	 */
	private static WebDriver createWebClient() {
		String browser = System.getProperty("lifestream.selenium.browser", "chrome");
		switch (browser) {
		case "firefox":
			return createFirefoxWebClient();
		default:
			return createChromiumwebClient();
		}
	}

	private static WebDriver createFirefoxWebClient() {
		FirefoxOptions options = new FirefoxOptions();
		// Never reuse firefox profile
		options.setProfile(new FirefoxProfile());
		options.setLegacy(false);
		options.setLogLevel(FirefoxDriverLogLevel.TRACE);
		options.setHeadless(true);
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.manager.showWhenStarting", false);
		options.addPreference(BROWSER_DOWNLOAD_DIR, getDownloadFolder().getAbsolutePath());
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", Arrays
				.asList("text/csv", "attachment/csv", "application/xml", "application/zip", "text/xml", "text/html")
				.stream().collect(Collectors.joining(";")));

		options.addPreference("browser.download.alertOnEXEOpen", false);
		options.addPreference("browser.download.manager.showWhenStarting", false);
		options.addPreference("browser.download.manager.focusWhenStarting", false);
		options.addPreference("browser.helperApps.alwaysAsk.force", false);
		options.addPreference("browser.download.manager.alertOnEXEOpen", false);
		options.addPreference("browser.download.manager.closeWhenDone", false);
		options.addPreference("browser.download.manager.showAlertOnComplete", false);
		options.addPreference("browser.download.manager.useWindow", false);
		options.addPreference("browser.download.manager.showWhenStarting", false);
		options.addPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		options.addPreference("pdfjs.disabled", true);
		FirefoxDriver driver = new FirefoxDriver(options);
		driver.setLogLevel(Level.FINEST);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		return driver;
	}

	private static WebDriver createChromiumwebClient() {
		String urlText = System.getProperty("lifestream.selenium.remote.url");
		Map<String, Object> preferences = new TreeMap<>();
		// See https://stackoverflow.com/a/34530160/15619 for download folder
		preferences.put("credentials_enable_service", false);
		preferences.put("profile.default_content_settings.popups", 0);
		preferences.put("download.prompt_for_download", 0);
		preferences.put("download.default_directory", getDownloadFolder().getAbsolutePath());
		if (urlText == null) {
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			// Now fill the correct objects
			options.setExperimentalOption("prefs", preferences);
			ChromeDriver driver = new ChromeDriver(options);
			driver.setLogLevel(Level.FINEST);
			driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
			return driver;
		} else {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", preferences);
			chromeOptions.setCapability("selenoid:options", Map.<String, Object>of(
				    "enableVNC", true,
				    "enableVideo", true
				));
			try {
				URL url = URI.create(urlText).toURL();
				RemoteWebDriver driver = new RemoteWebDriver(url, chromeOptions);
				return driver;
			} catch (MalformedURLException e) {
				throw new SeleniumException(String.format("provided url %s can't be parsed", urlText));
			}
		}
	}

	/**
	 * Get all links in current page
	 */
	public static List<WebElement> getLinks(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@href]"));
	}

	/**
	 * This method exists only because chrome gives back hand as soon as the link is
	 * clicked, instead of waiting for the file to be downloaded
	 */
	public static void waitForDownloadOver() {
		File[] temp = null;
		do {
			synchronized (WebClientUtils.class) {
				try {
					WebClientUtils.class.wait(1_000);
				} catch (InterruptedException e) {
				}
			}
			temp = getDownloadFolder().listFiles((dir, name) -> name.endsWith(".crdownload") || name.endsWith(".tmp"));
		} while (temp.length > 0);
	}
}

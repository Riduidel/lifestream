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
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class WebClientUtils {
	private static final String BROWSER_SELENIUM_REMOTE_URL = "lifestream.selenium.remote.url";
	private static final Logger logger = Logger.getLogger(WebClientUtils.class.getName());
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
		String urlText = System.getProperty(BROWSER_SELENIUM_REMOTE_URL);
		if (urlText == null) {
			throw new UnsupportedOperationException("Running selnium-driven browser locally is no more possible. Set the "+BROWSER_SELENIUM_REMOTE_URL+" system property");
		} else {
			FirefoxOptions options = new FirefoxOptions();
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
			options.setCapability("selenoid:options", Map.<String, Object>of(
				    "enableVNC", true,
				    "enableVideo", true
				));
			try {
				URL url = URI.create(urlText).toURL();
				RemoteWebDriver driver = new RemoteWebDriver(url, options);
				logger.info(String.format("Using remote firefox WebDriver. Connected to %s", urlText));
				return driver;
			} catch (MalformedURLException e) {
				throw new SeleniumException(String.format("provided url %s can't be parsed", urlText));
			}
		}
	}

	private static WebDriver createChromiumwebClient() {
		String urlText = System.getProperty(BROWSER_SELENIUM_REMOTE_URL);
		Map<String, Object> preferences = new TreeMap<>();
		// See https://stackoverflow.com/a/34530160/15619 for download folder
		preferences.put("credentials_enable_service", false);
		preferences.put("profile.default_content_settings.popups", 0);
		preferences.put("download.prompt_for_download", 0);
		preferences.put("download.default_directory", getDownloadFolder().getAbsolutePath());
		if (urlText == null) {
			throw new UnsupportedOperationException("Running selnium-driven browser locally is no more possible. Set the "+BROWSER_SELENIUM_REMOTE_URL+" system property");
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
				logger.info(String.format("Using remote chrome WebDriver. Connected to %s", urlText));
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
	
	public static void download(WebDriver client, File destination) {
		if (client instanceof RemoteWebDriver) {
			RemoteWebDriver remoteClient = (RemoteWebDriver) client;
			String urlText = System.getProperty(BROWSER_SELENIUM_REMOTE_URL);
			SessionId id = remoteClient.getSessionId();
			try {
				URL url = URI.create(urlText).toURL();
				String path= String.format("http://%s:%s/download/%s/%s", url.getHost(), url.getPort(), id.toString(), destination.getName());
				FileObject source = VFS.getManager().resolveFile(path);
				FileObject dest = VFS.getManager().resolveFile(destination.toURI());
				dest.copyFrom(source, new AllFileSelector());
			} catch (MalformedURLException | FileSystemException e) {
				throw new SeleniumException(String.format("provided url %s can't be parsed", urlText));
			}
		}

	}
}

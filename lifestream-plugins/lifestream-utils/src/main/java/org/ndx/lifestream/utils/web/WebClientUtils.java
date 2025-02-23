package org.ndx.lifestream.utils.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class WebClientUtils {
	private static final String WAIT_UNTIL_DOWNLOAD_IS_OVER_CHROME_JS = "wait_until_download_is_over.chrome.js";
	private static final Logger logger = Logger.getLogger(WebClientUtils.class.getName());
	public static final String BROWSER_DOWNLOAD_DIR = "browser.download.dir";
	private static File downloadPath;
	private static String WAITING_SCRIPT;
	static {
		URL scriptPath = WebClientUtils.class.getResource(WAIT_UNTIL_DOWNLOAD_IS_OVER_CHROME_JS);
		logger.info(String.format("Loading waiting script from %s", scriptPath));
		try {
			WAITING_SCRIPT = IOUtils.toString(scriptPath, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Unable to read wait for download script", e);
		}
	}

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

	public static Browser createWebClient(Playwright playwright) {
		String browser = System.getProperty("lifestream.selenium.browser", "chrome");
		switch (browser) {
		default:
			return createFirefoxWebClient(playwright);
		}
	}

	private static Browser createFirefoxWebClient(Playwright playwright) {
		Map<String, Object> options = new TreeMap<String, Object>();
			options.put("browser.download.folderList", 2);
			options.put("browser.download.manager.showWhenStarting", false);
			options.put(BROWSER_DOWNLOAD_DIR, getDownloadFolder().getAbsolutePath());
			options.put("browser.helperApps.neverAsk.saveToDisk", Arrays
					.asList("text/csv", "attachment/csv", "application/xml", "application/zip", "text/xml", "text/html")
					.stream().collect(Collectors.joining(";")));

			options.put("browser.download.alertOnEXEOpen", false);
			options.put("browser.download.manager.showWhenStarting", false);
			options.put("browser.download.manager.focusWhenStarting", false);
			options.put("browser.helperApps.alwaysAsk.force", false);
			options.put("browser.download.manager.alertOnEXEOpen", false);
			options.put("browser.download.manager.closeWhenDone", false);
			options.put("browser.download.manager.showAlertOnComplete", false);
			options.put("browser.download.manager.useWindow", false);
			options.put("browser.download.manager.showWhenStarting", false);
			options.put("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			options.put("pdfjs.disabled", true);
			logger.info(String.format("Using remote firefox WebDriver."));
			
			LaunchOptions firefoxLaunchOptions = new BrowserType.LaunchOptions();
			firefoxLaunchOptions.setFirefoxUserPrefs(options);
			return playwright.firefox()
					.launch(firefoxLaunchOptions)
					;
			
	}

	/**
	 * Get all links in current page
	 */
	public static Locator getLinks(Page driver) {
		return driver.locator("a[href]");
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

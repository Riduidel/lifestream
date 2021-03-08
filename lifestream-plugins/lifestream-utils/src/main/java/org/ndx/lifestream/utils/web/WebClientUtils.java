package org.ndx.lifestream.utils.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebClientUtils {
	public static final String BROWSER_DOWNLOAD_DIR = "browser.download.dir";
	private static WebDriver webClient;
	private static File downloadPath;
	
	public static File getDownloadFolder() {
		if(downloadPath==null) {
			synchronized(WebClientUtils.class) {
				if(downloadPath==null) {
					downloadPath = new File(".download");
				}
			}
		}
		return downloadPath;
	}

	public static WebDriver getWebClient() {
		if(webClient==null) {
			synchronized(WebClientUtils.class) {
				if(webClient==null) {
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
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);
	    options.addPreference("browser.download.folderList", 2);
	    options.addPreference("browser.download.manager.showWhenStarting", false);
	    options.addPreference(BROWSER_DOWNLOAD_DIR, getDownloadFolder().getAbsolutePath());
	    options.addPreference("browser.helperApps.neverAsk.saveToDisk", 
	    		Arrays.asList(
	    				"text/csv",
	    				"attachment/csv",
	    				"application/xml",
	    				"application/zip",
	    				"text/xml",
	    				"text/html"
	    				).stream().collect(Collectors.joining(";")));
	    

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
	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		return driver;
	}

	/**
	 * Get all links in current page
	 */
	public static List<WebElement> getLinks(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@href]"));
	}
}

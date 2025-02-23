package org.ndx.lifestream.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest {

	protected static Browser browser;
	private static Playwright playwright;

	@BeforeClass
	public static void startBrowser() {
		browser = WebClientUtils.createWebClient(startPlaywright());
	}

	protected static Playwright startPlaywright() {
		playwright = Playwright.create();
		return playwright;
	}

	@AfterClass
	public static void stopPlaywright() {
		playwright.close();
	}

}

package org.ndx.lifestream.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest {

	protected static Browser browser;
	private static Playwright playwright;

	@BeforeAll
	static void startBrowser() {
		browser = WebClientUtils.createWebClient(startPlaywright());
	}

	protected static Playwright startPlaywright() {
		playwright = Playwright.create();
		return playwright;
	}

	@AfterAll
	static void stopPlaywright() {
		playwright.close();
	}

}

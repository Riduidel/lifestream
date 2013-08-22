package org.ndx.lifestream.utils.web;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

public class WebClientFactory {

	public static WebClient getWebClient() {
		WebClient returned = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions options = returned.getOptions();
		options.setJavaScriptEnabled(false);
		options.setRedirectEnabled(true);
		options.setThrowExceptionOnFailingStatusCode(true);
		return returned;
	}

}

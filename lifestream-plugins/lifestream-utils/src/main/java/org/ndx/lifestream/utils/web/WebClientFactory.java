package org.ndx.lifestream.utils.web;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

public class WebClientFactory {
	private static WebClient webClient;

	public static WebClient getWebClient() {
		if(webClient==null) {
			synchronized(WebClientFactory.class) {
				if(webClient==null) {
					webClient = createWebClient();
				}
			}
		}
		return createWebClient();
	}

	private static WebClient createWebClient() {
		WebClient returned = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions options = returned.getOptions();
		options.setJavaScriptEnabled(false);
		options.setRedirectEnabled(true);
		options.setThrowExceptionOnFailingStatusCode(true);
		return returned;
	}

}

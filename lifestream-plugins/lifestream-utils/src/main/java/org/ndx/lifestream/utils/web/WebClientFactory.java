package org.ndx.lifestream.utils.web;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;

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
		WebClient returned = new WebClient(BrowserVersion.FIREFOX_68);
		returned.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
		returned.setCssErrorHandler(new SilentCssErrorHandler());
		WebClientOptions options = returned.getOptions();
		options.setCssEnabled(false);
		options.setDownloadImages(false);
		options.setJavaScriptEnabled(true);
		options.setRedirectEnabled(true);
		options.setThrowExceptionOnFailingStatusCode(true);
		options.setWebSocketEnabled(true);
		return returned;
	}

}

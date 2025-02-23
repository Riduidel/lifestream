package org.ndx.lifestream.configuration;

import com.microsoft.playwright.Browser.NewContextOptions;

public interface Configuration {
	/**
	 * Interface containing mostly fields for the application defined to access twitter from any kind of lifestream plugin
	 * @author ndx
	 *
	 */
	public static interface Twitter {

		String CONSUMER_KEY = "2zRgujcEZS9aEnsQlcXTOtA80";
		String CONSUMER_SECRET = "yPMVP14clIwNUOqw7wARiegyDvoaws9vImhoChAfMOPnSGHzpj";
		
	}

	/**
	 * Gives configuration for browser (specifically locale and timezone)
	 */
	public NewContextOptions getPlaywrightContext();
}

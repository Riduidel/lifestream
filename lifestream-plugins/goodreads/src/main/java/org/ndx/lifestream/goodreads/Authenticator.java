package org.ndx.lifestream.goodreads;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Authenticator {
	static final Logger logger = Logger.getLogger(Authenticator.class.getName());
	private static Optional<String> userId = Optional.empty();

	public static void authenticateInGoodreads(Page browser, GoodreadsConfiguration configuration)
			throws IOException {
		authenticateInGoodreads(browser, configuration.getMail(), configuration.getPassword());
	}
	public static String authenticateInGoodreads(Page browser, String username, String password)
			throws IOException {
		// TODO handle timeout
		if(userId.isEmpty()) {
			browser.navigate(GoodreadsConfiguration.SIGN_IN);
			// There is now a weird button click before to access the sign in by mail page
			browser.locator(".authPortalSignInButton").click();
			logger.log(Level.INFO, "logging in goodreads as "+username);
			browser.locator("#ap_email").fill(username);
			browser.locator("#ap_password").fill(password);
			browser.locator("#ap_password").click();
			browser.locator("#ap_password").press("Enter");
			browser.locator("div.dropdown--profileMenu").waitFor(new WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			String authenticationFailedMessage = "unable to sign in Goodreads using mail "+username+" and password "+password+". can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?";
			if(browser.url().contains(GoodreadsConfiguration.SIGN_IN)) {
				throw new AuthenticationFailedException(authenticationFailedMessage);
			} else {
				Locator toProfileLink = browser.locator("div.dropdown--profileMenu a").first();
				String href = toProfileLink.getAttribute("href");
				if (href.contains("/user/show")) {
					userId = Optional.of(href.substring(href.lastIndexOf('/') + 1, href.indexOf('-')));
					return userId.orElseThrow();
				}
			}
		}
		return userId.orElseThrow();
	}

}

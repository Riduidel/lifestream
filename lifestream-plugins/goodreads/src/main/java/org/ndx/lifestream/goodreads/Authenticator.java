package org.ndx.lifestream.goodreads;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Authenticator {
	static final Logger logger = Logger.getLogger(Authenticator.class.getName());
	private static boolean connected;

	public static void authenticateInGoodreads(WebClient client, GoodreadsConfiguration configuration)
			throws IOException, MalformedURLException {
		authenticateInGoodreads(client, configuration.getMail(), configuration.getPassword());
	}
	public static void authenticateInGoodreads(WebClient client, String username, String password)
			throws IOException, MalformedURLException {
		// TODO handle timeout
		if(!connected) {
			HtmlPage signIn = client.getPage(Goodreads.GOODREADS_BASE+"user/sign_in");
			HtmlForm signInForm = signIn.getFormByName("sign_in");
			logger.log(Level.INFO, "logging in goodreads as "+username);
			signInForm.getInputByName("user[email]").setValueAttribute(username);
			signInForm.getInputByName("user[password]").setValueAttribute(password);
			HtmlPage signedIn = signInForm.getInputByName("next").click();
			String authenticationFailedMessage = "unable to sign in Goodreads using mail "+username+" and password "+password+". can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?";
			if(200==signedIn.getWebResponse().getStatusCode()) {
				if(signedIn.getUrl().equals(signIn.getUrl())) {
					throw new AuthenticationFailedException(authenticationFailedMessage);
				}
			} else {
				throw new AuthenticationFailedException(authenticationFailedMessage);
			}
			connected = true;
		}
	}

}

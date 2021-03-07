package org.ndx.lifestream.goodreads;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.utils.web.WebClientUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authenticator {
	static final Logger logger = Logger.getLogger(Authenticator.class.getName());
	private static Optional<String> userId = Optional.empty();

	public static void authenticateInGoodreads(WebDriver browser, GoodreadsConfiguration configuration)
			throws IOException, MalformedURLException {
		authenticateInGoodreads(browser, configuration.getMail(), configuration.getPassword());
	}
	public static String authenticateInGoodreads(WebDriver browser, String username, String password)
			throws IOException, MalformedURLException {
		// TODO handle timeout
		if(userId.isEmpty()) {
			browser.get(GoodreadsConfiguration.SIGN_IN);
			logger.log(Level.INFO, "logging in goodreads as "+username);
			browser.findElement(By.id("user_email")).sendKeys(username);
			WebElement passwordField = browser.findElement(By.id("user_password"));
			passwordField.sendKeys(password);
			passwordField.click();
			passwordField.submit();
			WebDriverWait wait = new WebDriverWait(browser, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown__trigger--profileMenu")));
			String authenticationFailedMessage = "unable to sign in Goodreads using mail "+username+" and password "+password+". can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?";
			if(browser.getCurrentUrl().contains(GoodreadsConfiguration.SIGN_IN)) {
				throw new AuthenticationFailedException(authenticationFailedMessage);
			} else {
				// Let's try to get user id
				for(WebElement anchor : WebClientUtils.getLinks(browser)) {
					String href = anchor.getAttribute("href");
					if(href.contains("/user/show")) {
						userId = Optional.of(href.substring(href.lastIndexOf('/')+1, href.indexOf('-')));
						return userId.get();
					}
				}
			}
		}
		return userId.get();
	}

}

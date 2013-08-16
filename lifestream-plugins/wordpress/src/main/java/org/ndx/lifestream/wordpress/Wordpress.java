package org.ndx.lifestream.wordpress;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.goodreads.AuthenticationFailedException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.model.InputLoader;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Wordpress implements InputLoader<Post> {
	private static Logger logger = Logger.getLogger(Wordpress.class.getName());

	public String login;
	public String password;
	public String site;

	@Override
	public Collection<Post> load(Log log) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void output(Mode mode, Collection<Post> inputs, FileObject outputRoot) {
		// TODO Auto-generated method stub
		
	}
	public String loadXML() {
		WebClient client = new WebClient(com.gargoylesoftware.htmlunit.BrowserVersion.FIREFOX_3);
		client.setUseInsecureSSL(true);
		client.setJavaScriptEnabled(false);
		String siteLogin = site+"wp-login.php";
		HtmlPage signIn = client.getPage(siteLogin);
		HtmlForm signInForm = signIn.getFormByName("loginform");
		logger.log(Level.INFO, "logging in wordpress as "+login);
		signInForm.getInputByName("user_login").setValueAttribute(login);
		signInForm.getInputByName("user_password").setValueAttribute(password);
		HtmlPage signedIn = signInForm.getElementById("wp-submit").click();
		String authenticationFailedMessage = "unable to sign in Wordpress using mail "+login+" and password "+password+". can you check it by opening a browser at "+siteLogin+" ?";
		if(200==signedIn.getWebResponse().getStatusCode()) {
			if(signedIn.getUrl().equals(signIn.getUrl()))
				throw new AuthenticationFailedException(authenticationFailedMessage);
			logger.log(Level.INFO, "logged in ... downloading csv now ...");
			HtmlPage xmlExportPage = client.getPage(site+"wp-admin/export.php?type=export");
			Page xml = xmlExportPage.getElementById("submit").click();
			// May cause memory error, but later ...
			String xmlContent = xml.getWebResponse().getContentAsString();
			return xmlContent;
		} else {
			throw new AuthenticationFailedException(authenticationFailedMessage);
		}
	}

}

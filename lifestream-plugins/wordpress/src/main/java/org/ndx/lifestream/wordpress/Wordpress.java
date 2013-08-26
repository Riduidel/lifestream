package org.ndx.lifestream.wordpress;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.model.InputLoader;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Wordpress implements InputLoader<Post> {
	private static Logger logger = Logger.getLogger(Wordpress.class.getName());

	public String login;
	public String password;
	public String site;

	@Override
	public Collection<Post> load(WebClient client) {
		loadXML(client);
		throw new UnsupportedOperationException("not yet done");
	}

	@Override
	public void output(Mode mode, Collection<Post> inputs, FileObject outputRoot) {
		// TODO Auto-generated method stub

	}

	public String loadXML(WebClient client) {
		try {
			String siteLogin = site + "wp-login.php";
			HtmlPage signIn = client.getPage(siteLogin);
			HtmlForm signInForm = signIn.getFormByName("loginform");
			logger.log(Level.INFO, "logging in wordpress as " + login);
			((HtmlInput) signInForm.getElementById("user_login"))
					.setValueAttribute(login);
			((HtmlInput) signInForm.getElementById("user_pass"))
					.setValueAttribute(password);
			HtmlPage signedIn = signInForm.getElementById("wp-submit").click();
			String authenticationFailedMessage = "unable to sign in Wordpress using mail "
					+ login
					+ " and password "
					+ password
					+ ". can you check it by opening a browser at "
					+ siteLogin
					+ " ?";
			if (200 == signedIn.getWebResponse().getStatusCode()) {
				if (signedIn.getUrl().equals(signIn.getUrl()))
					throw new AuthenticationFailedException(
							authenticationFailedMessage);
				logger.log(Level.INFO, "logged in ... downloading csv now ...");
				HtmlPage xmlExportPage = client.getPage(site
						+ "wp-admin/export.php?type=export");
				Page xml = ((HtmlInput) xmlExportPage.getElementById("submit"))
						.click();
				// May cause memory error, but later ...
				String xmlContent = xml.getWebResponse().getContentAsString();
				return xmlContent;
			} else {
				throw new AuthenticationFailedException(
						authenticationFailedMessage);
			}
		} catch (Exception e) {
			throw new UnableToDownloadXMLException(e);
		}
	}

	Collection<Post> buildPostCollection(InputStream xmlSource) {
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(xmlSource, true, "utf-8"));
			List<Post> returned = new LinkedList<>();
			for (SyndEntry entry : (Collection<SyndEntry>) feed.getEntries()) {
				returned.add(createPostFromEntry(entry));
			}
			return returned;
		} catch (Exception e) {
			throw new UnableToTransformStreamInPostCollectionException(e);
		}
	}

	private Post createPostFromEntry(SyndEntry entry) {
		Post post = new Post();
		post.writeDate = entry.getPublishedDate()==null ? entry.getUpdatedDate() : entry.getPublishedDate();
		post.title = entry.getTitle();
		post.tags = getEntryTags(entry);
		post.text = getEntryText(entry);
		post.uri = entry.getUri();
		try {
			post.basename = new URL(entry.getLink()).getPath();
			if(post.basename.endsWith("/")) {
				post.basename = post.basename.substring(0, post.basename.lastIndexOf('/'));
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}

	private String getEntryText(SyndEntry entry) {
		StringBuilder sOut = new StringBuilder();
		for(SyndContent content : (Collection<SyndContent>) entry.getContents()) {
			sOut.append(content.getValue());
		}
		return sOut.toString();
	}

	private Collection<String> getEntryTags(SyndEntry entry) {
		Collection<String> tags = new LinkedList<String>();
		// tags are in SyndCategory ... OK
		for(SyndCategory category : (Collection<SyndCategory>) entry.getCategories()) {
			tags.add(category.getName());
		}
		tags.remove("Uncategorized");
		return tags;
	}
}

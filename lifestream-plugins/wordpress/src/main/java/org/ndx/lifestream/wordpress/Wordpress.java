package org.ndx.lifestream.wordpress;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.jdom.Element;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.plugin.exceptions.UnableToReadStreamAsUTF8Exception;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.Constants;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Wordpress implements InputLoader<Post, WordpressConfiguration> {
	private static Logger logger = Logger.getLogger(Wordpress.class.getName());

	@Override
	public Collection<Post> load(WebClient client, WordpressConfiguration configuration) {
		String text = loadXML(client, configuration);
		ByteArrayInputStream stream;
		try {
			stream = new ByteArrayInputStream(text.getBytes(Constants.UTF_8));
			return buildPostCollection(stream);
		} catch (UnsupportedEncodingException e) {
			throw new UnableToReadStreamAsUTF8Exception(e);
		}
	}

	@Override
	public void output(Mode mode, Collection<Post> inputs, FileObject outputRoot, WordpressConfiguration configuration) {
		Collection<Post> filtered = Collections2.filter(inputs, new Predicate<Post>() {

			@Override
			public boolean apply(Post input) {
				return Post.Type.post.equals(input.type);
			}
		});
		OutputWriter writer = mode.getWriter();
		int index = 1;
		int size = filtered.size();
		for (Post post : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing post "+(index++)+"/"+size+" : "+post);
			}
			writer.write(post, outputRoot);
		}
	}

	public String loadXML(final WebClient client, final WordpressConfiguration configuration) {
		try {
			return configuration.refreshCacheWith(new CacheLoader() {

				@Override
				public String load() {
					return downloadXML(client, configuration);
				}
			});
		} catch(Exception e) {
			throw new UnableToDownloadContentException("unable to download content from Wordpress", e);
		}
	}

	public String downloadXML(WebClient client, WordpressConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage();
			HtmlPage signIn = client.getPage(siteLogin);
			HtmlForm signInForm = signIn.getFormByName("loginform");
			logger.log(Level.INFO, "logging in wordpress as " + configuration.getLogin());
			((HtmlInput) signInForm.getElementById("user_login"))
					.setValueAttribute(configuration.getLogin());
			((HtmlInput) signInForm.getElementById("user_pass"))
					.setValueAttribute(configuration.getPassword());
			HtmlPage signedIn = signInForm.getElementById("wp-submit").click();
			String authenticationFailedMessage = "unable to sign in Wordpress using mail "
					+ configuration.getLogin()
					+ " and password "
					+ configuration.getPassword()
					+ ". can you check it by opening a browser at "
					+ siteLogin
					+ " ?";
			if (200 == signedIn.getWebResponse().getStatusCode()) {
				if (signedIn.getUrl().equals(signIn.getUrl()))
					throw new AuthenticationFailedException(
							authenticationFailedMessage);
				logger.log(Level.INFO, "logged in ... downloading xml now ...");
				HtmlPage xmlExportPage = client.getPage(configuration.getSiteExportPage());
				Page xml = ((HtmlInput) xmlExportPage.getElementById("submit"))
						.click();
				// May cause memory error, but later ...
				String xmlContent = xml.getWebResponse().getContentAsString(Constants.UTF_8);
				return xmlContent;
			} else {
				throw new AuthenticationFailedException(
						authenticationFailedMessage);
			}
		} catch (Exception e) {
			throw new UnableToDownloadContentException("Unable to download XML export from Wordpress site", e);
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
		post.type = Post.Type.valueOf(getEntryType(entry));
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

	private String getEntryType(SyndEntry entry) {
		List<Element> elements = (List<Element>) entry.getForeignMarkup();
		for(Element e : elements) {
			if(e.getName().equals("post_type")) {
				return e.getText();
			}
		}
		return null;
	}

	private String getEntryText(SyndEntry entry) {
		StringBuilder sOut = new StringBuilder();
		for(SyndContent content : (Collection<SyndContent>) entry.getContents()) {
			sOut.append(content.getValue());
		}
		return sOut.toString();
	}

	private Collection<String> getEntryTags(SyndEntry entry) {
		return getEntryCategories(entry, "post_tag");
	}

	/**
	 * Get all categories using the given taxonomy
	 * @param entry
	 * @param categoryUri the used category
	 * @return collection of tags
	 */
	private Collection<String> getEntryCategories(SyndEntry entry, String categoryUri) {
		Collection<String> tags = new LinkedList<String>();
		// tags are in SyndCategory ... OK
		for(SyndCategory category : (Collection<SyndCategory>) entry.getCategories()) {
			if(category==null || categoryUri.equals(category.getTaxonomyUri())) {
				tags.add(category.getName());
			}
		}
		return tags;
	}
}

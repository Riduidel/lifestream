package org.ndx.lifestream.wordpress;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.vfs2.FileObject;
import org.jdom.Element;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.plugin.exceptions.UnableToReadStreamAsUTF8Exception;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.wordpress.resolvers.MultiResolver;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Wordpress implements InputLoader<Post, WordpressConfiguration> {
	private static Logger logger = Logger.getLogger(Wordpress.class.getName());
	private GaedoEnvironmentProvider wordpressEnvironment = new GaedoEnvironmentProvider();

	@Override
	public Collection<Post> load(WebClient client, WordpressConfiguration configuration) {
		String text = loadXML(client, configuration);
		ByteArrayInputStream stream;
		try {
			stream = new ByteArrayInputStream(text.getBytes(Constants.UTF_8));
			return CollectionUtils.asList(buildPostCollection(client, stream, configuration).findAll());
		} catch (UnsupportedEncodingException e) {
			throw new UnableToReadStreamAsUTF8Exception(e);
		}
	}

	@Override
	public void output(Mode mode, Collection<Post> inputs, FileObject outputRoot, WordpressConfiguration configuration) {
		Collection<Post> filtered =
				inputs.stream()
					.filter(input -> Post.Type.post.equals(input.getType()))
					.collect(Collectors.toList());
		OutputWriter writer = mode.getWriter();
		LinkResolver linkResolver = configuration.getLinkResolver();
		writer.addListener(linkResolver);
		int index = 1;
		int size = filtered.size();
		for (Post post : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing post "+(index++)+"/"+size+" : "+post);
			}
			writer.write(post, outputRoot);
		}
		linkResolver.save();
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
			HtmlForm signInForm = signIn.getForms().get(0);
			logger.log(Level.INFO, "logging in wordpress as " + configuration.getLogin());
			((HtmlInput) signInForm.getElementsByAttribute("input", "id", "usernameOrEmail").get(0))
					.setValueAttribute(configuration.getLogin());
			((HtmlInput) signInForm.getElementsByAttribute("input", "id", "password").get(0))
					.setValueAttribute(configuration.getPassword());
			HtmlPage signedIn = ((HtmlButton) signInForm.getElementsByAttribute("button", "type", "submit").get(0)).click();
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
				String xmlContent = xml.getWebResponse().getContentAsString(Constants.UTF_8_CHARSET);
				return xmlContent;
			} else {
				throw new AuthenticationFailedException(
						authenticationFailedMessage);
			}
		} catch (Exception e) {
			throw new UnableToDownloadContentException("Unable to download XML export from Wordpress site", e);
		}
	}

	FinderCrudService<Post,PostInformer> buildPostCollection(WebClient client, InputStream xmlSource, WordpressConfiguration configuration) {
		ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadCount());
		FinderCrudService<Post, PostInformer> bookService = wordpressEnvironment.getServiceFor(Post.class, PostInformer.class);
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(xmlSource, true, "utf-8"));
			for (SyndEntry entry : (Collection<SyndEntry>) feed.getEntries()) {
				bookService.create(createPostFromEntry(entry));
			}
			new MultiResolver(client, configuration).resolveIn(executor, bookService);
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);
			return bookService;
		} catch (Exception e) {
			throw new UnableToTransformStreamInPostCollectionException(e);
		}
	}

	private Post createPostFromEntry(SyndEntry entry) {
		Post post = new Post(); // TODO Use http://sujitpal.blogspot.fr/2008/08/parsing-custom-modules-with-rome.html to parse wp:comment
		post.setWriteDate(entry.getPublishedDate()==null ? entry.getUpdatedDate() : entry.getPublishedDate());
		post.setTitle(entry.getTitle());
		post.tags = getEntryTags(entry);
		post.setText(getEntryText(entry));
		post.setUri(entry.getUri());
		post.setSource(entry.getLink());
		try {
			String basename = new URL(entry.getLink()).getPath();
			if(basename.endsWith("/")) {
				basename = basename.substring(0, basename.lastIndexOf('/'));
			}
			post.setBasename(basename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Element> elements = (List<Element>) entry.getForeignMarkup();
		for(Element e : elements) {
			switch(e.getName()) {
			case "post_type": post.setType(Post.Type.valueOf(e.getText())); break;
			case "comment": post.comments.add(new Comment(e)); break;
			case "post_name": break;
			case "post_parent": break;
			case "post_id": break;
			case "postmeta": break;
			case "encoded": post.excerpt = e.getText(); break;
			case "status": break;
			default:
			}
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

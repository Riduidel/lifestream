package org.ndx.lifestream.wordpress;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.jdom.Element;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.plugin.exceptions.UnableToReadStreamAsUTF8Exception;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.TagUtils;
import org.ndx.lifestream.utils.web.WebClientUtils;
import org.ndx.lifestream.wordpress.resolvers.MultiResolver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import de.schlichtherle.truezip.file.TVFS;

public class Wordpress implements InputLoader<Post, WordpressConfiguration> {
	private static final String EXPORT_BUTTON = "export-card__export-button";
	private static Logger logger = Logger.getLogger(Wordpress.class.getName());
	private GaedoEnvironmentProvider wordpressEnvironment = new GaedoEnvironmentProvider();

	@Override
	public Collection<Post> load(WebDriver client, WordpressConfiguration configuration) {
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
			String logMessage = "writing post "+(index++)+"/"+size+" : "+post;
			writer.write(post, outputRoot, logMessage);
		}
		linkResolver.save();
	}

	public String loadXML(final WebDriver client, final WordpressConfiguration configuration) {
		try {
			return configuration.refreshCacheWith(new CacheLoader() {

				@Override
				public String load() {
					if(configuration.getSite().toLowerCase().contains("wordpress.com")) {
						return downloadXMLFromWordpressCloud(client, configuration);
					} else {
						return downloadXMLFromSelfhostedWordpress(client, configuration);
					}
				}
			});
		} catch(Exception e) {
			throw new UnableToDownloadContentException("unable to download content from Wordpress", e);
		}
	}

	public String downloadXMLFromWordpressCloud(WebDriver browser, WordpressConfiguration configuration) {
		try {
			// For login into wordpress.com, it is unfortunatly mandatory to have javascript enabled.
			// A pity, if you ask me
			String siteLogin = configuration.getSiteLoginPage("https://wordpress.com");
			browser.get(siteLogin);
			logger.log(Level.INFO, "logging in wordpress as " + configuration.getLogin());
			browser.findElement(By.id("usernameOrEmail")).sendKeys(configuration.getLogin());
			browser.findElement(By.id("usernameOrEmail")).sendKeys(Keys.ENTER);
			// Don't forget to press on the damn "Continue" button
			new WebDriverWait(browser, 10).until(ExpectedConditions.elementToBeClickable(By.id("password")));
			browser.findElement(By.id("password")).sendKeys(configuration.getPassword());
			browser.findElement(By.id("password")).sendKeys(Keys.ENTER);
			new WebDriverWait(browser, 10).until(ExpectedConditions.elementToBeClickable(By.className("gravatar")));
			logger.log(Level.INFO, "logged in ... downloading xml now ...");
			browser.get(configuration.getCloudExportPage(configuration.getSite()));
			new WebDriverWait(browser, 60)
			.pollingEvery(Duration.ofSeconds(1))
			.until(ExpectedConditions.presenceOfElementLocated(By.className(EXPORT_BUTTON)));
			WebElement exportButton = browser.findElement(By.className(EXPORT_BUTTON));
			exportButton.click();
			new WebDriverWait(browser, 60)
			.pollingEvery(Duration.ofSeconds(1))
			.until(ExpectedConditions.presenceOfElementLocated(By.className("notice__action")));
			// Now there should be a download link
			WebElement downloadFileButton = browser.findElement(By.className("notice__action"));
			String fullUrl = downloadFileButton.getAttribute("href");
			URL url = new URL(fullUrl);
			String filename = url.getFile();
			filename = filename.substring(filename.lastIndexOf('/')+1);
			downloadFileButton.click();
			// Content should be downloaded into download folder, no ?
			File file = new File(WebClientUtils.getDownloadFolder(), filename);
			WebClientUtils.download(browser, file);
			try {
				// So read the zip content, and get the biggest file in that zip
				return readBiggestZipContent(file);
			} finally {
				file.delete();
				// This one is due to the fact that we use a zip file for Wordpress, which unmount
				// requires a shutdown hook
				TVFS.umount();
			}
		} catch (Exception e) {
			throw new UnableToDownloadContentException("Unable to download XML export from Wordpress site", e);
		} finally {
			browser.close();
		}
	}

	private String readBiggestZipContent(File file) throws IOException {
		String path = file.toURI().toString().replace("file:/", "zip:/");
		FileObject fileSystem = VFSHelper.getManager().resolveFile(path);
		FileObject[] exportFiles = fileSystem.getChildren()[0].getChildren();
		FileObject found = exportFiles[0];
		for (int i = 1; i < exportFiles.length; i++) {
			if(exportFiles[i].getContent().getSize()>found.getContent().getSize())
				found = exportFiles[i];
		}
		try(InputStream stream = found.getContent().getInputStream()) {
			return IOUtils.toString(stream);
		}
	}

	public String downloadXMLFromSelfhostedWordpress(WebDriver browser, WordpressConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage(configuration.getSite());
			browser.get(siteLogin);
			logger.log(Level.INFO, "logging in wordpress as " + configuration.getLogin());
			browser.findElement(By.id("usernameOrEmail")).sendKeys(configuration.getLogin());
			// Don't forget to press on the damn "Continue" button
			browser.findElement(By.id("password")).sendKeys(configuration.getPassword());
			browser.findElement(By.id("password")).sendKeys(Keys.ENTER);
			throw new UnsupportedOperationException("not yet reimplemented");
/*			HtmlPage signedIn = ((HtmlButton) signInForm.getElementsByAttribute("button", "type", "submit").get(0)).click();
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
				HtmlPage xmlExportPage = browser.getPage(configuration.getSiteExportPage());
				Page xml = ((HtmlInput) xmlExportPage.getElementById("submit"))
						.click();
				// May cause memory error, but later ...
				String xmlContent = xml.getWebResponse().getContentAsString(Constants.UTF_8_CHARSET);
				return xmlContent;
			} else {
				throw new AuthenticationFailedException(
						authenticationFailedMessage);
			}
*/		} catch (Exception e) {
			throw new UnableToDownloadContentException("Unable to download XML export from Wordpress site", e);
		} finally {
			browser.close();
		}
	}

	FinderCrudService<Post,PostInformer> buildPostCollection(WebDriver client, InputStream xmlSource, WordpressConfiguration configuration) {
		ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadCount());
		FinderCrudService<Post, PostInformer> postService = wordpressEnvironment.getServiceFor(Post.class, PostInformer.class);
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(xmlSource, true, "utf-8"));
			for (SyndEntry entry : (Collection<SyndEntry>) feed.getEntries()) {
				Post post = createPostFromEntry(entry);
				if("publish".equals(post.status)) {
					postService.create(post);
				}
			}
			new MultiResolver(client, configuration).resolveIn(executor, postService);
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);
			return postService;
		} catch (Exception e) {
			throw new UnableToTransformStreamInPostCollectionException(e);
		}
	}

	private Post createPostFromEntry(SyndEntry entry) {
		Post post = new Post(); // TODO Use http://sujitpal.blogspot.fr/2008/08/parsing-custom-modules-with-rome.html to parse wp:comment
		// We read the forein markup first, as it contains the post status which may mark the post as draft
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
			case "status": post.status = e.getText(); break;
			default:
			}
		}
		post.setWriteDate(entry.getPublishedDate()==null ? entry.getUpdatedDate() : entry.getPublishedDate());
		post.setTitle(entry.getTitle());
		post.tags = getEntryTags(entry);
		post.setText(getEntryText(entry));
		post.setUri(entry.getUri());
		post.setSource(entry.getLink());
		// Write date is not set for drafts
		if(post.getWriteDate()!=null) {
			post.tags.add(TagUtils.monthAsTag(post.getWriteDate()));
			post.tags.add(TagUtils.yearAsTag(post.getWriteDate()));
		}
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

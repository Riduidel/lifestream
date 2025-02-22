package org.ndx.lifestream.shaarli;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.TagUtils;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.google.inject.internal.Nullable;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Shaarli implements InputLoader<MicroblogEntry, ShaarliConfiguration> {
	private static final Logger logger = Logger.getLogger(Shaarli.class.getName());

	@Override
	public Collection<MicroblogEntry> load(Browser client, ShaarliConfiguration configuration) {
		String text =  loadXML(client, configuration);
		org.jsoup.nodes.Document document = Jsoup.parse(text);
		return buildEntriesCollection(configuration, document);
	}

	public String loadXML(final Browser client, final ShaarliConfiguration configuration) {
		try {
			return configuration.refreshCacheWith(new CacheLoader() {

				@Override
				public String load() throws Exception {
					return downloadXML(client, configuration);
				}
			});
		} catch(Exception e) {
			throw new UnableToDownloadContentException("unable to download content from Shaarli "+configuration.getSite(), e);
		}
	}

	/**
	 * Read data.
	 * Shaarli outputs data in the form of the legacy bookmarks.xml file that any browser can read.
	 * This file is poorly specified, but well, let's try.
	 * 
	 * As one may see, each bookmark is defined as a "dt" tag, possibly followed by a "dd" tag containing additional text.
	 * The "dt" contains one "a" tag, which contains all infos about the submitted link with non-standard HTML tags.
	 * 
	 * We will use Jsoup to parse that, since the bookmarks.html file may become rather big over time
	 */
	public Collection<MicroblogEntry> buildEntriesCollection(ShaarliConfiguration configuration, org.jsoup.nodes.Document document) {
		Collection<MicroblogEntry> returned = new ArrayList<>();
		
		for(Element entry : document.getElementsByTag("dt")) {
			// Notice we need both the DT and the immediatly following DD
			MicroblogEntry created = null;
			while(created==null) {
				Element nextElement = entry.nextElementSibling();
				created = new MicroblogEntry();
				// Hey, there is some text to add, cool !
				if(nextElement==null) {
					// If we found nothing, that's because we're at the end of the document
				} else if(nextElement.tagName().equalsIgnoreCase("DD")) {
					created = createEntryFrom(configuration, entry, nextElement);
					// Damn, no text ? Then create entry with no text.
				} else if(nextElement.tagName().equalsIgnoreCase("dt")) {
					// there is no text for this entry ... weird
					created = createEntryFrom(configuration, entry, null);
				}
			}
			returned.add(created);
		}
		return returned;
	}

	private MicroblogEntry createEntryFrom(ShaarliConfiguration configuration, Element dt, @Nullable Element dd) {
		MicroblogEntry returned = new MicroblogEntry();
		Element link = dt.selectFirst("a");
		returned.setLink(link.attr("href"));
		returned.setTitle(link.text().trim());
		long parsedLong = Long.parseLong(link.attr("add_date"));
		Date writeDate = new Date();
		writeDate.setTime(parsedLong*1000); // epoch time is given in seconds !
		returned.setWriteDdate(writeDate);
		String tagsText = link.attr("tags");
		if(tagsText==null) {
			if (logger.isLoggable(Level.FINE)) {
				logger.log(Level.FINE, returned+" has no tags ?");
			}
		} else {
			returned.addAllTags(Arrays.asList(tagsText.split(",")));
		}
		returned.addTag(TagUtils.monthAsTag(writeDate));
		returned.addTag(TagUtils.yearAsTag(writeDate));
		returned.setVisible(link.attr("private").equals("0"));
		if(dd!=null) {
			returned.setComment(HtmlToMarkdown.transformHtml(dd.text().trim()));
		}
		try {
			returned.setSource(
					String.format("%s?searchterm=%s&searchtags=%s", configuration.getSite(), 
							URLEncoder.encode(returned.getLink(), "UTF-8"),
							URLEncoder.encode(returned.getTags().stream().collect(Collectors.joining(" ")), "UTF-8")
							)
					);
		} catch(UnsupportedEncodingException e) {
			throw new UnableToProduceUTF8Exception(e);
		}
		return returned;
	}
	
	public String downloadXML(Browser browser, ShaarliConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage();
			Page shaarliPage = browser.newPage();
			shaarliPage.navigate(siteLogin);
			logger.log(Level.INFO, "logging in Shaarli as " + configuration.getLogin());
			Locator loginField = shaarliPage.locator("//input[@name='login']");
			loginField.click();
			loginField.fill(configuration.getLogin());

			Locator passwordField = shaarliPage.locator("//input[@name='password']");
			passwordField.click();
			passwordField.fill(configuration.getPassword());

			shaarliPage.locator("//input[@type='submit']").click();
			logger.log(Level.INFO, "logged in ... downloading html now ...");
			// 404 will throw over all this stuff
			Download download = shaarliPage.waitForDownload(() -> {
				shaarliPage.navigate(configuration.getSiteExportPage());
			});
			return IOUtils.toString(download.createReadStream(), "UTF-8");
		} catch(AuthenticationFailedException e) {
			throw e;
		} catch(Exception e) {
			throw new UnableToDownloadContentException("Unable to download HTML export from Shaarli\n"+configuration.getDownloadFailureMessage(), e);
		}
	}

	@Override
	public void output(Mode mode, Collection<MicroblogEntry> inputs, FileObject outputRoot, ShaarliConfiguration configuration) {
		Collection<MicroblogEntry> filtered =
				inputs.stream()
					.filter(input -> input.isVisible())
					.collect(Collectors.toList());
		OutputWriter writer = mode.getWriter();
		LinkResolver linkResolver = configuration.getLinkResolver();
		writer.addListener(linkResolver);
		int index = 1;
		int size = filtered.size();
		for (MicroblogEntry entry : filtered) {
			String message = "writing entry "+(index++)+"/"+size+" : "+entry;
			writer.write(entry, outputRoot, message);
		}
		linkResolver.save();
	}

}

package org.ndx.lifestream.shaarli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.vfs2.FileObject;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Parent;
import org.jdom2.filter.Filters;
import org.jdom2.input.DOMBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.internal.Nullable;

public class Shaarli implements InputLoader<MicroblogEntry, ShaarliConfiguration> {
	private static final Logger logger = Logger.getLogger(Shaarli.class.getName());

	private XPathExpression<Element> xpath = XPathFactory.instance().compile("//dt", Filters.element());

	@Override
	public Collection<MicroblogEntry> load(WebClient client, ShaarliConfiguration configuration) {
		String text =  loadXML(client, configuration);
		DOMBuilder builder = new DOMBuilder();
		Document document;
		try {
			document = builder.build(HtmlToMarkdown.transformToValidXhtmlDocument(text));
		} catch (ParserConfigurationException e) {
			throw new UnableToProduceXMLException(e);
		}
		return buildEntriesCollection(configuration, document);
	}

	public String loadXML(final WebClient client, final ShaarliConfiguration configuration) {
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
	 * To parse that structure, we use two method.
	 * This one uses XPath navigation to locate all "dt" tags, then navigates in document looking for next dd tag.
	 * Notice navigation is done by iterating over next XML element, which may be an empty text (for the line return)
	 * 
	 */
	public Collection<MicroblogEntry> buildEntriesCollection(ShaarliConfiguration configuration, Document entries) {
		Collection<MicroblogEntry> returned = new ArrayList<>();
		for(Element entry : xpath.evaluate(entries)) {
			// Notice we need both the DT and the immediatly following DD
			Parent entryParent = entry.getParent();
			int index = entryParent.indexOf(entry);
			index++;
			MicroblogEntry created = null;
			while(created==null) {
				Content next = entryParent.getContent(index);
				if(next instanceof Element) {
					Element nextElement = (Element) next;
					// Hey, there is some text to add, cool !
					if(nextElement.getName().equalsIgnoreCase("DD")) {
						created = createEntryFrom(configuration, entry, nextElement);
					}
					// Damn, no text ? Then create entry with no text.
					if(nextElement.getName().equalsIgnoreCase("dt")) {
						// there is no text for this entry ... weird
						created = createEntryFrom(configuration, entry, null);
					}
				}
				index++;
			}
			returned.add(created);
		}
		return returned;
	}

	private MicroblogEntry createEntryFrom(ShaarliConfiguration configuration, Element dt, @Nullable Element dd) {
		MicroblogEntry returned = new MicroblogEntry();
		returned.setSource(configuration.getSite());
		Element link = dt.getChild("a");
		returned.setLink(link.getAttributeValue("href"));
		returned.setTitle(HtmlToMarkdown.transformHtml(link.getText()).trim());
		long parsedLong = Long.parseLong(link.getAttributeValue("add_date"));
		Date writeDate = new Date();
		writeDate.setTime(parsedLong*1000); // epoch time is given in seconds !
		returned.setWriteDdate(writeDate);
		String tagsText = link.getAttributeValue("tags");
		if(tagsText==null) {
			if (logger.isLoggable(Level.FINE)) {
				logger.log(Level.FINE, returned+" has no tags ?");
			}
		} else {
			returned.setTags(Arrays.asList(tagsText.split(",")));
		}
		returned.setVisible(link.getAttributeValue("private").equals("0"));
		if(dd!=null) {
			returned.setComment(HtmlToMarkdown.transformHtml(dd.getText().trim()));
		}
		return returned;
	}

	public String downloadXML(WebClient client, ShaarliConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage();
			HtmlPage signIn = client.getPage(siteLogin);
			HtmlForm signInForm = signIn.getFormByName("loginform");
			logger.log(Level.INFO, "logging in Shaarli as " + configuration.getLogin());
			((HtmlInput) signInForm.getInputByName("login")).setValueAttribute(configuration.getLogin());
			((HtmlInput) signInForm.getInputByName("password")).setValueAttribute(configuration.getPassword());
			HtmlSubmitInput submitButton = (HtmlSubmitInput) signInForm.getInputByValue("Login");
			HtmlPage signedIn = submitButton.click();
			if (200 == signedIn.getWebResponse().getStatusCode()) {
				String signedInUrl = signedIn.getUrl().toString();
				if(signedInUrl.equals(siteLogin)) {
					throw new AuthenticationFailedException(
									configuration.getAuthenticationFailureMessage());
				}
				logger.log(Level.INFO, "logged in ... downloading html now ...");
				// 404 will throw over all this stuff
				HtmlPage xmlExportPage = client.getPage(configuration.getSiteExportPage());
				// yay, serializing an XML document to deserialize it immediatly after may not be optimal. Objection received.
				return xmlExportPage.asXml();
			} else {
				throw new AuthenticationFailedException(
						configuration.getAuthenticationFailureMessage());
			}
		} catch(AuthenticationFailedException e) {
			throw e;
		} catch(Exception e) {
			throw new UnableToDownloadContentException("Unable to download HTML export from Shaarli\n"+configuration.getDownloadFailureMessage(), e);
		}
	}

	@Override
	public void output(Mode mode, Collection<MicroblogEntry> inputs, FileObject outputRoot, ShaarliConfiguration configuration) {
		Collection<MicroblogEntry> filtered = Collections2.filter(inputs, new Predicate<MicroblogEntry>() {

			@Override
			public boolean apply(MicroblogEntry input) {
				return input.isVisible();
			}
		});
		OutputWriter writer = mode.getWriter();
		LinkResolver linkResolver = configuration.getLinkResolver();
		writer.addListener(linkResolver);
		int index = 1;
		int size = filtered.size();
		for (MicroblogEntry entry : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing entry "+(index++)+"/"+size+" : "+entry);
			}
			writer.write(entry, outputRoot);
		}
		linkResolver.save();
	}

}

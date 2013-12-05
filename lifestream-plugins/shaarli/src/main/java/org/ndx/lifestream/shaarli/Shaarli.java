package org.ndx.lifestream.shaarli;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileObject;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Parent;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.Constants;
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

	private XPathExpression<Element> xpathForImageUrl = XPathFactory.instance().compile("//dt", Filters.element());

	@Override
	public Collection<MicroblogEntry> load(WebClient client, ShaarliConfiguration configuration) {
		Document entries = loadXML(client, configuration);
		return buildEntriesCollection(entries);
	}

	/**
	 * Read
	 * @param entries
	 * @return
	 */
	public Collection<MicroblogEntry> buildEntriesCollection(Document entries) {
		Collection<MicroblogEntry> returned = new ArrayList<>();
		for(Element entry : xpathForImageUrl.evaluate(entries)) {
			// Notice we need both the DT and the immediatly following DD
			Parent entryParent = entry.getParent();
			int index = entryParent.indexOf(entry);
			index++;
			Element dd = null;
			while(dd==null) {
				Content next = entryParent.getContent(index);
				if(next instanceof Element) {
					Element nextElement = (Element) next;
					if(nextElement.getName().equalsIgnoreCase("DD")) {
						dd = nextElement;
						returned.add(createEntryFrom(entry, dd));
					}
					if(nextElement.getName().equalsIgnoreCase("dt")) {
						// there is no text for this entry ... weird
						returned.add(createEntryFrom(entry, dd));
					}
				}
				index++;
			}
		}
		return returned;
	}

	private MicroblogEntry createEntryFrom(Element DT, @Nullable Element DD) {
		MicroblogEntry returned = new MicroblogEntry();
		Element link = DT.getChild("a");
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
		if(DD!=null) {
			returned.setComment(HtmlToMarkdown.transformHtml(DD.getText()));
		}
		return returned;
	}

	public Document loadXML(WebClient client, ShaarliConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage();
			HtmlPage signIn = client.getPage(siteLogin);
			HtmlForm signInForm = signIn.getFormByName("loginform");
			logger.log(Level.INFO, "logging in Shaarli as " + configuration.getLogin());
			((HtmlInput) signInForm.getInputByName("login"))
					.setValueAttribute(configuration.getLogin());
			((HtmlInput) signInForm.getInputByName("password"))
					.setValueAttribute(configuration.getPassword());
			HtmlPage signedIn = ((HtmlSubmitInput) signInForm.getInputByValue("Login")).click();
			String authenticationFailedMessage = "unable to sign in Shaarli using login "
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
				logger.log(Level.INFO, "logged in ... downloading html now ...");
				HtmlPage xmlExportPage = client.getPage(configuration.getSiteExportPage());
				// yay, serializing an XML document to deserialize it immediatly after may not be optimal. Objection received.
				SAXBuilder builder = new SAXBuilder();
				return builder.build(new ByteArrayInputStream(xmlExportPage.asXml().getBytes(Constants.UTF_8)));
			} else {
				throw new AuthenticationFailedException(
						authenticationFailedMessage);
			}
		} catch(Exception e) {
			throw new UnableToDownloadContentException("Unable to download HTML export from Shaarli", e);
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
		int index = 1;
		int size = filtered.size();
		for (MicroblogEntry entry : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing entry "+(index++)+"/"+size+" : "+entry);
			}
			writer.write(entry, outputRoot);
		}
	}

}

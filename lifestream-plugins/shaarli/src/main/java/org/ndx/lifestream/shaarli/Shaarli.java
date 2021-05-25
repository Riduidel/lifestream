package org.ndx.lifestream.shaarli;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.internal.Nullable;

import twitter4j.JSONObject;

public class Shaarli implements InputLoader<MicroblogEntry, ShaarliConfiguration> {
	private static final Logger logger = Logger.getLogger(Shaarli.class.getName());

	@Override
	public Collection<MicroblogEntry> load(WebDriver client, ShaarliConfiguration configuration) {
		String text =  loadXML(client, configuration);
		org.jsoup.nodes.Document document = Jsoup.parse(text);
		return buildEntriesCollection(configuration, document);
	}

	public String loadXML(final WebDriver client, final ShaarliConfiguration configuration) {
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
	
	private String getFieldDescription(WebElement element) {
		StringBuilder returned = new StringBuilder();
		returned.append("<");
		returned.append(element.getTagName());
		returned.append(" type=\"").append(element.getAttribute("type")).append("\"");
		returned.append(" name=\"").append(element.getAttribute("name")).append("\"");
		returned.append(" value=\"").append(element.getAttribute("value")).append("\"");
		returned.append(">...</>");
		return returned.toString();
	}

	public String downloadXML(WebDriver browser, ShaarliConfiguration configuration) {
		try {
			String siteLogin = configuration.getSiteLoginPage();
			browser.get(siteLogin);
			logger.log(Level.INFO, "logging in Shaarli as " + configuration.getLogin());
			browser.findElements(By.xpath("//input[@name='login']")).stream()
				.filter(WebElement::isDisplayed)
				.forEach(field -> {
				try {
				field.click();
				field.sendKeys(configuration.getLogin());
				logger.info("Set login field "+getFieldDescription(field));
				} catch(ElementNotInteractableException e) {
					
				}
			});
			browser.findElements(By.xpath("//input[@name='password']")).stream()
				.filter(WebElement::isDisplayed)
				.forEach(field -> {
				try {
				field.click();
				field.sendKeys(configuration.getPassword());
				logger.info("Set password field "+getFieldDescription(field));
				} catch(ElementNotInteractableException e) {
					
				}
			});
			browser.findElements(By.xpath("//input[@value='Login']")).stream()
				.filter(WebElement::isDisplayed)
				.forEach(field -> {
				try {
				field.click();
				logger.info("Clicked on login button "+getFieldDescription(field));
				} catch(StaleElementReferenceException e) {
				} catch(ElementNotInteractableException e) {
				}
			});
			new WebDriverWait(browser, 60*60).until(ExpectedConditions
					.presenceOfElementLocated(By.id("linklist")));
			logger.log(Level.INFO, "logged in ... downloading html now ...");
			// 404 will throw over all this stuff
			try {
				browser.get(configuration.getSiteExportPage());
			} catch(TimeoutException e) {
				// There is something strange here that I don't fully understand, but the only solution I have is letting this timeout
				// Bad, and dangerous, but the only solution I could think about
			}
			LogEntries logs = browser.manage().logs().get(LogType.PERFORMANCE);
			List<JSONObject> jsonLogs = logs.getAll().stream()
				.map(entry -> new JSONObject(entry.getMessage()))
				.filter(entry -> "Network.responseReceived".equals(entry.getJSONObject("message").getString("method")))
				.collect(Collectors.toList());
			JSONObject downloadExchange = jsonLogs.get(jsonLogs.size()-1);
			JSONObject response = downloadExchange.getJSONObject("message").getJSONObject("params").getJSONObject("response");
			String contentDisposition = response.getJSONObject("headers").getString("Content-disposition");
			String filename = contentDisposition.substring(contentDisposition.indexOf('=')+1);
			// So there should now be an html file in download folder, no?
			File file = new File(WebClientUtils.getDownloadFolder(), filename);
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(browser)
					.withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofMillis(100));
			try {
				wait.until( unused -> false);
			} catch(org.openqa.selenium.TimeoutException e) {
				// I don't care about the timeout exception
			}
			WebClientUtils.download(browser, file);
			try {
				return IOUtils.toString(file.toURI(), "UTF-8");
			} finally {
				file.delete();
			}
		} catch(AuthenticationFailedException e) {
			throw e;
		} catch(Exception e) {
			throw new UnableToDownloadContentException("Unable to download HTML export from Shaarli\n"+configuration.getDownloadFailureMessage(), e);
		} finally {
			browser.close();
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

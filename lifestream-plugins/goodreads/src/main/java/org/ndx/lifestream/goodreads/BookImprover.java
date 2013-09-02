package org.ndx.lifestream.goodreads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.concurrent.Callable;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.filter.ContentFilter;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.ndx.lifestream.utils.Constants;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class BookImprover implements Callable<ImprovedBook> {
	/**
	 * A class only made to hold some constants in other to separate data
	 * @author Nicolas
	 *
	 */
	private static class FindWork {
		private static final String QUERY = "http://www.goodreads.com/search.xml?key="+DEVELOPPER_KEY+"&q=";
		
		public static XPathExpression<Element> xpathForImageUrl = XPathFactory.instance().compile("//image_url", Filters.element());
		public static XPathExpression<Element> xpathForTitle = XPathFactory.instance().compile("//title", Filters.element());
		public static XPathExpression<Element> xpathForWorkId = XPathFactory.instance().compile("//work/id", Filters.element());
		
		/**
		 * Improve book by setting image and title, and also return work id for serie identification
		 * @param client TODO
		 * @param query
		 * @param returned
		 * @return
		 * @throws IOException 
		 * @throws JDOMException 
		 * @throws UnsupportedEncodingException 
		 */
		public static String improveBook(WebClient client, String query, ImprovedBook returned) throws UnsupportedEncodingException, JDOMException, IOException {
			Document bookXmlData = queryToJDOM(client, QUERY+query);
			Element imageUrlText = xpathForImageUrl.evaluateFirst(bookXmlData);
			if(imageUrlText!=null)
				returned.imageUrl = imageUrlText.getText();
			Element titleText = xpathForTitle.evaluateFirst(bookXmlData);
			if(titleText!=null)
				returned.title = titleText.getText();
			return xpathForWorkId.evaluateFirst(bookXmlData).getText();
		}
	}
	
	private static class FindSerie {

		private static final String QUERY = "http://www.goodreads.com/series/work.xml?key="+DEVELOPPER_KEY+"&id=";

		public static void improveBook(WebClient client, String workId,
				ImprovedBook returned) throws MalformedURLException, UnsupportedEncodingException, IOException, JDOMException {
			Document bookXmlData = queryToJDOM(client, QUERY+workId);
		}
		
	}

	private static Document queryToJDOM(WebClient client, String query)
			throws IOException, MalformedURLException, JDOMException,
			UnsupportedEncodingException {
		Page bookXml = client.getPage(query);
		String content = bookXml.getWebResponse().getContentAsString(Constants.UTF_8);
		Document bookXmlData = builder.build(new ByteArrayInputStream(content.getBytes(Constants.UTF_8)));
		return bookXmlData;
	}
	/**
	 * Yeah this is my dev key. Use it with care
	 */
	private static final String DEVELOPPER_KEY = "vzlZHr69We4utsOyP508tg";

	private static SAXBuilder builder = new SAXBuilder();

	private Book rawBook;
	private WebClient client;

	private Collection<Book> destination;

	public BookImprover(WebClient client, Book rawBook, Collection<Book> books) {
		this.client = client;
		this.rawBook = rawBook;
		this.destination = books;
	}

	@Override
	public ImprovedBook call() throws Exception {
		ImprovedBook returned = improveBook();
		destination.add(returned);
		return returned;
	}

	private ImprovedBook improveBook() throws IOException,
			MalformedURLException, JDOMException {
		ImprovedBook returned = new ImprovedBook(rawBook);
		String query = null;
		if(returned.isbn13==null) {
			if(returned.title==null) {
				query = returned.title;
			}
		} else {
			query = returned.isbn13;
		}
		if(query!=null) {
			String workId = FindWork.improveBook(client, query, returned);
			FindSerie.improveBook(client, workId, returned);
		}
		return returned;
	}

}

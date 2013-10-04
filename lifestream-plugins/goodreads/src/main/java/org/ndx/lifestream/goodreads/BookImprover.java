package org.ndx.lifestream.goodreads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class BookImprover implements Callable<Void> {
	private static final Logger logger = Logger.getLogger(BookImprover.class.getName());
	/**
	 * A class only made to hold some constants in other to separate data
	 * @author Nicolas
	 *
	 */
	public static class FindWork {
		private static final String QUERY = Goodreads.GOODREADS_BASE+"search.xml?key="+DEVELOPPER_KEY+"&q=";

		public static XPathExpression<Element> xpathForImageUrl = XPathFactory.instance().compile("//image_url", Filters.element());
		public static XPathExpression<Element> xpathForTitle = XPathFactory.instance().compile("//title", Filters.element());
		public static XPathExpression<Element> xpathForWorkId = XPathFactory.instance().compile("//work/id", Filters.element());

		/**
		 * Improve book by setting image and title, and also return work id for serie identification
		 * @param client TODO
		 * @param query
		 * @param returned
		 * @param configuration
		 * @return first work id, which will be used to grab serie
		 * @throws IOException
		 * @throws JDOMException
		 * @throws UnsupportedEncodingException
		 */
		public String improveBook(WebClient client, String query, ImprovedBook returned, GoodreadsConfiguration configuration) {
			Document bookXmlData = queryToJDOM(client, QUERY+query, configuration, "books", query);
			Element imageUrlText = xpathForImageUrl.evaluateFirst(bookXmlData);
			if(imageUrlText!=null)
				returned.imageUrl = imageUrlText.getText();
			Element titleText = xpathForTitle.evaluateFirst(bookXmlData);
			if(titleText!=null)
				returned.title = titleText.getText();
			return xpathForWorkId.evaluateFirst(bookXmlData).getText();
		}
	}

	public static class FindSerie {

		private static final String QUERY = Goodreads.GOODREADS_BASE+"series/work.xml?key="+DEVELOPPER_KEY+"&id=";
		public static XPathExpression<Element> xpathForSerie = XPathFactory.instance().compile("//series_work", Filters.element());
		public static XPathExpression<Element> xpathForSerieId = XPathFactory.instance().compile("series/id", Filters.element());
		public static XPathExpression<Element> xpathForSerieTitle = XPathFactory.instance().compile("series/title", Filters.element());
		public static XPathExpression<Element> xpathForSerieDescription = XPathFactory.instance().compile("series/description", Filters.element());
		public static XPathExpression<Element> xpathForPositionInSerie = XPathFactory.instance().compile("user_position", Filters.element());

		/**
		 * Get containing serie.
		 * May fail (XPath exception) if none found
		 * @param client
		 * @param destination
		 * @param workId
		 * @param source
		 * @param configuration
		 * @return collection of series this book associates to
		 * @throws MalformedURLException
		 * @throws UnsupportedEncodingException
		 * @throws IOException
		 * @throws JDOMException
		 */
		public Collection<Serie> improveBook(WebClient client,
				FinderCrudService<BookInfos, BookInfosInformer> destination,
				String workId,
				ImprovedBook source, GoodreadsConfiguration configuration) {
			Document bookXmlData = queryToJDOM(client, QUERY+workId, configuration, "series", workId);
			Collection<Serie> returned = new ArrayList<>();

			List<Element> series = xpathForSerie.evaluate(bookXmlData);
			for(Element element : series) {
				addBookToSerie(destination, workId, source, element);
			}
			return returned;
		}

		private void addBookToSerie(FinderCrudService<BookInfos, BookInfosInformer> destination, String workId, ImprovedBook source, Element element) {
			String serieId = xpathForSerieId.evaluateFirst(element).getText();
			String serieDesc = xpathForSerieDescription.evaluateFirst(element).getText();
			String positionInSerie = xpathForPositionInSerie.evaluateFirst(element).getText();

			try {
				serieDesc = HtmlToMarkdown.transformHtml(serieDesc);
			} catch(RuntimeException e) {
				logger.log(Level.WARNING, "something went wrong while parsing one serie of work "+workId, e);
			}

			Serie used = findOrCreate(destination, serieId);
			used.setId(serieId);
			used.title = xpathForSerieTitle.evaluateFirst(element).getText().trim();
			used.description = serieDesc;
			used.setBook(positionInSerie, source);
		}

		private Serie findOrCreate(
				FinderCrudService<BookInfos, BookInfosInformer> destination,
				final String serieId) {
			try {
				return (Serie) destination.find().matching(new QueryBuilder<BookInfosInformer>() {

					@Override
					public QueryExpression createMatchingExpression(BookInfosInformer informer) {
						return informer.getId().equalsTo(serieId);
					}
				}).getFirst();
			} catch(Exception e) {
				Serie returned = new Serie();
				returned.setId(serieId);
				synchronized(destination) {
					return (Serie) destination.create(returned);
				}
			}
		}

	}

	private static Document queryToJDOM(WebClient client, String query, GoodreadsConfiguration configuration, String cacheFolder, String cacheKey) {
		String path = cacheFolder+"/"+cacheKey+".xml";
		try {
			FileObject cacheFile = configuration.getCacheFolder().resolveFile(path);
			String content = null;
			if(cacheFile.exists()) {
				try(InputStream input = cacheFile.getContent().getInputStream()) {
					content = IOUtils.toString(input, Constants.UTF_8);
				}
			} else {
				logger.info("download goodreads infos for "+cacheFolder+"/"+cacheKey);
				Page bookXml = client.getPage(query);
				content = bookXml.getWebResponse().getContentAsString(Constants.UTF_8);
				try(OutputStream output = cacheFile.getContent().getOutputStream()) {
					IOUtils.write(content, output, Constants.UTF_8);
				}
			}
			SAXBuilder builder = new SAXBuilder();
			Document bookXmlData = builder.build(new ByteArrayInputStream(content.getBytes(Constants.UTF_8)));
			return bookXmlData;
		} catch(RuntimeException | JDOMException | IOException e) {
			logger.log(Level.WARNING, "something failed while parsing "+path);
			throw new UnableToParseXMLException(e);
		}
	}

	private FindWork workFinder = new FindWork();
	private FindSerie serieFinder = new FindSerie();

	/**
	 * Yeah this is my dev key. Use it with care
	 */
	private static final String DEVELOPPER_KEY = "vzlZHr69We4utsOyP508tg";


	private Book rawBook;
	private WebClient client;

	private FinderCrudService<BookInfos, BookInfosInformer> destination;

	private GoodreadsConfiguration configuration;

	public BookImprover(WebClient client,
			Book rawBook,
			FinderCrudService<BookInfos, BookInfosInformer> books,
			GoodreadsConfiguration configuration) {
		this.client = client;
		this.rawBook = rawBook;
		this.destination = books;
		this.configuration = configuration;
	}

	@Override
	public Void call() throws Exception {
		improveBook();
		return null;
	}

	private void improveBook() {
		ImprovedBook returned = new ImprovedBook(rawBook);
		String query = null;
		try {
			if(returned.getIsbn13()==null) {
				if(returned.title==null) {
					query = returned.title;
				}
			} else {
				query = returned.getIsbn13();
			}
			if(query!=null) {
				logger.info("Improvving book "+returned);
				synchronized(destination) {
					returned = (ImprovedBook) destination.create(returned);
				}
				String workId = workFinder.improveBook(client, query, returned, configuration);
				synchronized(destination) {
					returned = (ImprovedBook) destination.update(returned);
				}
				serieFinder.improveBook(client, destination, workId, returned, configuration);
			}
		} catch(Throwable t) {
			logger.log(Level.WARNING, "something failed while improving book "+returned, t);
			throw t;
		}
	}

}

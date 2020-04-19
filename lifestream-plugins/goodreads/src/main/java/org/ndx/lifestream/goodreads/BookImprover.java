package org.ndx.lifestream.goodreads;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.maven.model.Developer;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.goodreads.references.Reference;
import org.ndx.lifestream.goodreads.references.References;
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
		public static final String QUERY = GoodreadsConfiguration.GOODREADS_BASE + "book/isbn/%s?key=" + GoodreadsConfiguration.DEVELOPPER_KEY;

		private XPathExpression<Element> xpathForImageUrl = XPathFactory.instance().compile("//image_url", Filters.element());
		private XPathExpression<Element> xpathForTitle = XPathFactory.instance().compile("//title", Filters.element());
		private XPathExpression<Element> xpathForDescription = XPathFactory.instance().compile("//description", Filters.element());
		private XPathExpression<Element> xpathForSmallImage = XPathFactory.instance().compile("//small_image_url", Filters.element());
		private XPathExpression<Element> xpathForUrl = XPathFactory.instance().compile("//url", Filters.element());
		private XPathExpression<Element> xpathForAuthors = XPathFactory.instance().compile("GoodreadsResponse/book/authors/author", Filters.element());
		private XPathExpression<Element> xpathForWorkId = XPathFactory.instance().compile("//work/id", Filters.element());

		/**
		 * Improve book by setting image and title, and also return work id for serie identification
		 * @param destination destination servide for storing all output objects. It is used to locate/create authors
		 * @return first work id, which will be used to grab serie
		 */
		public String improveBook(WebClient client, String isbn,
				Book returned,
				AbstractConfiguration configuration,
				FinderCrudService<BookInfos,BookInfosInformer> destination) throws FileSystemException {
			
			Document bookXmlData = queryToJDOM(client, 
					String.format(QUERY, isbn), configuration, "books", isbn);
			Element imageUrlText = xpathForImageUrl.evaluateFirst(bookXmlData);
			if(imageUrlText!=null)
				returned.bigImage = imageUrlText.getText();
			// I used to replace title, but it's in fact a bad idea, because localized title is replaced by english one
			if(returned.getTitle()==null) {
				Element titleText = xpathForTitle.evaluateFirst(bookXmlData);
				if(titleText!=null)
					returned.setTitle(titleText.getText());
			}
			Element workId = xpathForWorkId.evaluateFirst(bookXmlData);
			if(workId==null)
				throw new UnableToLocateWorkIdException("we were unable to find work id for query "+isbn+"\nA cache file should be available at "+getCachedFileForKey(configuration, "books", isbn));
			Element description = xpathForDescription.evaluateFirst(bookXmlData);
			if(description!=null) {
				returned.description = HtmlToMarkdown.transformHtml(description.getText());
			}
			Element smallImage = xpathForSmallImage.evaluateFirst(bookXmlData);
			if(smallImage!=null) {
				returned.smallImage = smallImage.getText();
			}
			Element url = xpathForUrl.evaluateFirst(bookXmlData);
			if(url!=null) {
				returned.url = simplify(url.getText());
			}
			List<Element> authors = xpathForAuthors.evaluate(bookXmlData);
			for(Element author : authors) {
				addBookToAuthor(destination, returned, author);
			}
			return workId.getText();
		}

		/**
		 * Simplify book url by removing anything (inclusive) after last dot positon
		 * @param text
		 * @return
		 */
		private String simplify(String text) {
			return text.substring(0, text.lastIndexOf('.'));
		}

		private void addBookToAuthor(
				FinderCrudService<BookInfos, BookInfosInformer> destination,
				Book returned, Element element) {
			String authorId = element.getChildTextTrim("id");
			String authorName = element.getChildTextTrim("name");
			String image = element.getChildTextTrim("image_url");

			Author used = findOrCreateSubClass(destination, authorId, Author.class);
			used.setId(authorId);
			used.setName(authorName);
			used.setImage(image);
			used.addBook(returned);
			destination.update(used);
		}
	}

	public static class FindSerie {

		private XPathExpression<Element> xpathForSerie = XPathFactory.instance().compile("//series_work", Filters.element());
		private XPathExpression<Element> xpathForSerieId = XPathFactory.instance().compile("series/id", Filters.element());
		private XPathExpression<Element> xpathForSerieTitle = XPathFactory.instance().compile("series/title", Filters.element());
		private XPathExpression<Element> xpathForSerieDescription = XPathFactory.instance().compile("series/description", Filters.element());
		private XPathExpression<Element> xpathForPositionInSerie = XPathFactory.instance().compile("user_position", Filters.element());

		/**
		 * Get containing serie.
		 * May fail (XPath exception) if none found
		 * @return collection of series this book associates to
		 */
		public Collection<Serie> improveBook(WebClient client,
				FinderCrudService<BookInfos, BookInfosInformer> destination,
				String workId,
				Book source, AbstractConfiguration configuration) {
			Document bookXmlData = queryToJDOM(client, 
					// https://www.goodreads.com/series/40321-drina?format=xml&key=vzlZHr69We4utsOyP508tg
					String.format("%sseries/work/%s?format=xml&key=%s", GoodreadsConfiguration.GOODREADS_BASE, workId, GoodreadsConfiguration.DEVELOPPER_KEY), 
					configuration, "series", workId);
			Collection<Serie> returned = new ArrayList<>();

			List<Element> series = xpathForSerie.evaluate(bookXmlData);
			for(Element element : series) {
				addBookToSerie(destination, workId, source, element);
			}
			return returned;
		}

		private void addBookToSerie(FinderCrudService<BookInfos, BookInfosInformer> destination, String workId, Book source, Element element) {
			String serieId = xpathForSerieId.evaluateFirst(element).getText();
			String serieDesc = xpathForSerieDescription.evaluateFirst(element).getText();
			String positionInSerie = xpathForPositionInSerie.evaluateFirst(element).getText();

			try {
				serieDesc = HtmlToMarkdown.transformHtml(serieDesc);
			} catch(RuntimeException e) {
				logger.log(Level.WARNING, "something went wrong while parsing one serie of work "+workId, e);
			}

			Serie used = findOrCreateSubClass(destination, serieId, Serie.class);
			used.setTitle(xpathForSerieTitle.evaluateFirst(element).getText().trim());
			used.description = serieDesc;
			used.setBook(positionInSerie, source);
		}

	}

	private static synchronized Document queryToJDOM(WebClient client, String query, AbstractConfiguration configuration, String cacheFolder, String cacheKey) {
		try {
			FileObject cacheFile = getCachedFileForKey(configuration, cacheFolder, cacheKey);
			String content = null;
			if(cacheFile.exists()) {
				try(InputStream input = cacheFile.getContent().getInputStream()) {
					content = IOUtils.toString(input, Constants.UTF_8);
				}
			} else {
				Authenticator.authenticateInGoodreads(client, (GoodreadsConfiguration) configuration);
				logger.info("download goodreads infos for "+cacheFolder+"/"+cacheKey);
				Page bookXml = client.getPage(query);
				content = bookXml.getWebResponse().getContentAsString(Constants.UTF_8_CHARSET);
				try(OutputStream output = cacheFile.getContent().getOutputStream()) {
					IOUtils.write(content, output, Constants.UTF_8);
				}
			}
			SAXBuilder builder = new SAXBuilder();
			Document bookXmlData = builder.build(new ByteArrayInputStream(content.getBytes(Constants.UTF_8)));
			return bookXmlData;
		} catch(RuntimeException | JDOMException | IOException e) {
			logger.log(Level.WARNING, "something failed while parsing "+getCachePath(cacheFolder, cacheKey));
			throw new UnableToParseXMLException(e);
		}
	}

	private static FileObject getCachedFileForKey(AbstractConfiguration configuration, String cacheFolder, String cacheKey) throws FileSystemException {
		return configuration.getCacheFolder().resolveFile(getCachePath(cacheFolder, cacheKey));
	}

	private static String getCachePath(String cacheFolder, String cacheKey) {
		return cacheFolder+"/"+cacheKey+".xml";
	}

	static <Type extends BookInfos> Type findOrCreateSubClass(
			FinderCrudService<BookInfos, BookInfosInformer> destination,
			final String serieId, Class<Type> createdType) {
		try {
			return (Type) destination.find().matching(new QueryBuilder<BookInfosInformer>() {

				@Override
				public QueryExpression createMatchingExpression(BookInfosInformer informer) {
					return informer.getId().equalsTo(serieId);
				}
			}).getFirst();
		} catch(Exception e) {
				Type returned;
				try {
					returned = createdType.newInstance();
					returned.setId(serieId);
					synchronized(destination) {
						return (Type) destination.create(returned);
					}
				} catch (InstantiationException | IllegalAccessException cantCreate) {
					throw new GoodreadsException("Seems like the "+createdType.getName()+" class has no accessible public constructor ...", cantCreate);
				}
		}
	}

	private FindWork workFinder = new FindWork();
	private FindSerie serieFinder = new FindSerie();

	private Book book;
	private WebClient client;

	private FinderCrudService<BookInfos, BookInfosInformer> destination;

	private AbstractConfiguration configuration;

	public BookImprover(WebClient client,
			Book rawBook,
			FinderCrudService<BookInfos, BookInfosInformer> books,
			AbstractConfiguration configuration) {
		this.client = client;
		this.book = rawBook;
		this.destination = books;
		this.configuration = configuration;
	}

	@Override
	public Void call() throws Exception {
		improveBook();
		return null;
	}

	private void improveBook() {
		String query = null;
		try {
			if(book.getIsbn13()==null) {
				if(book.getTitle()==null) {
					query = book.getTitle();
				}
			} else {
				query = book.getIsbn13();
			}
			if(query!=null) {
				logger.info("Improvving book "+book);
				synchronized(destination) {
					book = (Book) destination.create(book);
				}
				String workId = workFinder.improveBook(client, query, book, configuration, destination);
				synchronized(destination) {
					book = (Book) destination.update(book);
				}
				serieFinder.improveBook(client, destination, workId, book, configuration);
			}
		} catch(RuntimeException e) {
			logger.log(Level.WARNING, "something failed while improving book "+book, e);
			throw e;
		} catch(Exception e) {
			logger.log(Level.WARNING, "something failed while improving book "+book, e);
			throw new GoodreadsException("something failed while improving book "+book, e);
		} catch(Throwable t) {
			logger.log(Level.WARNING, "something failed while improving book "+book, t);
			throw t;
		}
	}

}

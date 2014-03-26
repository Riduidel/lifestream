package org.ndx.lifestream.goodreads;

import java.io.CharArrayReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.goodreads.references.Reference;
import org.ndx.lifestream.goodreads.references.ReferencesMerger;
import org.ndx.lifestream.goodreads.references.Resolvers;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import au.com.bytecode.opencsv.CSVReader;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class Goodreads implements InputLoader<BookInfos, GoodreadsConfiguration> {
	private static final Logger logger = Logger.getLogger(Goodreads.class.getName());

	private static final String INPUT_DATE_FORMAT = "yyyy/MM/dd";
	private static final DateFormat INPUT_FORMATTER = new SimpleDateFormat(INPUT_DATE_FORMAT);
	private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final DateFormat OUTPUT_FORMATTER = new SimpleDateFormat(OUTPUT_DATE_FORMAT);

	private GaedoEnvironmentProvider goodreadsEnvironment = new GaedoEnvironmentProvider();

	public String xsl = null;

	public static final String GOODREADS_BASE = "http://www.goodreads.com/";

	/**
	 * Collection loading is partially synchronous (reading the CSV),
	 * partially asynchronous (fetching metadatas from Goodreads API and inserting
	 * a text header containing those datas)
	 * @param client, used to improve books. May be null (in which case there is obviously no improvement done)
	 * @param csv source csv file
	 * @return a collection of valid and usable books
	 */
	public FinderCrudService<BookInfos, BookInfosInformer> buildBooksCollection(WebClient client, List<String[]> csv, GoodreadsConfiguration configuration) {
		FinderCrudService<BookInfos, BookInfosInformer> bookService = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		TreeMap<String, Reference> allReferences = new TreeMap<>();
		Map<String, Reference> references = Collections.synchronizedSortedMap(allReferences);
		ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadCount());
		Map<String, Integer> columns = getColumnsNamesToColumnsIndices(csv.get(0));
		List<String[]> usableLines = csv.subList(1, csv.size());
		// Assynchronous submit allow us to wait for completion without stopping executor service
		Collection<Callable<Void>> toRun = new LinkedList<>();
		for(String[] line : usableLines) {
			Book rawBook = createBook(columns, line);
			toRun.add(new BookImprover(client, rawBook, bookService, configuration));
		}
		// We in fact don't care about the results
		// Bu this is the way to wait for termination of those specific tasks
		try {
			executor.invokeAll(toRun);
			toRun.clear();
			// Now build references
			for(BookInfos b : bookService.findAll()) {
				if(b instanceof Book) {
					toRun.add(new ReferencesMerger((Book) b, references));
				}
			}
			executor.invokeAll(toRun);
			toRun.clear();
			// now books have been improved, we can resolve all references
			for (Reference reference : references.values()) {
				toRun.add(Resolvers.resolverFor(reference, bookService));
			}
			executor.invokeAll(toRun);
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			throw new UnableTRunImprovementTasksException(e);
		}
		return bookService;
	}
	public static enum Columns {
		Title, Additional_Authors, ISBN, ISBN13, My_Rating, Average_Rating, Number_of_Pages, Original_Publication_Year, Date_Read, Bookshelves, Exclusive_Shelf, My_Review, Private_Notes, Owned_Copies;
		
		private String asHeader() {
			return name().replace('_', ' ');
		}

		public String getString(Map<String, Integer> columns, String[] line) {
			int index = columns.get(asHeader());
			if(index<line.length)
				return line[index];
			else
				return "";
		}
		
		public Integer getInteger(Map<String, Integer> columns, String[] line) {
			String v = getString(columns, line);
			if(v.length()==0)
				return 0;
			else
				return  new Integer(v);
		}
		
		public Float getFloat(Map<String, Integer> columns, String[] line) {
			String v = getString(columns, line);
			if(v.length()==0)
				return 0f;
			else
				return  new Float(v);
		}
	}

	Book createBook(Map<String, Integer> columns, String[] line) {
		Book book = new Book();
		book.setTitle(Columns.Title.getString(columns, line));
//		String additionalAuthors = Columns.Additional_Authors.getString(columns, line);
		book.setIsbn10(filterIsbn(Columns.ISBN.getString(columns, line)));
		book.setIsbn13(filterIsbn(Columns.ISBN13.getString(columns, line)));
		book.rating = Columns.My_Rating.getInteger(columns, line);
		book.average = Columns.Average_Rating.getFloat(columns, line);
		book.pages = Columns.Number_of_Pages.getInteger(columns, line);
		book.initialPublication = Columns.Original_Publication_Year.getString(columns, line);
		String dateRead = Columns.Date_Read.getString(columns, line);
		if(!(dateRead==null || dateRead.length()==0))
			book.setRead(parseDate(dateRead));
		book.addAllTags(Arrays.asList(Columns.Bookshelves.getString(columns, line).split(",")));
		book.addAllTags(Arrays.asList(Columns.Exclusive_Shelf.getString(columns, line).split(",")));
		book.setReview(HtmlToMarkdown.transformHtml(Columns.My_Review.getString(columns, line)));
		book.notes = Columns.Private_Notes.getString(columns, line);
		book.owns = Columns.Owned_Copies.getInteger(columns, line);
		// Add a tag for book score
		if(book.rating.floatValue()>0)
			book.addTag(Book.ratingAsTag(book.rating));
		return book;
	}

	private String filterIsbn(String string) {
		if(string!=null && string.length()>2)
			return string.substring(2,string.length());
		return null;
	}

	Date parseDate(String text) {
		Date d;
		try {
			d = INPUT_FORMATTER.parse(text);
			return d;
		} catch (ParseException e) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.log(Level.WARNING, "unable to parse date "+text, e);
			}
			return null;
		}
	}

	Map<String, Integer> getColumnsNamesToColumnsIndices(String[] firstLine) {
		Map<String, Integer> returned = new TreeMap<>();
		for (int index = 0; index < firstLine.length; index++) {
			returned.put(firstLine[index], index);
		}
		return returned;
	}

	public List<String[]> loadCSV(final WebClient client, final GoodreadsConfiguration configuration) {
		try {
			String csvContent = configuration.refreshCacheWith(new CacheLoader() {

				@Override
				public String load() throws Exception {
					authenticateInGoodreads(client, configuration.getMail(), configuration.getPassword());
					logger.log(Level.INFO, "logged in ... downloading csv now ...");
					Page csv = client.getPage(GOODREADS_BASE+"review_porter/goodreads_export.csv");
					// May cause memory error, but later ...
					return csv.getWebResponse().getContentAsString();
				}
			});
			return splitIntoRows(csvContent);
		} catch(AuthenticationFailedException e) {
			throw e;
		} catch(Exception e) {
			throw new UnableToDownloadContentException("unable to download CSV from Goodreads", e);
		}
	}

	public static void authenticateInGoodreads(WebClient client, String username, String password)
			throws IOException, MalformedURLException {
		HtmlPage signIn = client.getPage(GOODREADS_BASE+"user/sign_in");
		HtmlForm signInForm = signIn.getFormByName("sign_in");
		logger.log(Level.INFO, "logging in goodreads as "+username);
		signInForm.getInputByName("user[email]").setValueAttribute(username);
		signInForm.getInputByName("user[password]").setValueAttribute(password);
		HtmlPage signedIn = signInForm.getInputByName("next").click();
		String authenticationFailedMessage = "unable to sign in Goodreads using mail "+username+" and password "+password+". can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?";
		if(200==signedIn.getWebResponse().getStatusCode()) {
			if(signedIn.getUrl().equals(signIn.getUrl()))
				throw new AuthenticationFailedException(authenticationFailedMessage);
		} else {
			throw new AuthenticationFailedException(authenticationFailedMessage);
		}
	}

	public List<String[]> splitIntoRows(String csvContent) throws IOException {
		CSVReader reader = new CSVReader(new CharArrayReader(csvContent.toCharArray()));
		try {
			return reader.readAll();
		} finally {
			reader.close();
		}
	}

	/**
	 * Output given book list in the given folder using the given output mode
	 * @param mode output mode, may generate any kind of file
	 * @param books list of books to output
	 * @param output output folder
	 */
	public void output(Mode mode, Collection<BookInfos> books, FileObject output, GoodreadsConfiguration configuration) {
		Collection<BookInfos> filtered = Collections2.filter(books, new Predicate<BookInfos>() {

			@Override
			public boolean apply(BookInfos input) {
				if(input instanceof Book) {
					return input.getTags().contains("read");
				}
				// only output elements thar are in the past
				return input.getWriteDate().compareTo(BookInfos.TODAY)<0;
			}
		});
		OutputWriter writer = mode.getWriter();
		int index = 1;
		int size = filtered.size();
		for (BookInfos book : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing "+book.getClass().getSimpleName().toLowerCase()+" "+(index++)+"/"+size+" : "+book);
			}
			writer.write(book, output);
		}
	}

	@Override
	public Collection<BookInfos> load(WebClient client, GoodreadsConfiguration configuration) {
		try {
			logger.info("loading CSV data");
			List<String[]> rawData = loadCSV(client, configuration);
			logger.info("transforming that data into books");
			FinderCrudService<BookInfos, BookInfosInformer> allBookInfos = buildBooksCollection(client, rawData, configuration);
			// Now all book infos have been loaded, we can resolve references
			return CollectionUtils.asList(allBookInfos.findAll());
		} catch(Exception e) {
			throw new UnableToBuildBookCollection(e);
		}
	}
}
package org.ndx.lifestream.goodreads;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.goodreads.references.Reference;
import org.ndx.lifestream.goodreads.references.ReferencesMerger;
import org.ndx.lifestream.goodreads.references.Resolvers;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.plugin.exceptions.UnableToDownloadContentException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.utils.ThreadSafeSimpleDateFormat;
import org.ndx.lifestream.utils.web.WebClientUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;

import au.com.bytecode.opencsv.CSVReader;

public class Goodreads implements InputLoader<BookInfos, GoodreadsConfiguration> {
	private static final String GOODREADS_EXPORT = "https://www.goodreads.com/review/import";

	static final Logger logger = Logger.getLogger(Goodreads.class.getName());

	private static final String INPUT_DATE_FORMAT = "yyyy/MM/dd";
	private static final ThreadSafeSimpleDateFormat INPUT_FORMATTER = new ThreadSafeSimpleDateFormat(INPUT_DATE_FORMAT);
	private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final ThreadSafeSimpleDateFormat OUTPUT_FORMATTER = new ThreadSafeSimpleDateFormat(
			OUTPUT_DATE_FORMAT);

	private GaedoEnvironmentProvider goodreadsEnvironment = new GaedoEnvironmentProvider();

	public String xsl = null;

	/**
	 * Collection loading is partially synchronous (reading the CSV), partially
	 * asynchronous (fetching metadatas from Goodreads API and inserting a text
	 * header containing those datas)
	 * 
	 * @param client, used to improve books. May be null (in which case there is
	 *                obviously no improvement done)
	 * @param csv     source csv file
	 * @return a collection of valid and usable books
	 */
	public FinderCrudService<BookInfos, BookInfosInformer> buildBooksCollection(WebDriver client, List<String[]> csv,
			GoodreadsConfiguration configuration) {
		FinderCrudService<BookInfos, BookInfosInformer> bookService = goodreadsEnvironment
				.getServiceFor(BookInfos.class, BookInfosInformer.class);
		TreeMap<String, Reference> allReferences = new TreeMap<>();
		Map<String, Reference> references = Collections.synchronizedSortedMap(allReferences);
		ExecutorService executor = Executors.newFixedThreadPool(configuration.getThreadCount());
		Map<String, Integer> columns = getColumnsNamesToColumnsIndices(csv.get(0));
		List<String[]> usableLines = csv.subList(1, csv.size());
		// Assynchronous submit allow us to wait for completion without stopping
		// executor service
		Collection<Callable<Void>> toRun = new LinkedList<>();
		for (String[] line : usableLines) {
			Book rawBook = createBook(columns, line);
			toRun.add(new BookImprover(client, rawBook, bookService, configuration));
		}
		// We in fact don't care about the results
		// Bu this is the way to wait for termination of those specific tasks
		try {
			executor.invokeAll(toRun);
			toRun.clear();
			// Now build references
			for (BookInfos b : bookService.findAll()) {
				if (b instanceof Book) {
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
		Title, Additional_Authors, ISBN, ISBN13, My_Rating, Average_Rating, Number_of_Pages, Original_Publication_Year,
		Date_Read, Bookshelves, Exclusive_Shelf, My_Review, Private_Notes, Owned_Copies, Book_Id;

		private String asHeader() {
			return name().replace('_', ' ');
		}

		public String getString(Map<String, Integer> columns, String[] line) {
			int index = columns.get(asHeader());
			if (index < line.length)
				return line[index];
			else
				return "";
		}

		public Integer getInteger(Map<String, Integer> columns, String[] line) {
			String v = getString(columns, line);
			if (v.length() == 0)
				return 0;
			else
				return new Integer(v);
		}

		public Float getFloat(Map<String, Integer> columns, String[] line) {
			String v = getString(columns, line);
			if (v.length() == 0)
				return 0f;
			else
				return new Float(v);
		}
	}

	Book createBook(Map<String, Integer> columns, String[] line) {
		Book book = new Book();
		book.setId(Columns.Book_Id.getString(columns, line));
		book.setTitle(Columns.Title.getString(columns, line));
//		String additionalAuthors = Columns.Additional_Authors.getString(columns, line);
		book.setIsbn10(filterIsbn(Columns.ISBN.getString(columns, line)));
		book.setIsbn13(filterIsbn(Columns.ISBN13.getString(columns, line)));
		book.setRating(Columns.My_Rating.getInteger(columns, line));
		book.average = Columns.Average_Rating.getFloat(columns, line);
		book.pages = Columns.Number_of_Pages.getInteger(columns, line);
		book.initialPublication = Columns.Original_Publication_Year.getString(columns, line);
		String dateRead = Columns.Date_Read.getString(columns, line);
		if (!(dateRead == null || dateRead.length() == 0))
			book.setRead(parseDate(dateRead));
		book.addAllTags(Arrays.asList(Columns.Bookshelves.getString(columns, line).split(",")));
		book.addAllTags(Arrays.asList(Columns.Exclusive_Shelf.getString(columns, line).split(",")));
		book.setReview(Columns.My_Review.getString(columns, line));
		book.notes = Columns.Private_Notes.getString(columns, line);
		book.owns = Columns.Owned_Copies.getInteger(columns, line);
		// Add a tag for book score
		return book;
	}

	private String filterIsbn(String string) {
		if (string != null && string.length() > 2)
			return string.substring(2, string.length() - 1);
		return null;
	}

	Date parseDate(String text) {
		Date d;
		try {
			d = INPUT_FORMATTER.parse(text);
			return d;
		} catch (ParseException e) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.log(Level.WARNING, "unable to parse date " + text, e);
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

	public List<String[]> loadCSV(final WebDriver client, final GoodreadsConfiguration configuration) {
		try {
			String csvContent = configuration.refreshCacheWith(new CacheLoader() {
				private String getExportIfExist() throws MalformedURLException, IOException {
					client.get(GOODREADS_EXPORT);
					Optional<WebElement> export = getExportElement(client);
					if(export.isPresent()) {
						logger.info("Found export element");
						return readCSVExport(export.get());
					} else {
						logger.info("No export element found. Let's ask one export.");
						// If we're here, there was no export available, so generate it, wait for it to be available, and download it
						client.findElement(By.className("gr-form--compact__submitButton")).click();
						new WebDriverWait(client, 60*60).until(ExpectedConditions.elementToBeClickable(By.className("gr-form--compact__submitButton")));
						export = getExportElement(client);
						if(export.isPresent()) {
							return readCSVExport(export.get());
						} else {
							throw new UnsupportedOperationException("Unable to download reviews!");
						}
					}
				}

				private Optional<WebElement> getExportElement(WebDriver client) {
					List<WebElement> links = WebClientUtils.getLinks(client);
					return links.stream().parallel()
						.map(anchor -> Map.entry(anchor, anchor.getAttribute("href")))
						.filter(entry -> entry.getValue()!=null)
						.filter(entry -> entry.getValue().contains("/review_porter/export/"))
						.map(entry -> entry.getKey())
						.findAny();
				}

				private String readCSVExport(WebElement anchor) throws IOException {
					// We have a CSV export! So use it
					anchor.click();
					// So we must have a file in the download folder now, which name ends with *.csv
					File downloaded = new File(WebClientUtils.getDownloadFolder(), "goodreads_library_export.csv");
					// Hopefully both wait and download are handled in this magic method
					logger.info(String.format("downloading %s", downloaded));
					WebClientUtils.download(client, downloaded);
					try {
						return FileUtils.readFileToString(downloaded, "UTF-8");
					} finally {
						downloaded.delete();
					}
				}

				@Override
				public String load() throws Exception {
					String userId = Authenticator.authenticateInGoodreads(client, configuration.getMail(),
							configuration.getPassword());
					logger.log(Level.INFO, "logged in ... downloading csv now ...");
					String csv = getExportIfExist();
					return csv;
				}
			});
			return splitIntoRows(csvContent);
		} catch (AuthenticationFailedException e) {
			throw e;
		} catch (Exception e) {
			throw new UnableToDownloadContentException("unable to download CSV from Goodreads", e);
		} finally {
			client.close();
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
	 * 
	 * @param mode   output mode, may generate any kind of file
	 * @param books  list of books to output
	 * @param output output folder
	 */
	public void output(Mode mode, Collection<BookInfos> books, FileObject output,
			GoodreadsConfiguration configuration) {
		Collection<BookInfos> filtered = books.stream().parallel().filter(new Predicate<BookInfos>() {
			@Override
			public boolean test(BookInfos input) {
				if (input instanceof Book) {
					return input.getTags().contains("read");
				}
				// only output elements thar are in the past
				return input.getWriteDate().compareTo(BookInfos.TODAY) < 0;
			}
		}).collect(Collectors.toList());
		OutputWriter writer = mode.getWriter();
		LinkResolver linkResolver = configuration.getLinkResolver();
		writer.addListener(linkResolver);
		int index = 1;
		int size = filtered.size();
		for (BookInfos book : filtered) {
			String message = "writing " + book.getClass().getSimpleName().toLowerCase() + " " + (index++)
					+ "/" + size + " : " + book;
			writer.write(book, output, message);
		}
		linkResolver.save();
	}

	@Override
	public Collection<BookInfos> load(WebDriver client, GoodreadsConfiguration configuration) {
		try {
			logger.info("loading CSV data");
			List<String[]> rawData = loadCSV(client, configuration);
			if(rawData.isEmpty())
				throw new UnableToBuildBookCollection("unable to find any books on goodreads. Something is going wrong!");
			logger.info("transforming that data into books");
			FinderCrudService<BookInfos, BookInfosInformer> allBookInfos = buildBooksCollection(client, rawData,
					configuration);
			// Now all book infos have been loaded, we can resolve references
			return CollectionUtils.asList(allBookInfos.findAll());
		} catch (Exception e) {
			throw new UnableToBuildBookCollection(e);
		}
	}
}
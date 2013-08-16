package org.ndx.lifestream.goodreads;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.InputLoader;

import au.com.bytecode.opencsv.CSVReader;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class Goodreads implements InputLoader<Book> {
	private static final Logger logger = Logger.getLogger(Goodreads.class.getName());

	private static final String INPUT_DATE_FORMAT = "yyyy/MM/dd";
	private static final DateFormat INPUT_FORMATTER = new SimpleDateFormat(INPUT_DATE_FORMAT);
	private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final DateFormat OUTPUT_FORMATTER = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
	private static final String YEAR_DATE_FORMAT = "yyyy";
	private static final DateFormat YEAR_FORMATTER = new SimpleDateFormat(YEAR_DATE_FORMAT);
	private static final String MONTH_DATE_FORMAT = "MM";
	private static final DateFormat MONTH_FORMATTER = new SimpleDateFormat(MONTH_DATE_FORMAT);


	public String username;
	public String password;
	public String outputFolder;
	public String xsl = null;

	Collection<Book> buildBooksCollection(List<String[]> csv) {
		Map<String, Integer> columns = getColumnsNamesToColumnsIndices(csv.get(0));
		List<String[]> usableLines = csv.subList(1, csv.size());
		Collection<Book> books = new ArrayList<>();
		for(String[] line : usableLines) {
			books.add(createBook(columns, line));
		}
		return books;
	}

	Book createBook(Map<String, Integer> columns, String[] line) {
		Book book = new Book();
		book.title = line[columns.get("Title")];
		book.author = line[columns.get("Author")];
		book.additionnalAuthors = line[columns.get("Additional Authors")];
		book.isbn10 = filterIsbn(line[columns.get("ISBN")]);
		book.isbn13 = filterIsbn(line[columns.get("ISBN13")]);
		book.rating = new Integer(line[columns.get("My Rating")]);
		book.average = new Float(line[columns.get("Average Rating")]);
		book.pages = 0;
		try {
			book.pages = new Integer(line[columns.get("Number of Pages")]);
		} catch(Exception e) {
		}
		book.initialPublication = line[columns.get("Original Publication Year")];
		String dateRead = line[columns.get("Date Read")];
		if(dateRead==null || dateRead.length()==0)
			book.read = null;
		else
			book.read = parseDate(dateRead);
		book.tags.addAll(Arrays.asList(line[columns.get("Bookshelves")].split(",")));
		book.tags.addAll(Arrays.asList(line[columns.get("Exclusive Shelf")].split(",")));
		book.review = line[columns.get("My Review")];
		book.notes = line[columns.get("Private Notes")];
		book.owns = new Integer(line[columns.get("Owned Copies")]);
		// adds special author tags
		if(book.getAuthors().size()>0) {
			for(String author : book.getAuthors()) {
				book.tags.add("author:"+author.replace(' ', '_'));
			}
		}
		// Add a tag for book score
		if(book.rating.floatValue()>0)
			book.tags.add("rating:"+book.rating);
		// Add a tag for book read year
		if(book.read!=null) {
			try {
				Date d = OUTPUT_FORMATTER.parse(book.read);
				book.tags.add("read_year:"+YEAR_FORMATTER.format(d));
				book.tags.add("read_month:"+MONTH_FORMATTER.format(d));
			} catch(Exception e) {
				if (logger.isLoggable(Level.WARNING)) {
					logger.log(Level.WARNING, "unable to parse read date "+book.read, e);
				}
			}
		}
		return book;
	}

	private String filterIsbn(String string) {
		if(string!=null && string.length()>2)
			return string.substring(2,string.length()-1);
		return null;
	}

	String parseDate(String text) {
		Date d;
		try {
			d = INPUT_FORMATTER.parse(text);
			return OUTPUT_FORMATTER.format(d);
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

	public List<String[]> loadCSV() throws Exception {
		WebClient client = new WebClient(com.gargoylesoftware.htmlunit.BrowserVersion.FIREFOX_3);
		client.setUseInsecureSSL(true);
		client.setJavaScriptEnabled(false);
		HtmlPage signIn = client.getPage("http://www.goodreads.com/user/sign_in");
		HtmlForm signInForm = signIn.getFormByName("sign_in");
		logger.log(Level.INFO, "logging in goodreads as "+username);
		signInForm.getInputByName("user[email]").setValueAttribute(username);
		signInForm.getInputByName("user[password]").setValueAttribute(password);
		HtmlPage signedIn = signInForm.getInputByName("next").click();
		String authenticationFailedMessage = "unable to sign in Goodreads using mail "+username+" and password "+password+". can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?";
		if(200==signedIn.getWebResponse().getStatusCode()) {
			if(signedIn.getUrl().equals(signIn.getUrl()))
				throw new AuthenticationFailedException(authenticationFailedMessage);
			logger.log(Level.INFO, "logged in ... downloading csv now ...");
			Page csv = client.getPage("http://www.goodreads.com/review_porter/goodreads_export.csv");
			// May cause memory error, but later ...
			String csvContent = csv.getWebResponse().getContentAsString();
			return splitIntoRows(csvContent);
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
	public void output(Mode mode, Collection<Book> books, FileObject output) {
		Collection<Book> filtered = Collections2.filter(books, new Predicate<Book>() {

			@Override
			public boolean apply(Book input) {
				return input.getTags().contains("read");
			}
		});
		OutputWriter writer = mode.getWriter();
		int index = 1;
		int size = filtered.size();
		for (Book book : filtered) {
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "writing book "+(index++)+"/"+size+" : "+book);
			}
			writer.write(book, output);
		}
	}

	@Override
	public Collection<Book> load() {
		try {
			logger.info("loading CSV data");
			List<String[]> rawData = loadCSV();
			logger.info("transforming that data into books");
			return buildBooksCollection(rawData);
		} catch(Exception e) {
			throw new UnableToBuildBookCollection(e);
		}
	}
}
package org.ndx.lifestream.goodreads;

import java.io.CharArrayReader;
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

import org.apache.http.protocol.HTTP;

import au.com.bytecode.opencsv.CSVReader;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class Goodreads {
	private static final Logger logger = Logger.getLogger(Goodreads.class.getName());

	private static final String REQUIRED_AUTHENTICATION = "page requires Goodreads compatible authentication";
	private static final String INPUT_DATE_FORMAT = "yyyy/MM/dd";
	private static final DateFormat INPUT_FORMATTER = new SimpleDateFormat(INPUT_DATE_FORMAT);
	private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final DateFormat OUTPUT_FORMATTER = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
	private static final String YEAR_DATE_FORMAT = "yyyy";
	private static final DateFormat YEAR_FORMATTER = new SimpleDateFormat(YEAR_DATE_FORMAT);
	private static final String MONTH_DATE_FORMAT = "MM";
	private static final DateFormat MONTH_FORMATTER = new SimpleDateFormat(MONTH_DATE_FORMAT);


	String username;
	String password;
	String outputFolder;
	String xsl = null;

	/**
	 * Run full export by using the infamous csv stuff
	 */
	public void run() throws Exception {
		List<String[]> csv = getCSV();
		Collection<Book> books = buildBooksCollection(csv);
		int index = 0;
		int size = books.size();
		for(Book book : books) {
			index++;
			if (logger.isLoggable(Level.INFO)) {
				logger.log(Level.INFO, "saving book $index/"+books.size()+" "+book.title);
			}
			if (logger.isLoggable(Level.SEVERE)) {
				logger.log(Level.SEVERE, "not yet implemented book.save()");
			}
		}
	}

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
		book.isbn10 = line[columns.get("ISBN")];
		book.isbn10 = book.isbn10.substring(2,book.isbn10.length()-1);
		book.isbn13 = line[columns.get("ISBN13")];
		book.isbn13 = book.isbn13.substring(2,book.isbn13.length()-1);
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
		book.tags.addAll(Arrays.asList(line[columns.get("Bookshelves")].split(" ")));
		book.review = line[columns.get("My Review")];
		book.notes = line[columns.get("Private Notes")];
		book.owns = new Integer(line[columns.get("Owned Copies")]);
		// adds special author tags
		book.tags.add("author:"+book.author);
		if(book.additionnalAuthors.length()>0) {
			for(String author : book.additionnalAuthors.split(",")) {
				book.tags.add("author:"+author);
			}
		}
		// Add a tag for book score
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

	public List<String[]> getCSV() throws Exception {
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
			CSVReader reader = new CSVReader(new CharArrayReader(csvContent.toCharArray()));
			try {
				return reader.readAll();
			} finally {
				reader.close();
			}
		} else {
			throw new AuthenticationFailedException(authenticationFailedMessage);
		}
	}

	public static void main(String[] args) throws Exception {
		logger.info("This is goodreads export script 0.1");
		logger.info("You like that script ? You already have a flattr account ? Then please go to http://flattr.com/thing/54246/Goodreads-backup-script !");
/*		def cli = new CliBuilder(usage:'groovy goodreads.groovy -u email@goodreads -p password -o outputFolder')
		cli.h(longOpt: 'help', 'provides full help and usage information')
		cli.u(longOpt: 'username', 'Sets goodreads mail address here', args:1, required:true)
		cli.p(longOpt: 'password', 'Unfortunatly one have to give its password to this little script', args:1, required:true)
		cli.o(longOpt: 'output', 'An eventually existing output folder, where all data will be output. Beware, if some data exists in that folder, it may be overwritten.', args:1, required:true)
		cli.x(longOpt: 'xsl', 'Gives an XSL stylmesheet URL that will be put in all generated files', args:1, required:false)
		def opt = cli.parse(args);
		if(!opt) {
			cli.usage
		} else {
			Goodreads app = new Goodreads();
			if(opt.h)
			cli.usage();
			app.username = opt.u;
			app.password = opt.p;
			app.outputFolder = opt.o
			app.xsl = opt.x
			app.run();
		}
*/	}
}
package org.ndx.lifestream.goodreads;

import java.io.CharArrayReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import groovy.lang.Grab;
import groovy.util.CliBuilder;
import groovy.xml.MarkupBuilder;
import groovy.xml.MarkupBuilderHelper;

class Goodreads {
	private static final String REQUIRED_AUTHENTICATION = "page requires Goodreads compatible authentication";
	private static final String INPUT_DATE_FORMAT = 'yyyy/MM/dd'
	private static final DateFormat INPUT_FORMATTER = new SimpleDateFormat(INPUT_DATE_FORMAT);
	private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
	private static final DateFormat OUTPUT_FORMATTER = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
	private static final String YEAR_DATE_FORMAT = "yyyy"
	private static final DateFormat YEAR_FORMATTER = new SimpleDateFormat(YEAR_DATE_FORMAT);
	private static final String MONTH_DATE_FORMAT = "MM"
	private static final DateFormat MONTH_FORMATTER = new SimpleDateFormat(MONTH_DATE_FORMAT);
	private String username;
	private String password;
	private String outputFolder;
	private String xsl = null;

	/**
	 * Run full export by using the infamous csv stuff
	 */
	public void run() throws Exception {
		List<String[]> csv = getCSV();
		Collection books = buildBooksCollection(csv);
		books.eachWithIndex {book, j ->
			def index = j+1;
			println "saving book $index/"+books.size()+" "+book.title
			book.save(outputFolder);
		}
	}

	Collection<Book> buildBooksCollection(List<String[]> csv) {
		def columns = getColumns(csv[0])
		def usableLines = csv[1..csv.size()-1]
		def books = [];
		for(line in usableLines) {
			Book book = new Book();
			book.title = line[columns['Title']]
			book.author = line[columns['Author']]
			book.additionnalAuthors = line[columns['Additional Authors']]
			book.isbn10 = line[columns['ISBN']]
			book.isbn10 = book.isbn10[2..book.isbn10.size()-1]
			book.isbn13 = line[columns['ISBN13']]
			book.isbn13 = book.isbn13[2..book.isbn13.size()-1]
			book.rating = new Integer(line[columns['My Rating']])
			book.average = new Float(line[columns['Average Rating']])
			book.pages = 0;
			try {
				book.pages = new Integer(line[columns['Number of Pages']])
			} catch(Exception e) {
			}
			book.initialPublication = line[columns['Original Publication Year']]
			def dateRead = line[columns['Date Read']];
			if(dateRead==null || dateRead.size()==0)
				book.read = null
			else
				book.read = parseDate(dateRead)
			book.tags = line[columns['Bookshelves']].tokenize(" ")
			book.review = line[columns['My Review']]
			book.notes = line[columns['Private Notes']]
			book.owns = line[columns['Owned Copies']] as Integer
			book.save = createSave();
			// adds special author tags
			book.tags << "author:"+book.author;
			if(book.additionnalAuthors.size()>0) {
				for(String author : book.additionnalAuthors.split(",")) {
					book.tags << "author:"+author;
				}
			}
			// Add a tag for book score
			book.tags << "rating:"+book.rating;
			// Add a tag for book read year
			if(book.read!=null) {
				Date d = OUTPUT_FORMATTER.parse(book.read);
				book.tags << "read_year:"+YEAR_FORMATTER.format(d);
				book.tags << "read_month:"+MONTH_FORMATTER.format(d);
			}

			books << book
		}
		return books;
	}

	def parseDate(String text) {
		Date d = INPUT_FORMATTER.parse(text);
		return OUTPUT_FORMATTER.format(d);
	}

	def getColumns(String[] firstLine) {
		def columns=[:]
		firstLine.eachWithIndex {key, index ->
			columns[key]=index;
		}
		return columns;
	}

	public List<String[]> getCSV() throws Exception {
		WebClient client = new WebClient(com.gargoylesoftware.htmlunit.BrowserVersion.FIREFOX_3);
		client.setUseInsecureSSL(true)
		client.setJavaScriptEnabled(false);
		HtmlPage signIn = client.getPage("http://www.goodreads.com/user/sign_in");
		HtmlForm signInForm = signIn.getFormByName("sign_in")
		signInForm.getInputByName("user[email]").setValueAttribute(username);
		signInForm.getInputByName("user[password]").setValueAttribute(password);
		HtmlPage signedIn = signInForm.getInputByName("next").click();
		if(200==signedIn.getWebResponse().getStatusCode()) {
			Page csv = client.getPage("http://www.goodreads.com/review/goodreads_export.csv");
			// May cause memory error, but later ...
			def csvContent = csv.getWebResponse().getContentAsString()
			CSVReader reader = new CSVReader(new CharArrayReader(csvContent.toCharArray()));
			return reader.readAll();
		} else {
			throw new RuntimeException("unable to sign in Goodreads using mail $username and password $password. can you check it by opening a browser at http://www.goodreads.com/user/sign_in ?");
		}
	}

	static void main(args) throws Exception {
		println "This is goodreads export script 0.1"
		println "You like that script ? You already have a flattr account ? Then please go to http://flattr.com/thing/54246/Goodreads-backup-script !"
		def cli = new CliBuilder(usage:'groovy goodreads.groovy -u email@goodreads -p password -o outputFolder')
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
	}
}
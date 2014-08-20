package org.ndx.lifestream.goodreads;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.goodreads.references.Reference;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.Freemarker;

import freemarker.template.Template;

/**
 * A class extracting content from the infamous Expando to a more "object" notion
 */
public class Book extends BookInfos implements Input {
	public static class ByWriteDate implements Comparator<Book> {

		@Override
		public int compare(Book o1, Book o2) {
			int returned = 0;
			returned = o1.getWriteDate().compareTo(o2.getWriteDate());
			if(returned==0) {
				returned = o1.getTitle().compareTo(o2.getTitle());
			}
			if(returned==0) {
				returned = o1.getId().compareTo(o2.getId());
			}
			return returned;
		}

	}
	private static Template book;
	private static final Logger logger = Logger.getLogger(Book.class.getName());
	private static final String MONTH_DATE_FORMAT = "MMM";
	private static final DateFormat MONTH_FORMATTER = new SimpleDateFormat(MONTH_DATE_FORMAT);
	private static DateFormat READ_FORMATTER = new SimpleDateFormat("yyyy/MM/dd");
	private static final String YEAR_DATE_FORMAT = "yyyy";
	private static final DateFormat YEAR_FORMATTER = new SimpleDateFormat(YEAR_DATE_FORMAT);

	static {
		book = BookInfos.loadTemplate("book.ftl");
	}
	public static String ratingAsTag(Number rating) {
		return "rated_"+rating;
	}
	public static String readMonthAsTag(Date d) {
		return "read_on_"+MONTH_FORMATTER.format(d);
	}
	public static String readYearAsTag(Date d) {
		return "read_in_"+YEAR_FORMATTER.format(d);
	}
	public Collection<Author> authors = new TreeSet<>();
	public Number average;
	public String bigImage;
	/**
	 * Source description (usually book's back text)
	 */
	public String description;
	public String initialPublication;
	private String isbn10;
	private String isbn13;
	/** private notes, as opposed to public #review */
	public String notes;
	public Integer owns;
	public Integer pages = 0;
	public Number rating;
	/**
	 * Date book is read
	 */
	public String read;

	private String review;
	/** Set of series this book is member of */
	private Set<Serie> series = new TreeSet<>();
	public String smallImage;
	/** All the tags assignated to this review */
	private SortedSet<String> tags = new TreeSet<String>();


	/**
	 * written text that will be filled using {@link #accept(OutputWriter)}
	 */
	protected String text;

	/**
	 * Book url on goodreads
	 */
	public String url;
	private Collection<Reference> references = new LinkedList<>();
	public Book() {
		setId(UUID.randomUUID().toString());
	}
	/**
	 * Will prepare rendered text by adding text from goodreads,
	 * and infos about container series
	 */
	@Override
	public void accept(OutputWriter writer) {
		review = resolveReferences(review, references, writer);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("series", generateLinks(series, writer));
		parameters.put("authors", generateLinks(authors, writer));
		parameters.put("book", this);
		text = Freemarker.render(book, parameters);
	}
	String resolveReferences(String review, Collection<Reference> references, OutputWriter writer) {
		// before to write entry, replace refrences by their values - if any
		for(Reference ref : references) {
			review = review.replace(ref.getId(), ref.getLink(writer, this));
		}
		return review;
	}
	public void addAllTags(Collection<String> list) {
		tags.addAll(list);
	}
	public void addAuthor(Author author) {
		authors.add(author);
		addTag(author.getAssociatedTag());
	}
	public void addSerie(Serie serie) {
		series.add(serie);
		tags.add(serie.getAssociatedTag());
	}
	public void addTag(String associatedTag) {
		tags.add(associatedTag);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn13 == null) {
			if (other.isbn13 != null)
				return false;
		} else if (!isbn13.equals(other.isbn13))
			return false;
		if (getTitle() == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!getTitle().equals(other.getTitle()))
			return false;
		return true;
	}
	private Collection<String> generateLinks(Collection<? extends Input> elements, OutputWriter writer) {
		Collection<String> returned = new LinkedList<>();
		for(Input s : elements) {
			returned.add(writer.link(this, s, s.getTitle()));
		}
		return returned;
	}

	@Override
	public Collection<String> getExpectedPath() {
		Collection<String> returned = new LinkedList<>();
		returned.add("goodreads");
		returned.add("books");
		if(isbn13!=null)
			returned.add(isbn13);
		else if(isbn10!=null)
			returned.add(isbn10);
		else
			returned.add(getTitle().replace(' ', '_'));
		return returned;
	}

	@Override
	public String getId() {
		return isbn13;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public Set<Serie> getSeries() {
		return series;
	}

	@Override
	public Collection<String> getTags() {
		return tags;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Date getWriteDate() {
		try {
			if(read==null || read.trim().length()==0)
				return TODAY;
			return Goodreads.OUTPUT_FORMATTER.parse(read);
		} catch (ParseException e) {
			return TODAY;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn13 == null) ? 0 : isbn13.hashCode());
		result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
		return result;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
		if(getId()==null) {
			setId(isbn10);
		}
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
		setId(isbn13);
	}

	public void setRead(Date date) {
		read = Goodreads.OUTPUT_FORMATTER.format(date);
		addTag(Book.readYearAsTag(date));
		addTag(Book.readMonthAsTag(date));
	}

	@Override
	public String toString() {
		return "title : "+getTitle()+" - basename : "+getExpectedPath();
	}
	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = super.getAdditionalHeaders();
		returned.put(Headers.BIG_IMAGE, bigImage);
		returned.put(Headers.SMALL_IMAGE, smallImage);
		returned.put(Headers.SOURCE, url);
		return returned;
	}
	public String getReview() {
		return review;
	}
	/**
	 * Set review (yup, crazy, I know). When doing so, #references map is populated with required references
	 * @param review
	 */
	public void setReview(String review) {
		this.review = review;
	}
	public boolean add(Reference e) {
		return references.add(e);
	}
	public boolean addAll(Collection<? extends Reference> c) {
		return references.addAll(c);
	}
	public Collection<Reference> getReferences() {
		return references;
	}
	public void setReferences(Collection<Reference> references) {
		this.references = references;
	}
}
package org.ndx.lifestream.goodreads;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.StringTemplateUtils;
import org.stringtemplate.v4.ST;

import com.google.common.collect.Maps;

/**
 * A class extracting content from the infamous Expando to a more "object" notion
 */
public class Book extends BookInfos implements Input {
	private static final Logger logger = Logger.getLogger(Book.class.getName());
	private static DateFormat READ_FORMATTER = new SimpleDateFormat("yyyy/MM/dd");
	private static ST book;

	static {
		try {
			book = goodreadsGroup.getInstanceOf("book");
		} catch(RuntimeException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.log(Level.SEVERE, "unable to load standard book template", e);
			}
			throw e;
		}
	}
	public String title;
	public Collection<String> authors = new ArrayList<>();
	private String isbn10;
	private String isbn13;
	public Number rating;
	public Number average;
	public Integer pages = 0;
	public String initialPublication;
	public SortedSet<String> tags = new TreeSet<String>();
	public String review;
	/** private notes, as opposed to public #review */
	public String notes;
	public Integer owns;
	/**
	 * Date book is read
	 */
	public String read;
	private Set<Serie> series = new TreeSet<>();
	/**
	 * written text that will be filled using {@link #accept(OutputWriter)}
	 */
	protected String text;

	public String image;
	/**
	 * Source description (usually book's back text)
	 */
	public String description;
	public String smallImage;
	/**
	 * Book url on goodreads
	 */
	public String url;


	public Book() {
		setId(UUID.randomUUID().toString());
	}

	@Override
	public String getText() {
		return text;
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
			returned.add(title.replace(' ', '_'));
		return returned;
	}
	@Override
	public String toString() {
		return "title : "+title+" - basename : "+getExpectedPath();
	}
	@Override
	public Collection<String> getTags() {
		return tags;
	}
	@Override
	public Date getWriteDate() {
		try {
			if(read==null)
				return new Date();
			return Goodreads.OUTPUT_FORMATTER.parse(read);
		} catch (ParseException e) {
			return new Date();
		}
	}
	@Override
	public String getTitle() {
		return title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn13 == null) ? 0 : isbn13.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String getId() {
		return isbn13;
	}
	public void addSerie(Serie serie) {
		series.add(serie);
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
		if(getId()==null) {
			setId(isbn10);
		}
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
		setId(isbn13);
	}

	public Set<Serie> getSeries() {
		return series;
	}

	/**
	 * Will prepare rendered text by adding text from goodreads,
	 * and infos about container series
	 */
	@Override
	public void accept(OutputWriter writer) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("series", generateSeriesLinks(writer));
		parameters.put("book", this);
		text = StringTemplateUtils.applyParametersToTemplate(book, parameters);
	}

	private Collection<String> generateSeriesLinks(OutputWriter writer) {
		Collection<String> returned = new LinkedList<>();
		for(Serie s : series) {
			returned.add(writer.link(this, s, s.title));
		}
		return returned;
	}

	public static String authorAsTag(String author) {
		return author.replace(' ', '_');
	}

}
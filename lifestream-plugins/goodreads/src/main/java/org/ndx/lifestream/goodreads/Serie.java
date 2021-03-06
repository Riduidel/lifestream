package org.ndx.lifestream.goodreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Input.Headers;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.output.Freemarker;

import freemarker.template.Template;

public class Serie extends BookInfos implements Input, Comparable<Serie> {
	private static Template serie;

	static {
		serie = BookInfos.loadTemplate("serie.ftl");
	}

	/**
	 * First cycle book date is set as default to today and pushed in the past when books are added
	 */
	private Date firstBookDate = BookInfos.TODAY;
	public String description;

	private Map<Book, String> books = new HashMap<>();
	private String text;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		return Arrays.asList("goodreads", "series", FileNameUtils.simplify(getTitle()));
	}

	@Override
	public Collection<String> getTags() {
		return Arrays.asList("serie");
	}

	/**
	 * There is no date per se for series
	 */
	@Override
	public Date getWriteDate() {
		return firstBookDate;
	}

	/**
	 * Add book to serie.
	 * If book read date is anterior to serie date, book read date will be used instead
	 */
	public void setBook(String number, Book source) {
		books.put(source, number);
		source.addSerie(this);
		// Each time a book is added, its date is used as first book date if anterior to previous one
		firstBookDate = firstBookDate.compareTo(source.getWriteDate())<0 ? firstBookDate : source.getWriteDate();
	}

	@Override
	public int compareTo(Serie o) {
		return getTitle().compareTo(o.getTitle());
	}


	/**
	 * Will prepare rendered text by adding text from goodreads,
	 * and infos about container series
	 */
	@Override
	public void accept(OutputWriter writer) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("serie", this);
		parameters.put("books", createBooksList(writer));
		text = Freemarker.render(serie, parameters);
	}

	/**
	 * As a covnention, numbered books will be put in a first list and unnumbered ones in another.
	 * Then those lists will be merged
	 * @param writer
	 * @return
	 */
	private List<String> createBooksList(OutputWriter writer) {
		List<String> returned = new ArrayList<>();
		Map<Integer, Book> numbered = new TreeMap<>();
		List<Book> unnumbered = new ArrayList<>();
		for(Map.Entry<Book, String> entry : books.entrySet()) {
			try {
				int number = Integer.parseInt(entry.getValue());
				numbered.put(number, entry.getKey());
			} catch(NumberFormatException e) {
				unnumbered.add(entry.getKey());
			}
		}
		// now they've been filtered, create strings and add them
		for(Book b : numbered.values()) {
			returned.add(writer.link(this, b, b.getTitle()));
		}
		for(Book b : unnumbered) {
			returned.add(writer.link(this, b, b.getTitle()));
		}
		return returned;
	}

	@Override
	public String toString() {
		return "Serie [title=" + getTitle() + ", getExpectedPath()="
				+ getExpectedPath() + "]";
	}

	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = super.getAdditionalHeaders();
		returned.put(Headers.STYLE, returned.get(Headers.STYLE)+" "+Headers.Styles.NO_INDEX);
		returned.put(Headers.SOURCE, String.format("https://www.goodreads.com/series/%s", getId()));
		return returned;
	}
}

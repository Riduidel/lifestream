package org.ndx.lifestream.goodreads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.stringtemplate.v4.ST;

public class Serie extends BookInfos implements Input, Comparable<Serie> {
	private static ST serie;
	
	static {
		serie = goodreadsGroup.getInstanceOf("serie");
	}

	private Date firstBookDate = new Date();
	public String title;
	public String description;
	
	private Map<Book, String> books = new HashMap<>();
	private String text;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		return Arrays.asList("series", title.replace(' ', '_'));
	}

	@Override
	public Collection<String> getTags() {
		return Arrays.asList(getSerieTag(title));
	}

	public static String getSerieTag(String serieName) {
		return "serie:"+serieName;
	}

	/**
	 * There is no date per se for series
	 */
	@Override
	public Date getWriteDate() {
		return firstBookDate;
	}

	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * Add book to serie.
	 * If book read date is anterior to serie date, book read date will be used instead
	 * @param number
	 * @param source
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
		serie.add("serie", this);
		text  = serie.render();
		serie.remove("serie");
	}

	@Override
	public String toString() {
		return "Serie [title=" + title + ", getExpectedPath()="
				+ getExpectedPath() + "]";
	}
}

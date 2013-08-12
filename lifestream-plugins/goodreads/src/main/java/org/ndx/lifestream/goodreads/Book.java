package org.ndx.lifestream.goodreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ndx.lifestream.rendering.model.Input;

/**
 * A class extracting content from the infamous Expando to a more "object" notion
 */
public class Book implements Input {
	public String title;
	public String author;
	public String additionnalAuthors;
	public String isbn10;
	public String isbn13;
	public Number rating;
	public Number average;
	public Integer pages = 0;
	public String initialPublication;
	public String dateRead;
	public SortedSet<String> tags = new TreeSet<String>();
	public String review;
	/** private notes, as opposed to public #review */
	public String notes;
	public Integer owns;
	/**
	 * Date book is read
	 */
	public String read;
	public Collection<String> getAuthors() {
		List<String> returned = new ArrayList<>();
		returned.add(author);
		for(String additional : additionnalAuthors.split(",")) {
			returned.add(additional.trim());
		}
		return returned;
	}
	@Override
	public String getText() {
		return review;
	}
	@Override
	public String getBasename() {
		if(isbn13!=null)
			return isbn13;
		else if(isbn10!=null)
			return isbn10;
		else
			return title.replace(' ', '_');
	}
	@Override
	public String toString() {
		return "title : "+title+" - basename : "+getBasename();
	}

}
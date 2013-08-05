package org.ndx.lifestream.goodreads;

import java.util.Collection;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A class extracting content from the infamous Expando to a more "object" notion
 */
public class Book {
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

}
package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.rendering.OutputWriter;

public interface Reference {
	public String getId();
	/**
	 * Get object to which this reference refers
	 */
	public BookInfos getDestination();
	public void setDestination(BookInfos found);
	/**
	 * Get reference text
	 */
	public String getText();


	/**
	 * Get link for this reference using given writer and from given book
	 * @return a link for that reference - if this reference has a destination !
	 */
	public String getLink(OutputWriter writer, Book source);
}

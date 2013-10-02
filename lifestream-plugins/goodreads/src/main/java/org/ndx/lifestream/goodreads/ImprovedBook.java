package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.rendering.OutputWriter;
import org.stringtemplate.v4.ST;

public class ImprovedBook extends Book {
	private static ST book;
	
	static {
		book = goodreadsGroup.getInstanceOf("bookWithImages");
	}
	String imageUrl;

	public ImprovedBook(Book raw) {
		this.additionnalAuthors = raw.additionnalAuthors;
		this.author = raw.author;
		this.average = raw.average;
		this.initialPublication = raw.initialPublication;
		this.setIsbn10(raw.getIsbn10());
		this.setIsbn13(raw.getIsbn13());
		this.notes = raw.notes;
		this.owns = raw.owns;
		this.pages = raw.pages;
		this.rating = raw.rating;
		this.read = raw.read;
		this.review = raw.review;
		this.tags = raw.tags;
		this.title = raw.title;
	}
	@Override
	public void accept(OutputWriter writer) {
		book.add("book", this);
		text  = book.render();
		book.remove("book");
	}
}

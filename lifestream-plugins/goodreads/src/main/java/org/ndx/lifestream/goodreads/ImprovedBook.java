package org.ndx.lifestream.goodreads;

public class ImprovedBook extends Book {
	String imageUrl;

	public ImprovedBook(Book raw) {
		this.additionnalAuthors = raw.additionnalAuthors;
		this.author = raw.author;
		this.average = raw.average;
		this.dateRead = raw.dateRead;
		this.initialPublication = raw.initialPublication;
		this.isbn10 = raw.isbn10;
		this.isbn13 = raw.isbn13;
		this.notes = raw.notes;
		this.owns = raw.owns;
		this.pages = raw.pages;
		this.rating = raw.rating;
		this.read = raw.read;
		this.review = raw.review;
		this.tags = raw.tags;
		this.title = raw.title;
	}
}

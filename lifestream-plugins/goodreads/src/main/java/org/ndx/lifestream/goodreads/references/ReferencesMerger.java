package org.ndx.lifestream.goodreads.references;

import java.util.Map;
import java.util.concurrent.Callable;

import org.ndx.lifestream.goodreads.Book;

public class ReferencesMerger {

	private Book book;
	private Map<String, Reference> allReferences;

	public ReferencesMerger(Book b, Map<String, Reference> references) {
		this.book = b;
		this.allReferences = references;
	}

	public void call() {
		new References().load(book, allReferences);
	}

}

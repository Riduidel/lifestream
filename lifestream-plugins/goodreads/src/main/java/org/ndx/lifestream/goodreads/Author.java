package org.ndx.lifestream.goodreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input.Headers;
import org.ndx.lifestream.rendering.output.StringTemplateUtils;
import org.stringtemplate.v4.ST;

public class Author extends BookInfos implements Comparable<Author>{
	private static ST author;

	static {
		author = goodreadsGroup.getInstanceOf("author");
	}


	private String name;
	private Set<Book> books = new TreeSet<>(new Book.ByWriteDate());
	private String text;
	private Date date = TODAY;
	private String image;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		return Arrays.asList("goodreads", "authors", getId());
	}

	@Override
	public Collection<String> getTags() {
		return Arrays.asList(getAssociatedTag());
	}

	String getAssociatedTag() {
		return "by_"+forTag(name);
	}

	@Override
	public Date getWriteDate() {
		return date;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public void accept(OutputWriter writer) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("author", this);
		parameters.put("books", createBooksList(writer));
		text = StringTemplateUtils.applyParametersToTemplate(author, parameters);
	}

	/**
	 * Create a list of links from this author to its written works
	 * @param writer
	 * @return
	 */
	private List<String> createBooksList(OutputWriter writer) {
		List<String> returned = new ArrayList<>();
		for(Book b : books) {
			returned.add(writer.link(this, b, b.getTitle()));
		}
		return returned;
	}

	public void setName(String authorName) {
		this.name = authorName;
	}

	public void addBook(Book book) {
		this.books.add(book);
		if(book.getWriteDate().compareTo(date)<0)
			date = book.getWriteDate();
		book.addAuthor(this);
	}

	@Override
	public int compareTo(Author o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = new TreeMap<>();
		returned.put(Headers.BIG_IMAGE, image);
		return returned;
	}

}

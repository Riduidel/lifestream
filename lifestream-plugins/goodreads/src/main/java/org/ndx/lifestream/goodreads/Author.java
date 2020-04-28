package org.ndx.lifestream.goodreads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.output.Freemarker;

import freemarker.template.Template;

public class Author extends BookInfos implements Comparable<Author>{
	private static Template author;

	static {
		author = BookInfos.loadTemplate("author.ftl");
	}


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
		return "by_"+forTag(getTitle());
	}

	@Override
	public Date getWriteDate() {
		return date;
	}

	public void setName(String name) {
		setTitle(name);
	}

	public String getName() {
		return getTitle();
	}

	@Override
	public void accept(OutputWriter writer) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("author", this);
		parameters.put("books", createBooksList(writer));
		text = Freemarker.render(author, parameters);
	}

	/**
	 * Create a list of links from this author to its written works
	 * @param writer
	 * @return
	 */
	private Collection<String> createBooksList(OutputWriter writer) {
		Set<String> returned = new TreeSet<>();
		for(Book b : books) {
			returned.add(writer.link(this, b, b.getTitle()));
		}
		return returned;
	}


	public void addBook(Book book) {
		this.books.add(book);
		if(book.getWriteDate().compareTo(date)<0)
			date = book.getWriteDate();
		book.addAuthor(this);
	}

	@Override
	public int compareTo(Author o) {
		return getTitle().compareTo(o.getTitle());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [name=");
		builder.append(getTitle());
		builder.append("]");
		return builder.toString();
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = super.getAdditionalHeaders();
		returned.put(Headers.BIG_IMAGE, image);
		returned.put(Headers.STYLE, returned.get(Headers.STYLE)+" "+Headers.Styles.NO_INDEX);
		returned.put(Headers.SOURCE, String.format("https://www.goodreads.com/author/show/%s", getId()));
		return returned;
	}

}

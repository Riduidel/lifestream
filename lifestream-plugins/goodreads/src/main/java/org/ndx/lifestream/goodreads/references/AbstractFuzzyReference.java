package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.rendering.OutputWriter;

public abstract class AbstractFuzzyReference implements Reference {
	private final String referenceText;
	private final String searchedText;
	private BookInfos destination;
	public AbstractFuzzyReference(String referenceText, String searchedText) {
		super();
		this.referenceText = referenceText;
		this.searchedText = searchedText;
	}
	public String getReferenceText() {
		return referenceText;
	}
	public String getSearchedText() {
		return searchedText;
	}
	public String getId() {
		return referenceText;
	}
	/**
	 * @return the destination
	 * @category getter
	 * @category destination
	 */
	public BookInfos getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 * @category setter
	 * @category destination
	 */
	public void setDestination(BookInfos destination) {
		this.destination = destination;
	}

	public String getText() {
		return getSearchedText();
	}
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName()).append(" [");
		if (referenceText != null) {
			builder.append("referenceText=");
			builder.append(referenceText);
			builder.append(", ");
		}
		if (searchedText != null) {
			builder.append("searchedText=");
			builder.append(searchedText);
			builder.append(", ");
		}
		if (destination != null) {
			builder.append("destination=");
			builder.append(destination);
		}
		builder.append("]");
		return builder.toString();
	}

	public String getLink(OutputWriter writer, Book source) {
		String link = getText();
		if(getDestination()!=null) {
			link = writer.link(source, getDestination(), String.format("<strike>%s</strike> %s", link, getDestination().getTitle()));
		}
		return link;
	}
}

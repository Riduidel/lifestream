package org.ndx.lifestream.goodreads.references;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.rendering.OutputWriter;

public abstract class AbstractDirectReference implements Reference {
	private static Pattern referencePattern = Pattern.compile("\\[([^|]+)(|([^|]+))*\\]");
	private final List<String> referenceElements;
	private String referenceText;

	private BookInfos destination;

	public AbstractDirectReference(String referenceText, String referenceTextPart) {
		this.referenceText = referenceText;
		referenceElements = Arrays.asList(referenceTextPart.split("\\|"));
	}

	public List<String> getReferenceElements() {
		return referenceElements;
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
		if (destination != null) {
			builder.append("destination=");
			builder.append(destination);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String getLink(OutputWriter writer, Book source) {
		String link = getText();
		if(getDestination()!=null) {
			link = writer.link(source, getDestination(), getDestination().getTitle());
		}
		return link;
	}
}

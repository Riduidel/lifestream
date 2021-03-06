package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.Author;

public class DirectAuthorReference extends AbstractDirectReference implements Reference {

	public DirectAuthorReference(String referenceText, String referenceTextPart) {
		super(referenceText, referenceTextPart);
	}

	public String getAuthorId() {
		return getReferenceElements().get(1);
	}

	public String getName() {
		return getReferenceElements().get(2);
	}

	/**
	 * @see org.ndx.lifestream.goodreads.references.AbstractDirectReference#getDestination()
	 */
	@Override
	public Author getDestination() {
		return (Author) super.getDestination();
	}

	@Override
	public String getText() {
		return getAuthorId();
	}
}

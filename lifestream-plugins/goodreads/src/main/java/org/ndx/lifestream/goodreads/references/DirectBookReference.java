package org.ndx.lifestream.goodreads.references;

public class DirectBookReference extends AbstractDirectReference implements Reference {

	public DirectBookReference(String referenceText, String referenceTextPart) {
		super(referenceText, referenceTextPart);
	}

	public String getBookId() {
		return getReferenceElements().get(1);
	}

	public String getTitle() {
		return getReferenceElements().get(2);
	}

	public String getText() {
		return getReferenceElements().get(0);
	}

}

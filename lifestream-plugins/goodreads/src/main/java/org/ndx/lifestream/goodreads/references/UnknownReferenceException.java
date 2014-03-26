package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.GoodreadsException;

public class UnknownReferenceException extends GoodreadsException {

	public UnknownReferenceException() {
	}

	public UnknownReferenceException(String message) {
		super(message);
	}

	public UnknownReferenceException(Throwable cause) {
		super(cause);
	}

	public UnknownReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownReferenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package org.ndx.lifestream.goodreads;

public class UnableToLocateWorkIdException extends GoodreadsException {

	public UnableToLocateWorkIdException() {
	}

	public UnableToLocateWorkIdException(String message) {
		super(message);
	}

	public UnableToLocateWorkIdException(Throwable cause) {
		super(cause);
	}

	public UnableToLocateWorkIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToLocateWorkIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

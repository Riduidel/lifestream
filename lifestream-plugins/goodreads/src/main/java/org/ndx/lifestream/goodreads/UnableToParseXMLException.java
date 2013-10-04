package org.ndx.lifestream.goodreads;

public class UnableToParseXMLException extends GoodreadsException {

	public UnableToParseXMLException() {
	}

	public UnableToParseXMLException(String message) {
		super(message);
	}

	public UnableToParseXMLException(Throwable cause) {
		super(cause);
	}

	public UnableToParseXMLException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToParseXMLException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

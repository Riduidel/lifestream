package org.ndx.lifestream.goodreads;

public class UnableToConfigureGoodreadsException extends GoodreadsException {

	public UnableToConfigureGoodreadsException() {
		super();
	}

	public UnableToConfigureGoodreadsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnableToConfigureGoodreadsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToConfigureGoodreadsException(String message) {
		super(message);
	}

	public UnableToConfigureGoodreadsException(Throwable cause) {
		super(cause);
	}

}

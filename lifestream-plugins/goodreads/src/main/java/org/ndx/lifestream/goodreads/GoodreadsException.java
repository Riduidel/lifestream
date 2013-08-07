package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class GoodreadsException extends LifestreamException {

	public GoodreadsException() {
	}

	public GoodreadsException(String message) {
		super(message);
	}

	public GoodreadsException(Throwable cause) {
		super(cause);
	}

	public GoodreadsException(String message, Throwable cause) {
		super(message, cause);
	}

	public GoodreadsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

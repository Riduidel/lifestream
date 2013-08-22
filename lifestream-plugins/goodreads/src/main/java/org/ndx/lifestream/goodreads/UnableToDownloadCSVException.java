package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToDownloadCSVException extends LifestreamException {

	public UnableToDownloadCSVException() {
	}

	public UnableToDownloadCSVException(String message) {
		super(message);
	}

	public UnableToDownloadCSVException(Throwable cause) {
		super(cause);
	}

	public UnableToDownloadCSVException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDownloadCSVException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

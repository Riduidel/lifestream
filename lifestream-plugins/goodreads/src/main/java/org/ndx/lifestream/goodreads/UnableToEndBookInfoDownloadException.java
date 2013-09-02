package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToEndBookInfoDownloadException extends LifestreamException {

	public UnableToEndBookInfoDownloadException() {
	}

	public UnableToEndBookInfoDownloadException(String message) {
		super(message);
	}

	public UnableToEndBookInfoDownloadException(Throwable cause) {
		super(cause);
	}

	public UnableToEndBookInfoDownloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToEndBookInfoDownloadException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

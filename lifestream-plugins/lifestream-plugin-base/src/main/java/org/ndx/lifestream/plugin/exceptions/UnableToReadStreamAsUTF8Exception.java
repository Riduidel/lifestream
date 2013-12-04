package org.ndx.lifestream.plugin.exceptions;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToReadStreamAsUTF8Exception extends LifestreamException {

	public UnableToReadStreamAsUTF8Exception() {
	}

	public UnableToReadStreamAsUTF8Exception(String message) {
		super(message);
	}

	public UnableToReadStreamAsUTF8Exception(Throwable cause) {
		super(cause);
	}

	public UnableToReadStreamAsUTF8Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToReadStreamAsUTF8Exception(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

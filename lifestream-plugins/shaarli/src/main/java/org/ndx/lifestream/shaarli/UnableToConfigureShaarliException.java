package org.ndx.lifestream.shaarli;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToConfigureShaarliException extends LifestreamException {

	public UnableToConfigureShaarliException() {
	}

	public UnableToConfigureShaarliException(String message) {
		super(message);
	}

	public UnableToConfigureShaarliException(Throwable cause) {
		super(cause);
	}

	public UnableToConfigureShaarliException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToConfigureShaarliException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package org.ndx.lifestream.utils.transform;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToConfigureTransformerException extends LifestreamException {

	public UnableToConfigureTransformerException() {
	}

	public UnableToConfigureTransformerException(String message) {
		super(message);
	}

	public UnableToConfigureTransformerException(Throwable cause) {
		super(cause);
	}

	public UnableToConfigureTransformerException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToConfigureTransformerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

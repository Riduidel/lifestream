package org.ndx.lifestream.utils.exception;

/**
 * Base class for all exception, allow not to write those bothering throws clauses
 * @author ndx
 *
 */
public abstract class LifestreamException extends RuntimeException {

	public LifestreamException() {
	}

	public LifestreamException(String message) {
		super(message);
	}

	public LifestreamException(Throwable cause) {
		super(cause);
	}

	public LifestreamException(String message, Throwable cause) {
		super(message, cause);
	}

	public LifestreamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

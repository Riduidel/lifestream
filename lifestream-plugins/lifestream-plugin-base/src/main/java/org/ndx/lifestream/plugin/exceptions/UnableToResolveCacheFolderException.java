package org.ndx.lifestream.plugin.exceptions;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToResolveCacheFolderException extends LifestreamException {

	public UnableToResolveCacheFolderException() {
	}

	public UnableToResolveCacheFolderException(String message) {
		super(message);
	}

	public UnableToResolveCacheFolderException(Throwable cause) {
		super(cause);
	}

	public UnableToResolveCacheFolderException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToResolveCacheFolderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

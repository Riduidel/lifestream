package org.ndx.lifestream.rendering.output;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToLocateUserDirException extends LifestreamException {

	public UnableToLocateUserDirException() {
	}

	public UnableToLocateUserDirException(String message) {
		super(message);
	}

	public UnableToLocateUserDirException(Throwable cause) {
		super(cause);
	}

	public UnableToLocateUserDirException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToLocateUserDirException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

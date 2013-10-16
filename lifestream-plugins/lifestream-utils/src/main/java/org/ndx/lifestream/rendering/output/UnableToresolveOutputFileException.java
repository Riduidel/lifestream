package org.ndx.lifestream.rendering.output;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToresolveOutputFileException extends LifestreamException {

	public UnableToresolveOutputFileException() {
	}

	public UnableToresolveOutputFileException(String message) {
		super(message);
	}

	public UnableToresolveOutputFileException(Throwable cause) {
		super(cause);
	}

	public UnableToresolveOutputFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToresolveOutputFileException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

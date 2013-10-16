package org.ndx.lifestream.rendering.output;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToRenderException extends LifestreamException {

	public UnableToRenderException() {
	}

	public UnableToRenderException(String message) {
		super(message);
	}

	public UnableToRenderException(Throwable cause) {
		super(cause);
	}

	public UnableToRenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToRenderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

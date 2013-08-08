package org.ndx.lifestream.utils.transform;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToTransformToMarkdownException extends LifestreamException {

	public UnableToTransformToMarkdownException() {
		super();
	}

	public UnableToTransformToMarkdownException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnableToTransformToMarkdownException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToTransformToMarkdownException(String message) {
		super(message);
	}

	public UnableToTransformToMarkdownException(Throwable cause) {
		super(cause);
	}
	
}

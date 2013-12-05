package org.ndx.lifestream.shaarli;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToTransformFeedIntoEntriesCollection extends LifestreamException {

	public UnableToTransformFeedIntoEntriesCollection() {
	}

	public UnableToTransformFeedIntoEntriesCollection(String message) {
		super(message);
	}

	public UnableToTransformFeedIntoEntriesCollection(Throwable cause) {
		super(cause);
	}

	public UnableToTransformFeedIntoEntriesCollection(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToTransformFeedIntoEntriesCollection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package org.ndx.lifestream.wordpress;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToTransformStreamInPostCollectionException extends
		LifestreamException {

	public UnableToTransformStreamInPostCollectionException() {
	}

	public UnableToTransformStreamInPostCollectionException(String message) {
		super(message);
	}

	public UnableToTransformStreamInPostCollectionException(Throwable cause) {
		super(cause);
	}

	public UnableToTransformStreamInPostCollectionException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public UnableToTransformStreamInPostCollectionException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

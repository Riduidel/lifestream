package org.ndx.lifestream.configuration;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToConfigureCacheException extends LifestreamException {

	public UnableToConfigureCacheException() {
	}

	public UnableToConfigureCacheException(String message) {
		super(message);
	}

	public UnableToConfigureCacheException(Throwable cause) {
		super(cause);
	}

	public UnableToConfigureCacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToConfigureCacheException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

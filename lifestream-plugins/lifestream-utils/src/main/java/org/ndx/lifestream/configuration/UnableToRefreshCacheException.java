package org.ndx.lifestream.configuration;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToRefreshCacheException extends LifestreamException {

	public UnableToRefreshCacheException() {
	}

	public UnableToRefreshCacheException(String message) {
		super(message);
	}

	public UnableToRefreshCacheException(Throwable cause) {
		super(cause);
	}

	public UnableToRefreshCacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToRefreshCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

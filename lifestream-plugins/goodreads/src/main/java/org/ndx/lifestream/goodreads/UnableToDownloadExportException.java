package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToDownloadExportException extends LifestreamException {

	public UnableToDownloadExportException() {
	}

	public UnableToDownloadExportException(String message) {
		super(message);
	}

	public UnableToDownloadExportException(Throwable cause) {
		super(cause);
	}

	public UnableToDownloadExportException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDownloadExportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

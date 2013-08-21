package org.ndx.lifestream.wordpress;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToDownloadXMLException extends LifestreamException {

	public UnableToDownloadXMLException() {
	}

	public UnableToDownloadXMLException(String message) {
		super(message);
	}

	public UnableToDownloadXMLException(Throwable cause) {
		super(cause);
	}

	public UnableToDownloadXMLException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDownloadXMLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

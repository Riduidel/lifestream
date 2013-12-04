package org.ndx.lifestream.plugin.exceptions;

import org.ndx.lifestream.utils.exception.LifestreamException;

/**
 * Thrown when downloading content was impossible for an unspecified reason
 * @author ndx
 *
 */
public class UnableToDownloadContentException extends LifestreamException {

	public UnableToDownloadContentException() {
	}

	public UnableToDownloadContentException(String message) {
		super(message);
	}

	public UnableToDownloadContentException(Throwable cause) {
		super(cause);
	}

	public UnableToDownloadContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDownloadContentException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

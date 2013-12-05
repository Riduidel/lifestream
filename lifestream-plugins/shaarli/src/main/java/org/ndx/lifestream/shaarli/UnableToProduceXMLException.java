package org.ndx.lifestream.shaarli;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToProduceXMLException extends LifestreamException {

	public UnableToProduceXMLException() {
	}

	public UnableToProduceXMLException(String message) {
		super(message);
	}

	public UnableToProduceXMLException(Throwable cause) {
		super(cause);
	}

	public UnableToProduceXMLException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToProduceXMLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package org.ndx.lifestream.utils.web;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class SeleniumException extends LifestreamException {

	public SeleniumException() {
	}

	public SeleniumException(String message) {
		super(message);
	}

	public SeleniumException(Throwable cause) {
		super(cause);
	}

	public SeleniumException(String message, Throwable cause) {
		super(message, cause);
	}
}

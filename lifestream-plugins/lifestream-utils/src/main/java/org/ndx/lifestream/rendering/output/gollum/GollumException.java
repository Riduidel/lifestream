package org.ndx.lifestream.rendering.output.gollum;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class GollumException extends LifestreamException {

	public GollumException() {
	}

	public GollumException(String message) {
		super(message);
	}

	public GollumException(Throwable cause) {
		super(cause);
	}

	public GollumException(String message, Throwable cause) {
		super(message, cause);
	}

	public GollumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package org.ndx.lifestream.rendering.output.jekyll;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class JekykllException extends LifestreamException {

	public JekykllException() {
	}

	public JekykllException(String message) {
		super(message);
	}

	public JekykllException(Throwable cause) {
		super(cause);
	}

	public JekykllException(String message, Throwable cause) {
		super(message, cause);
	}

	public JekykllException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

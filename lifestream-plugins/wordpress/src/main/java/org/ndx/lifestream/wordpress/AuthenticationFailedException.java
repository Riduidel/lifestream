package org.ndx.lifestream.wordpress;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class AuthenticationFailedException extends LifestreamException {

	public AuthenticationFailedException() {
	}

	public AuthenticationFailedException(String message) {
		super(message);
	}

	public AuthenticationFailedException(Throwable cause) {
		super(cause);
	}

	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

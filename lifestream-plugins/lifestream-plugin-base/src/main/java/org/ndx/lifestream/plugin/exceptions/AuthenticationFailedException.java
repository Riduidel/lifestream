package org.ndx.lifestream.plugin.exceptions;

import org.ndx.lifestream.utils.exception.LifestreamException;

/**
 * Thrown when authentication to a service failed
 * @author ndx
 *
 */
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

	public AuthenticationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

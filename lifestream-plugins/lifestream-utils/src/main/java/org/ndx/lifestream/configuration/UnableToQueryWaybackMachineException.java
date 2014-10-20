package org.ndx.lifestream.configuration;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToQueryWaybackMachineException extends LifestreamException {

	public UnableToQueryWaybackMachineException() {
	}

	public UnableToQueryWaybackMachineException(String message) {
		super(message);
	}

	public UnableToQueryWaybackMachineException(Throwable cause) {
		super(cause);
	}

	public UnableToQueryWaybackMachineException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToQueryWaybackMachineException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

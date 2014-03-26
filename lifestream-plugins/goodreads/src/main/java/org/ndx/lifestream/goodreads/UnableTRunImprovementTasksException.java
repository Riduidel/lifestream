package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableTRunImprovementTasksException extends LifestreamException {

	public UnableTRunImprovementTasksException() {
	}

	public UnableTRunImprovementTasksException(String message) {
		super(message);
	}

	public UnableTRunImprovementTasksException(Throwable cause) {
		super(cause);
	}

	public UnableTRunImprovementTasksException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableTRunImprovementTasksException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

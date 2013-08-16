package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class UnableToBuildBookCollection extends LifestreamException {

	public UnableToBuildBookCollection() {
	}

	public UnableToBuildBookCollection(String message) {
		super(message);
	}

	public UnableToBuildBookCollection(Throwable cause) {
		super(cause);
	}

	public UnableToBuildBookCollection(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToBuildBookCollection(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

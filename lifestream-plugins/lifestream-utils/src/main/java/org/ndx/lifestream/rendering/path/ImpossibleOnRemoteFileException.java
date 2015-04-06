package org.ndx.lifestream.rendering.path;

import org.ndx.lifestream.utils.exception.LifestreamException;

public class ImpossibleOnRemoteFileException extends LifestreamException {

	public ImpossibleOnRemoteFileException() {
	}

	public ImpossibleOnRemoteFileException(String message) {
		super(message);
	}

	public ImpossibleOnRemoteFileException(Throwable cause) {
		super(cause);
	}

	public ImpossibleOnRemoteFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImpossibleOnRemoteFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

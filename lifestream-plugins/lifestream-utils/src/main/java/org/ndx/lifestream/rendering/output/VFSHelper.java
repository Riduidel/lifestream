package org.ndx.lifestream.rendering.output;

import java.util.logging.Logger;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.ndx.lifestream.rendering.output.gollum.GollumOutputter;
import org.ndx.lifestream.utils.exception.LifestreamException;

public class VFSHelper {
	public static class UnableToLoadFileSystemManagerException extends LifestreamException {

		public UnableToLoadFileSystemManagerException() {
			super();
		}

		public UnableToLoadFileSystemManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public UnableToLoadFileSystemManagerException(String message, Throwable cause) {
			super(message, cause);
		}

		public UnableToLoadFileSystemManagerException(String message) {
			super(message);
		}

		public UnableToLoadFileSystemManagerException(Throwable cause) {
			super(cause);
		}

	}
	private static final Logger logger = Logger.getLogger(GollumOutputter.class.getName());

	private static FileSystemManager fsManager;

	static {
		try {
			fsManager = VFS.getManager();
		} catch (FileSystemException e) {
			throw new UnableToLoadFileSystemManagerException(e);
		}
	}

	public static FileSystemManager getManager() {
		return fsManager;
	}

}

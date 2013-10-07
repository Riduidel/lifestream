package org.ndx.lifestream.goodreads;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.configuration.Configuration;

public class GoodreadsConfiguration extends AbstractConfiguration implements Configuration {
	public GoodreadsConfiguration(FileObject baseFolder) {
		super(baseFolder, CACHE_BASE_PATH + "goodreads");
	}

	public FileObject getCachedExport() throws FileSystemException {
		return getCacheFolder().resolveFile("export.csv");
	}
}

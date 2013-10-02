package org.ndx.lifestream.goodreads;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.configuration.Configuration;

public class GoodreadsConfiguration extends AbstractConfiguration implements Configuration {
	private FileObject baseFolder;
	private FileObject cacheFolder;
	
	public GoodreadsConfiguration(FileObject baseFolder) {
		this.baseFolder = baseFolder;
	}

	public FileObject getCacheFolder() {
		if(cacheFolder==null) {
			try {
				cacheFolder = baseFolder.resolveFile("target/cache");
			} catch (FileSystemException e) {
				throw new UnableToConfigureGoodreadsException(e);
			}
		}
		return cacheFolder;
	}

}

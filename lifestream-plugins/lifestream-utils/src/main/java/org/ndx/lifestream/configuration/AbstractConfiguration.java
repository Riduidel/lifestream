package org.ndx.lifestream.configuration;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;

public abstract class AbstractConfiguration implements Configuration {

	protected static final String CACHE_BASE_PATH = "target/cache/";
	private final FileObject baseFolder;
	private FileObject cacheFolder;
	private String cachePath;

	public AbstractConfiguration(FileObject baseFolder, String cacheSpecificPath) {
		super();
		cachePath = cacheSpecificPath;
		this.baseFolder = baseFolder;
	}
	
	public FileObject getCacheFolder() {
		if(cacheFolder==null) {
			try {
				cacheFolder = baseFolder.resolveFile(cachePath);
			} catch (FileSystemException e) {
				throw new UnableToConfigureCacheException(e);
			}
		}
		return cacheFolder;
	}
}

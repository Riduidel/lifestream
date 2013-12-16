package org.ndx.lifestream.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.exception.LifestreamException;

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

	public long getCacheTimeout() {
		return 24*60*60*1000;
	}

	/**
	 * Refresh cached file with given cache laoder and output the content of that cached file	 * @return
	 * @throws IOException
	 * @throws FileSystemException
	 */
	public String refreshCacheWith(CacheLoader cacheLaoder) throws FileSystemException, IOException {
		String content = null;
		FileObject cachedExport = getCachedExport();
		if(cachedExport.exists()) {
			long lastModifiedTime = cachedExport.getContent().getLastModifiedTime();
			// csv can be download each day (not many peop
			if((System.currentTimeMillis()-lastModifiedTime)<getCacheTimeout()) {
				try(InputStream fileContent = cachedExport.getContent().getInputStream()) {
					content = IOUtils.toString(fileContent, Constants.UTF_8);
				}
			}
		}
		if(content==null) {
			try {
				content = cacheLaoder.load();
				try(OutputStream fileContent = cachedExport.getContent().getOutputStream()) {
					IOUtils.write(content, fileContent, Constants.UTF_8);
				}
			} catch (LifestreamException e) {
				throw e;
			} catch (Exception e) {
				throw new UnableToRefreshCacheException(e);
			}
		}
		return content;
	}

	public abstract FileObject getCachedExport() throws FileSystemException;
}

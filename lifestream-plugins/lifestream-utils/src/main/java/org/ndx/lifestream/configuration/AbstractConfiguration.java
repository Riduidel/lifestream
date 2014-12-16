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

	private final FileObject baseFolder;
	private FileObject cacheFolder;
	private String cachePath;
	private LinkResolver linkResolver;
	private twitter4j.conf.Configuration twitterConfiguration;

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
				throw new UnableToConfigureCacheException("Unable to load path for cache folder "+ cachePath, e);
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
		return getFromCacheOrLoad(cacheLaoder, getCachedExport(), getCacheTimeout());
	}

	/**
	 * Download data from the web or load it from the cache if it was already in cache
	 * @param cacheLaoder
	 * @param cachedPath
	 * @param cacheTimeout
	 * @return
	 * @throws FileSystemException
	 * @throws IOException
	 */
	public String getFromCacheOrLoad(CacheLoader cacheLaoder, FileObject cachedPath, long cacheTimeout) throws FileSystemException, IOException {
		String content = null;
		if(cachedPath.exists()) {
			long lastModifiedTime = cachedPath.getContent().getLastModifiedTime();
			if(cacheTimeout<0 || (System.currentTimeMillis()-lastModifiedTime)<cacheTimeout) {
				try(InputStream fileContent = cachedPath.getContent().getInputStream()) {
					content = IOUtils.toString(fileContent, Constants.UTF_8);
				}
			}
		}
		if(content==null) {
			try {
				content = cacheLaoder.load();
				try(OutputStream fileContent = cachedPath.getContent().getOutputStream()) {
					IOUtils.write(content, fileContent, Constants.UTF_8);
				}
			} catch (LifestreamException e) {
				throw e;
			} catch (Exception e) {
				throw new UnableToRefreshCacheException("Something strange happened while trying to refresh cache in "+cachePath, e);
			}
		}
		return content;
	}

	public abstract FileObject getCachedExport() throws FileSystemException;

	/**
	 * @return the linkResolver
	 * @category getter
	 * @category linkResolver
	 */
	public LinkResolver getLinkResolver() {
		if(linkResolver==null) {
			try {
				linkResolver = new LinkResolver(this, baseFolder.resolveFile("links/catalog.properties"));
			} catch (FileSystemException e) {
				throw new UnableToConfigureCacheException("unable to create path for links catalog", e);
			}
		}
		return linkResolver;
	}

	/**
	 * @return the baseFolder
	 * @category getter
	 * @category baseFolder
	 */
	public FileObject getBaseFolder() {
		return baseFolder;
	}

	/**
	 * @return the twitterConfiguration
	 * @category getter
	 * @category twitterConfiguration
	 */
	public twitter4j.conf.Configuration getTwitterConfiguration() {
		return twitterConfiguration;
	}

	/**
	 * @param twitterConfiguration the twitterConfiguration to set
	 * @category setter
	 * @category twitterConfiguration
	 */
	public void setTwitterConfiguration(twitter4j.conf.Configuration twitterConfiguration) {
		this.twitterConfiguration = twitterConfiguration;
	}
}

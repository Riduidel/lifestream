package org.ndx.lifestream.configuration;

public interface CacheLoader {

	/**
	 * Load cache content with data coming from source service
	 * @return text content to cache
	 */
	String load() throws Exception;

}

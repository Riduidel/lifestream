package org.ndx.lifestream.configuration;

public interface CacheLoader {

	/**
	 * Load cache content with data coming from source service
	 * @return
	 */
	String load() throws Exception;

}

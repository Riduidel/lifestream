package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;

/**
 *
 * @author ndx
 * @goal goodreads
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class GoodreadsPlugin extends AbstractLifestreamPlugin<Book>  {

	@Override
	protected InputLoader<Book> loadInputLoader() {
		Goodreads goodreadsEngine = new Goodreads();
		goodreadsEngine.username = username;
		goodreadsEngine.password = password;
		return goodreadsEngine;
	}
}

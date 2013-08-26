package org.ndx.lifestream.goodreads;

import java.io.File;

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
	/**
	 * username on goodreads site
	 *
	 * @parameter alias="username"
	 * @required
	 */
	protected String username;


	/**
	 * password on goodreads site
	 *
	 * @parameter alias="password"
	 * @required
	 */
	protected String password;

	/**
	 * Output file where those classes will be written
	 *
	 * @parameter
	 *            default-value="${project.basedir}/src/main/site/markdown/goodreads"
	 */
	protected File output;

	/**
	 * Currently used rendering mode
	 * @parameter alias="mode" default-value="gollum"
	 */
	protected String modeName;

	@Override
	protected InputLoader<Book> loadInputLoader() {
		Goodreads goodreadsEngine = new Goodreads();
		goodreadsEngine.username = username;
		goodreadsEngine.password = password;
		return goodreadsEngine;
	}

	@Override
	public File getOutput() {
		return output;
	}

	@Override
	public String getModeName() {
		return modeName;
	}
}

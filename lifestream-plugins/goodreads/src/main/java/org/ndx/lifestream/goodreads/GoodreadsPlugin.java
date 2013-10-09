package org.ndx.lifestream.goodreads;

import java.io.File;

import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;

/**
 *
 * @author ndx
 * @goal goodreads
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class GoodreadsPlugin extends AbstractLifestreamPlugin<BookInfos, GoodreadsConfiguration>  {
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
	protected InputLoader<BookInfos, GoodreadsConfiguration> loadInputLoader() {
		Goodreads goodreadsEngine = new Goodreads();
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

	@Override
	protected GoodreadsConfiguration createConfiguration() {
		return new GoodreadsConfiguration(VFSHelper.getRunningDir()).withUsername(username).withPassword(password);
	}
}

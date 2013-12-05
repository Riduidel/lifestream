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
	 * Currently used rendering mode
	 * @parameter alias="mode" default-value="gollum"
	 */
	protected String modeName;

	/**
	 * Output file where those markdown generated files will be written.
	 * Notice the goodreads subfolder is automatically added to each file, so no need to add it by hand !
	 *
	 * @parameter
	 *            default-value="${project.basedir}/src/main/site/markdown/"
	 */
	protected File output;

	protected final File getOutput() {
		return output;
	}

	protected final String getModeName() {
		return modeName;
	}


	@Override
	protected InputLoader<BookInfos, GoodreadsConfiguration> loadInputLoader() {
		return new Goodreads();
	}

	@Override
	protected GoodreadsConfiguration createConfiguration() {
		return new GoodreadsConfiguration(VFSHelper.getRunningDir()).withUsername(username).withPassword(password);
	}
}

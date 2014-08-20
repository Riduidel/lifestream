package org.ndx.lifestream.shaarli;

import java.io.File;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;
/**
*
* @author Nicolas
* @goal shaarli
* @phase process-resources
* @requiresDependencyResolution runtime
*/
public class ShaarliPlugin extends AbstractLifestreamPlugin<MicroblogEntry, ShaarliConfiguration> {
	
	/**
	 * Path for caching any kind of data, be it data downloaded from the web, or built by plugin
	 * @parameter
	 *            default-value="${project.basedir}/.cache/"
	 */
	protected File cache;
	/**
	 * username on Shaarli site
	 *
	 * @parameter alias="username"
	 * @required
	 */
	protected String username;


	/**
	 * password on Shaarli site
	 *
	 * @parameter alias="password"
	 * @required
	 */
	protected String password;

	/**
	 * Shaarli site address
	 *
	 * @parameter alias="address"
	 * @required
	 */
	protected String address;

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
	protected InputLoader<MicroblogEntry, ShaarliConfiguration> loadInputLoader() {
		return new Shaarli();
	}

	@Override
	protected ShaarliConfiguration createConfiguration() {
		return new ShaarliConfiguration(getCacheObject())
			.withLogin(username).withPassword(password).withSite(address);
	}

	@Override
	protected File getCache() {
		return cache;
	}

}

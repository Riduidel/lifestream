package org.ndx.lifestream.wordpress;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;

/**
 *
 * @author Nicolas
 * @goal wordpress
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class WordpressPlugin extends AbstractLifestreamPlugin<Post, WordpressConfiguration> {
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
	 * wordpress site address
	 *
	 * @parameter alias="site"
	 * @required
	 */
	protected String site;

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
	protected InputLoader<Post, WordpressConfiguration> loadInputLoader() {
		return new Wordpress();
	}

	@Override
	protected WordpressConfiguration createConfiguration() {
		return new WordpressConfiguration(VFSHelper.getRunningDir()).withLogin(username).withPassword(password).withSite(site);
	}

}

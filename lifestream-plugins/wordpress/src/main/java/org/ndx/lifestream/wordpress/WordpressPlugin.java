package org.ndx.lifestream.wordpress;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;

/**
 * 
 * @author Nicolas
 * @goal wordpress
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class WordpressPlugin extends AbstractLifestreamPlugin<Post> {
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

	/**
	 * wordpress site address
	 *
	 * @parameter alias="site"
	 * @required
	 */
	protected String site;

	@Override
	protected InputLoader<Post> loadInputLoader() {
		Wordpress loader = new Wordpress();
		loader.login = username;
		loader.password = password;
		loader.site = site;
		return loader;
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

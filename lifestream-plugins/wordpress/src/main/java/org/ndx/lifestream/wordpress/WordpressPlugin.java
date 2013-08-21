package org.ndx.lifestream.wordpress;

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
public class WordpressPlugin extends AbstractLifestreamPlugin {
	/**
	 * wordpress site address
	 *
	 * @parameter alias="site"
	 * @required
	 */
	protected String site;

	@Override
	protected InputLoader loadInputLoader() {
		Wordpress loader = new Wordpress();
		loader.login = username;
		loader.password = password;
		loader.site = site;
		return loader;
	}

}

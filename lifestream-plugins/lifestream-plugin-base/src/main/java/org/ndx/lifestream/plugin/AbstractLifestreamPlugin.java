package org.ndx.lifestream.plugin;

import java.io.File;
import java.util.Collection;

import org.apache.commons.vfs2.FileObject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.web.WebClientFactory;

public abstract class AbstractLifestreamPlugin<Type extends Input> extends AbstractMojo {
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

	protected abstract InputLoader<Type> loadInputLoader();

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		InputLoader<Type> loader = loadInputLoader();
		getLog().info("grabbing content");
		try {
			Mode mode = Mode.valueOf(modeName);
			getLog().info("Rendering will be made for \""+mode+"\"");
			Collection<Type> inputs = loader.load(WebClientFactory.getWebClient());
			FileObject outputRoot = VFSHelper.getManager().resolveFile(output.toURI().toURL().toString());
			outputRoot.createFolder();
			// Now output all using given mode
			loader.output(mode, inputs, outputRoot);
		} catch (Exception e) {
			throw new MojoExecutionException("there was a failure during goodreads pages construction", e);
		}
	}
}

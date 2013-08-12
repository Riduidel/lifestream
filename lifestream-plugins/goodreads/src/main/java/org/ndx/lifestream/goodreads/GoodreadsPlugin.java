package org.ndx.lifestream.goodreads;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.vfs2.FileObject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.output.VFSHelper;

/**
 *
 * @author ndx
 * @goal goodreads
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class GoodreadsPlugin extends AbstractMojo  {
	/**
	 * username on goodreads site
	 *
	 * @parameter alias="username"
	 * @required
	 */
	private String username;

	/**
	 * password on goodreads site
	 *
	 * @parameter alias="password"
	 * @required
	 */
	private String password;

	/**
	 * Output file where those classes will be written
	 *
	 * @parameter
	 *            default-value="${project.basedir}/src/main/site/markdown/goodreads"
	 */
	private File output;

	/**
	 * Currently used rendering mode
	 * @parameter alias="mode" default-value="gollum"
	 */
	private String modeName;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Goodreads goodreadsEngine = new Goodreads();
		goodreadsEngine.username = username;
		goodreadsEngine.password = password;
		getLog().info("downloading book list as csv");
		List<String[]> csv;
		try {
			Mode mode = Mode.valueOf(modeName);
			getLog().info("Rendering will be made for \""+mode+"\"");
			csv = goodreadsEngine.getCSV();
			getLog().info("transforming csv to books");
			Collection<Book> books = goodreadsEngine.buildBooksCollection(csv);
			FileObject outputRoot = VFSHelper.getManager().resolveFile(output.toURI().toURL().toString());
			outputRoot.createFolder();
			// Now output all using given mode
			goodreadsEngine.output(mode, books, outputRoot);
		} catch (Exception e) {
			throw new MojoExecutionException("there was a failure during goodreads pages construction", e);
		}
	}
}

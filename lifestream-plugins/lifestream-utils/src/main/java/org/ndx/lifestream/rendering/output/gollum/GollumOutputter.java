package org.ndx.lifestream.rendering.output.gollum;

import java.util.Collection;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.path.PathNavigator;

import com.google.common.base.Joiner;

/**
 * Simple output of raw content to file
 *
 * @author ndx
 *
 */
public class GollumOutputter extends AbstractOutputter implements OutputWriter {

	private static final Logger logger = Logger.getLogger(GollumOutputter.class.getName());

	private static final Joiner PATH_JOINER = Joiner.on('/').skipNulls();

	@Override
	protected PathNavigator toRealPath(Input input) {
		return super.toRealPath(input, ".md");
	}

	@Override
	public void write(Input input, FileObject output) {
		FileObject resultFile;
		PathNavigator usedPath = toRealPath(input);
		try {
			resultFile = output.resolveFile(PATH_JOINER.join(
					FileNameUtils.simplify(usedPath.toPathList())));
			input.accept(this);
			writeFile(resultFile, render(input));
			notify(input, resultFile, output);
		} catch (Exception e) {
			throw new GollumException("unable to output render for input "+usedPath, e);
		}
	}

	@Override
	public String href(Input from, Input to) {
		return href(from, to, HTML);
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, HTML);
	}
}

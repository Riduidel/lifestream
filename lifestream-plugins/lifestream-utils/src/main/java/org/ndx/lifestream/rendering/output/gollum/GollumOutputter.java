package org.ndx.lifestream.rendering.output.gollum;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.output.Formats;
import org.ndx.lifestream.rendering.path.PathNavigator;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

/**
 * Simple output of raw content to file
 *
 * @author ndx
 *
 */
public class GollumOutputter extends AbstractOutputter implements OutputWriter {

	private static final Logger logger = Logger.getLogger(GollumOutputter.class.getName());

	@Override
	protected PathNavigator toRealPath(Linkable input) {
		return super.toRealPath(input, Formats.MARKDOWN);
	}

	@Override
	public void write(Input input, FileObject output, String message) {
		FileObject resultFile;
		PathNavigator usedPath = toRealPath(input);
		try {
			resultFile = output.resolveFile(
					FileNameUtils.simplify(usedPath.toPathList()).stream().collect(Collectors.joining("/"))
					);
			input.accept(this);
			writeFile(resultFile, HtmlToMarkdown.transformHtml(render(input)), message);
			notify(input, resultFile, output);
		} catch (Exception e) {
			throw new GollumException("unable to output render for input "+usedPath, e);
		}
	}

	@Override
	public String href(Input from, Linkable to) {
		return href(from, to, HTML);
	}

	@Override
	public String link(Input from, Linkable to, String text) {
		return link(from, to, text, Formats.MARKDOWN);
	}
}

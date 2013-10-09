package org.ndx.lifestream.rendering.output.gollum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.stringtemplate.v4.ST;

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

	/**
	 * Gollum template is simple, non ?
	 */
	private ST gollum= new ST("<text>");

	@Override
	public void write(Input input, FileObject output) {
		FileObject resultFile;
		Collection<String> usedPath = toRealPath(input);
		try {
			resultFile = output.resolveFile(PATH_JOINER.join(
					FileNameUtils.simplify(usedPath))+".md");
			input.accept(this);
			writeFile(resultFile, render(input));
		} catch (Exception e) {
			throw new GollumException("unable to output render for input "+usedPath, e);
		}
	}

	private String render(Input input) {
		gollum.add("text", input.getText());
		String resultText  = gollum.render();
		gollum.remove("text");
		return resultText;
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text);
	}
}

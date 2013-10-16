package org.ndx.lifestream.rendering.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import com.google.common.base.Joiner;

public abstract class AbstractOutputter implements OutputWriter {

	public void write(Input input, FileObject output) {
		FileObject resultFile = toRealFile(input, output);
		try {
			input.accept(this);
			writeFile(resultFile, render(input));
		} catch (Exception e) {
			throw new UnableToRenderException("unable to output render for input file "+resultFile.getName().getPath(), e);
		}
	}

	protected abstract String render(Input input);

	protected void writeFile(FileObject resultFile, String resultText)
			throws IOException, FileSystemException {
		if(!resultFile.exists())
			resultFile.createFile();
		try (OutputStream outputStream = resultFile.getContent()
				.getOutputStream()) {
			IOUtils.write(HtmlToMarkdown.transformHtml(resultText),
					outputStream, Constants.UTF_8);
		}
	}


	/**
	 * Transform input object into output file by resolving each element
	 * @param input
	 * @param output
	 * @return
	 */
	protected FileObject toRealFile(Input input, FileObject output) {
		Collection<String> realPath = toRealPath(input);
		try {
			FileObject resultFile = output;
			for(String pathElement : realPath) {
				resultFile = resultFile.resolveFile(pathElement);
			}
			return resultFile;
		} catch(FileSystemException e) {
			throw new UnableToresolveOutputFileException("unable to create output file below "+output.getName().getPath()+" for "+Joiner.on('/').join(realPath));
		}
	}
	/**
	 * Get real path for that input.
	 * @param input
	 * @return path (from output root) to that file
	 */
	protected abstract List<String> toRealPath(Input input);

	/**
	 * Default implementation of {@link #toRealPath(Input)} adding an extension to last element
	 */
	protected  List<String> toRealPath(Input input, String extension) {
		List<String> returned = new ArrayList<>();
		Collection<String> expectedPath = input.getExpectedPath();
		Iterator<String> expectedPathIterator = expectedPath.iterator();
		while(expectedPathIterator.hasNext()) {
			String pathElement = expectedPathIterator.next();
			if(expectedPathIterator.hasNext()) {
				returned.add(FileNameUtils.simplify(pathElement));
			} else {
				returned.add(FileNameUtils.simplify(pathElement+extension));
			}
		}
		return returned;
	}

	protected String markdownLink(Input from, Input to, String text) {
		List<String> fromPath = toRealPath(from);
		// remove last element of from path, as it's the file name
		List<String> toPath = toRealPath(to);
		return MarkdownUtils.link(fromPath, toPath, text);
	}
}

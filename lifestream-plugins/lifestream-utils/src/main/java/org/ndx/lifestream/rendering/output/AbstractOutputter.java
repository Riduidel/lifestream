package org.ndx.lifestream.rendering.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

public class AbstractOutputter {

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
	 * Get real path for that input.
	 * Default implementation delegates to {@link Input#getExpectedPath()}
	 * @param input
	 * @return path (from output root) to that file
	 */
	protected  List<String> toRealPath(Input input) {
		List<String> returned = new ArrayList<>();
		returned.addAll(input.getExpectedPath());
		return returned;
	}

	protected String markdownLink(Input from, Input to, String text) {
		return text;
	}
}

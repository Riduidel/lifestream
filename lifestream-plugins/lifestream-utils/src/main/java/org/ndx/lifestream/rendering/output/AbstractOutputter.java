package org.ndx.lifestream.rendering.output;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

public class AbstractOutputter {

	protected void writeFile(FileObject resultFile, String resultText)
			throws IOException, FileSystemException {
		try (OutputStream outputStream = resultFile.getContent()
				.getOutputStream()) {
			IOUtils.write(HtmlToMarkdown.transformHtml(resultText),
					outputStream, Constants.UTF_8);
		}
	}
}

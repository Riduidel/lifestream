package org.ndx.lifestream.rendering.output.jekyll;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;

/**
 * Notice here file name is to be manipulated
 * @author ndx
 *
 */
public class JekyllOutputter implements OutputWriter {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final DateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	private STGroupDir jekyllGroup;
	private ST jekyll;

	public JekyllOutputter() {
		jekyllGroup = new STRawGroupDir("jekyll");
		jekyll = jekyllGroup.getInstanceOf("page");
	}

	@Override
	public void write(Input input, FileObject output) {
		Date writeDate = input.getWriteDate();
		jekyll.add("input", input);
		String resultText  = jekyll.render();
		jekyll.remove("input");
		FileObject resultFile;
		try {
			resultFile = output.resolveFile(FORMATTER.format(writeDate)+"-"+input.getBasename()+".md");
			try (OutputStream outputStream = resultFile.getContent().getOutputStream()) {
				IOUtils.write(HtmlToMarkdown.transformHtml(resultText), outputStream);
			}
		} catch (Exception e) {
			throw new JekykllException("unable to output render for input "+input.getBasename(), e);
		}
	}

}

package org.ndx.lifestream.rendering.output.jekyll;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.gollum.GollumException;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

/**
 * Notice here file name is to be manipulated
 * @author ndx
 *
 */
public class JekyllOutputter implements OutputWriter {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final DateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	private STGroupDir jekyllGroup;

	public JekyllOutputter() {
		jekyllGroup = new STGroupDir("jekyll");
		STGroup.verbose = STGroupDir.verbose = true;
	}

	@Override
	public void write(Input input, FileObject output) {
		ST jekyll = jekyllGroup.getInstanceOf("page");
		Date writeDate = input.getWriteDate();
		jekyll.add("title", input.getTitle());
		jekyll.add("tags", input.getTags());
		jekyll.add("text", input.getText());
		String resultText  = jekyll.render();
		jekyll.remove("text");
		jekyll.remove("tags");
		jekyll.remove("title");
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

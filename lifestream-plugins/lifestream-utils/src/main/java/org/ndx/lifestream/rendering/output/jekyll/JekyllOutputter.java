package org.ndx.lifestream.rendering.output.jekyll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;

/**
 * Notice here file name is to be manipulated
 * @author ndx
 *
 */
public class JekyllOutputter extends AbstractOutputter implements OutputWriter {
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
		FileObject resultFile;
		try {
			String filename = FORMATTER.format(writeDate)+"-"+input.getBasename().replace('/', '_')+".md";
			resultFile = output.resolveFile(filename);
			writeFile(resultFile, render(input));
		} catch (Exception e) {
			throw new JekykllException("unable to output render for input "+input.getBasename(), e);
		}
	}

	private String render(Input input) {
		jekyll.add("input", input);
		String resultText  = jekyll.render();
		jekyll.remove("input");
		return resultText;
	}

}

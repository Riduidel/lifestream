package org.ndx.lifestream.rendering.output.jekyll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.stringtemplate.v4.ST;
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
		FileObject resultFile = output;
		Collection<String> basepath = input.getExpectedPath();
		Iterator<String> iterator = basepath.iterator();
		try {
			while(iterator.hasNext()) {
				String filename = iterator.next();
				if(iterator.hasNext()) {
					resultFile = resultFile.resolveFile(filename);
				} else {
					filename = (writeDate==null ? "2001-01-01" : FORMATTER.format(writeDate))+"-"+filename.replace('/', '_')+".md";
					resultFile = resultFile.resolveFile(filename);
					input.accept(this);
					writeFile(resultFile, render(input));
				}
			}
		} catch (Exception e) {
			throw new JekykllException("unable to output render for input "+basepath, e);
		}
	}

	private String render(Input input) {
		jekyll.add("input", input);
		String resultText  = jekyll.render();
		jekyll.remove("input");
		return resultText;
	}

}

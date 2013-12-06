package org.ndx.lifestream.rendering.output.jekyll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.AbstractStringTemplateBackedOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.stringtemplate.v4.STRawGroupDir;

import com.google.common.base.Joiner;

/**
 * Notice here file name is to be manipulated
 * @author ndx
 *
 */
public class JekyllOutputter extends AbstractStringTemplateBackedOutputter implements OutputWriter {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final DateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	public JekyllOutputter() {
		super("jekyll", "page");
	}

	/**
	 * Create a real path from the expected path that {@link Input} can return
	 * @param input
	 * @return the real path used by this rendering engine
	 */
	protected List<String> toRealPath(Input input) {
		Date writeDate = input.getWriteDate();
		List<String> returned = new ArrayList<>();
		Iterator<String> iterator = input.getExpectedPath().iterator();
		while(iterator.hasNext()) {
			String filename = FileNameUtils.simplify(iterator.next());
			if(iterator.hasNext()) {
				returned.add(filename);
			} else {
				filename = (writeDate==null ? "2001-01-01" : FORMATTER.format(writeDate))+"-"+filename.replace('/', '_')+".md";
				returned.add(filename);
			}
		}
		return returned;
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, "html");
	}

}

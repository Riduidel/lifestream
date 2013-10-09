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
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;

import com.google.common.base.Joiner;

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
		Collection<String> realPath = toRealPath(input);
		try {
			FileObject resultFile = output;
			for(String pathElement : realPath) {
				resultFile = resultFile.resolveFile(pathElement);
			}
			input.accept(this);
			writeFile(resultFile, render(input));
		} catch (Exception e) {
			throw new JekykllException("unable to output render for input "+Joiner.on('/').join(realPath), e);
		}
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

	private String render(Input input) {
		jekyll.add("input", input);
		String resultText  = jekyll.render();
		jekyll.remove("input");
		return resultText;
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text);
	}

}

package org.ndx.lifestream.rendering.output.gumdrop;

import java.util.List;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;

public class GumdropOutputter extends AbstractOutputter implements OutputWriter {

	@Override
	public void write(Input input, FileObject output) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("method "+OutputWriter.class.getName()+"#write has not yet been implemented AT ALL");
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, "html");
	}

	@Override
	protected List<String> toRealPath(Input input) {
		return toRealPath(input, ".md");
	}

	@Override
	protected String render(Input input) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("method "+AbstractOutputter.class.getName()+"#render has not yet been implemented AT ALL");
	}
}

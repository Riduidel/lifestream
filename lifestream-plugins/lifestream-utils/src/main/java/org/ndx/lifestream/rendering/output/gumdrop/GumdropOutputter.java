package org.ndx.lifestream.rendering.output.gumdrop;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.path.PathNavigator;

public class GumdropOutputter extends AbstractOutputter implements OutputWriter {

	@Override
	public void write(Input input, FileObject output) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("method "+OutputWriter.class.getName()+"#write has not yet been implemented AT ALL");
	}

	@Override
	public String href(Input from, Linkable to) {
		return href(from, to, HTML);
	}

	@Override
	public String link(Input from, Linkable to, String text) {
		return markdownLink(from, to, text, HTML);
	}

	@Override
	protected PathNavigator toRealPath(Linkable input) {
		return toRealPath(input, MARKDOWN);
	}

	@Override
	protected String render(Input input) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("method "+AbstractOutputter.class.getName()+"#render has not yet been implemented AT ALL");
	}
}

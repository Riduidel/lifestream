package org.ndx.lifestream.rendering.output.opoopress;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.path.PathNavigator;

public class OpooPressOutputter extends AbstractOutputter
		implements OutputWriter {

	@Override
	public String href(Input from, Input to) {
		return href(from, to, HTML);
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, HTML);
	}

	@Override
	protected PathNavigator toRealPath(Input input) {
		return toRealPath(input, ".md");
	}

}

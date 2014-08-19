package org.ndx.lifestream.rendering.output.opoopress;

import java.util.List;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;

public class OpooPressOutputter extends AbstractOutputter
		implements OutputWriter {

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, "html");
	}

	@Override
	protected List<String> toRealPath(Input input) {
		return toRealPath(input, ".md");
	}

}

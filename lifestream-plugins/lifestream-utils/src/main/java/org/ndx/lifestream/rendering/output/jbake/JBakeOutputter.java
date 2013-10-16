package org.ndx.lifestream.rendering.output.jbake;

import java.util.List;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractStringTemplateBackedOutputter;

public class JBakeOutputter extends AbstractStringTemplateBackedOutputter implements OutputWriter {

	public JBakeOutputter() {
		super("jbake", "page");
	}

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text);
	}


	@Override
	protected List<String> toRealPath(Input input) {
		return toRealPath(input, ".md");
	}

}

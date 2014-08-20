package org.ndx.lifestream.rendering.output.jbake;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.utils.ThreadSafeSimpleDateFormat;

public class JBakeOutputter extends AbstractOutputter implements OutputWriter {
	private static ThreadSafeSimpleDateFormat jbakeFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd");

	@Override
	public String link(Input from, Input to, String text) {
		return markdownLink(from, to, text, "html");
	}


	@Override
	protected List<String> toRealPath(Input input) {
		return toRealPath(input, ".md");
	}

	protected String render(Input input) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("depth", getDepthOf(input));
		parameters.put("uri", getUriOf(input));
		parameters.put("input", input);
		parameters.put("writeDate", jbakeFormat.format(input.getWriteDate()));
		return render(parameters);
	}
}

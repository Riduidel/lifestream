package org.ndx.lifestream.rendering.output.jbake;

import java.util.HashMap;
import java.util.Map;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.path.PathNavigator;
import org.ndx.lifestream.utils.ThreadSafeSimpleDateFormat;

public class JBakeOutputter extends AbstractOutputter implements OutputWriter {
	private static ThreadSafeSimpleDateFormat jbakeFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd");

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

	protected String render(Input input) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("depth", getDepthOf(input));
		parameters.put("uri", getUriOf(input));
		parameters.put("input", input);
		parameters.put("writeDate", jbakeFormat.format(input.getWriteDate()));
		return render(parameters);
	}
}

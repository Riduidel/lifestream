package org.ndx.lifestream.rendering.output.jbake;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.AbstractStringTemplateBackedOutputter;
import org.ndx.lifestream.rendering.output.StringTemplateUtils;

public class JBakeOutputter extends AbstractStringTemplateBackedOutputter implements OutputWriter {
	private static DateFormat jbakeFormat = new SimpleDateFormat("yyyy-MM-dd");

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

	protected String render(Input input) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("depth", getDepthOf(input));
// not useful with this jbake bplugin version
//		parameters.put("uri", getUriOf(input));
		parameters.put("input", input);
		parameters.put("writeDate", jbakeFormat.format(input.getWriteDate()));
		return StringTemplateUtils.applyParametersToTemplate(page, parameters);
	}
}

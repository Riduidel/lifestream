package org.ndx.lifestream.rendering.output;

import java.util.HashMap;
import java.util.Map;

import org.ndx.lifestream.rendering.model.Input;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;

public abstract class AbstractStringTemplateBackedOutputter extends AbstractOutputter {

	protected STGroupDir group;
	protected ST page;

	public AbstractStringTemplateBackedOutputter(String groupName, String pageName) {
		group = new STRawGroupDir(groupName);
		page = group.getInstanceOf(pageName);
	}

	protected String render(Input input) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("input", input);
		return StringTemplateUtils.applyParametersToTemplate(page, parameters);
	}

}

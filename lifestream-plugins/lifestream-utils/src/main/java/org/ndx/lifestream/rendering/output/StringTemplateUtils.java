package org.ndx.lifestream.rendering.output;

import java.util.Map;
import java.util.Map.Entry;

import org.stringtemplate.v4.ST;

public class StringTemplateUtils {

	public static synchronized String applyParametersToTemplate(ST template, Map<String, Object> parameters) {
		for(Map.Entry<String, Object> e : parameters.entrySet()) {
			template.add(e.getKey(), e.getValue());
		}
		try {
			return template.render();
		} finally {
			for(String key : parameters.keySet()) {
				template.remove(key);
			}
		}
	}
}

package org.ndx.lifestream.rendering.output;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class Freemarker {

	/**
	 * Provides a configuration with some default parameters set. Notice template loading will NOT be set here.
	 */
	public static Configuration getConfiguration() {
		Configuration returned = new Configuration();
		returned.setObjectWrapper(new DefaultObjectWrapper());
		returned.setDefaultEncoding("UTF-8");
		returned.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		returned.setIncompatibleEnhancements("2.3.20");
		return returned;
	}

	public static String render(Template template, Map<String, Object> parameters) {
		try {
			CharArrayWriter output = new CharArrayWriter();
			template.process(parameters, output);
			return output.toString();
		} catch (IOException e) {
			throw new UnableToRenderException("unable to render parameters using template "+template.getName(), e);
		} catch (TemplateException e) {
			throw new UnableToRenderException("unable to render parameters using template "+template.getName(), e);
		}
	}
}

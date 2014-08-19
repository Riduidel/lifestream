package org.ndx.lifestream.goodreads;

import java.io.IOException;
import java.util.Date;

import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.output.Freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * Base interface for all book data exported from goodreads
 * @author Nicolas
 *
 */
public abstract class BookInfos implements Input {
	protected static Configuration goodreadsTemplates;

	static {
		goodreadsTemplates = Freemarker.getConfiguration();
		// we set template path here
		goodreadsTemplates.setClassForTemplateLoading(BookInfos.class, "/templates");
	}

	/**
	 * Utility method used to transform an input tag name into something usable for a file name
	 * @param tag
	 * @return
	 */
	protected static String forTag(String tag) {
		return FileNameUtils.simplify(tag);
	}

	private String title;
	private String id;
	public  static final Date TODAY = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getStyle() {
		return "goodreads";
	}

	@Override
	public String getSource() {
		return null;
	}

	/**
	 * @return the title
	 * @category getter
	 * @category title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 * @category setter
	 * @category title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public static Template loadTemplate(String string) {
		try {
			return goodreadsTemplates.getTemplate(string);
		} catch (IOException e) {
			throw new UnableToConfigureGoodreadsException(e);
		}
	}
}

package org.ndx.lifestream.goodreads;

import java.util.Date;

import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;


/**
 * Base interface for all book data exported from goodreads
 * @author Nicolas
 *
 */
public abstract class BookInfos implements Input {
	protected static STGroupDir goodreadsGroup;

	static {
		goodreadsGroup = new STRawGroupDir("templates");
	}

	/**
	 * Utility method used to transform an input tag name into something usable for a file name
	 * @param tag
	 * @return
	 */
	protected static String forTag(String tag) {
		return FileNameUtils.simplify(tag);
	}

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
}

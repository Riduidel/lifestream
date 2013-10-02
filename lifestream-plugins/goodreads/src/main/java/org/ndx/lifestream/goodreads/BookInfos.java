package org.ndx.lifestream.goodreads;

import org.ndx.lifestream.rendering.model.Input;
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

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

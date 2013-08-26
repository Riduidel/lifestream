package org.ndx.lifestream.wordpress;

import java.util.Collection;
import java.util.Date;

import org.ndx.lifestream.rendering.model.Input;

public class Post implements Input {

	String title;
	String uri;
	Collection<String> tags;
	String text;
	Date writeDate;
	
	String basename;
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getBasename() {
		return basename;
	}

	@Override
	public Collection<String> getTags() {
		return tags;
	}

	@Override
	public Date getWriteDate() {
		return writeDate;
	}

	@Override
	public String getTitle() {
		return title;
	}

}

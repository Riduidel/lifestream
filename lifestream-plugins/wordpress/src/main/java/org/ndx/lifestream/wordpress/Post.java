package org.ndx.lifestream.wordpress;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;

public class Post implements Input {
	public static enum Type {
		post, attachment, page, link;
	}

	String title;
	String uri;
	Collection<String> tags;
	String text;
	Date writeDate;

	String basename;
	Type type;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		Collection<String> returned = new LinkedList<>();
		returned.add("wordpress");
		returned.addAll(Arrays.asList(basename.split("/")));
		return returned;
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

	@Override
	public String toString() {
		return basename;
	}

	/**
	 * prepare links
	 */
	@Override
	public void accept(OutputWriter writer) {
		// TODO Auto-generated method stub

	}


}

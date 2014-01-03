package org.ndx.lifestream.wordpress;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Input.Headers;

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
	public String source;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		Collection<String> returned = new LinkedList<>();
		returned.add("wordpress");
		for(String path : basename.split("/")) {
			if(path.trim().length()>0)
				returned.add(path);
		}
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

	@Override
	public String getStyle() {
		return "wordpress";
	}

	@Override
	public String getSource() {
		return source;
	}
	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = new TreeMap<>();
		return returned;
	}
}

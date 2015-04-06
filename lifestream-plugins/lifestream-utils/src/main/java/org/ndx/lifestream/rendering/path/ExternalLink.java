package org.ndx.lifestream.rendering.path;

import java.util.Arrays;
import java.util.Collection;

import org.ndx.lifestream.rendering.model.Linkable;

public class ExternalLink implements Linkable {

	private final String initial;
	private final String current;

	public ExternalLink(String initial, String current) {
		this.initial = initial;
		this.current = current;
	}

	@Override
	public Collection<String> getSourceLinks() {
		return Arrays.asList(initial);
	}

	/**
	 * @return the current
	 * @category getter
	 * @category current
	 */
	public String getCurrent() {
		return current;
	}

}

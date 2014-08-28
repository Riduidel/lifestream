package org.ndx.lifestream.rendering.path;

import java.util.Arrays;
import java.util.Collection;

import org.ndx.lifestream.rendering.model.Linkable;

public class RelativeLink implements Linkable {

	private final String source;
	private final String destination;

	public RelativeLink(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public Collection<String> getSourceLinks() {
		return Arrays.asList(source);
	}

	/**
	 * @return the destination
	 * @category getter
	 * @category destination
	 */
	public String getDestination() {
		return destination;
	}

}

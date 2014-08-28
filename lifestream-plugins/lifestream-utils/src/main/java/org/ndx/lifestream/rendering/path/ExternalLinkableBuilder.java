package org.ndx.lifestream.rendering.path;


public class ExternalLinkableBuilder implements PathBuilder {

	private ExternalLink external;

	public ExternalLinkableBuilder(ExternalLink external) {
		this.external = external;
	}

	@Override
	public PathNavigator build(String extension) {
		return new ExternalPathNavigator(external);
	}

}

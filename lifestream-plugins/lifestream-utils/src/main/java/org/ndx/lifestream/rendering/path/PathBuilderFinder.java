package org.ndx.lifestream.rendering.path;

import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;

public class PathBuilderFinder {

	public PathBuilder get(Linkable input) {
		if (input instanceof Input) {
			return newPathBuilderForInput((Input) input);
		} else if (input instanceof ExternalLink) {
			return newPathBuilderForExternalLink((ExternalLink) input);
		} else if (input instanceof RelativeLink) {
			return newRelativeLinkBuilder((RelativeLink) input);
		}
		throw new UnsupportedOperationException(input.getClass().getName()+" is not supported, and it is sad");
	}

	protected RelativeLinkBuilder newRelativeLinkBuilder(RelativeLink relative) {
		return new RelativeLinkBuilder(relative);
	}

	protected ExternalLinkableBuilder newPathBuilderForExternalLink(ExternalLink external) {
		return new ExternalLinkableBuilder(external);
	}

	protected RelativePathBuilder newPathBuilderForInput(Input input) {
		return new RelativePathBuilder((Input) input);
	}
	
}
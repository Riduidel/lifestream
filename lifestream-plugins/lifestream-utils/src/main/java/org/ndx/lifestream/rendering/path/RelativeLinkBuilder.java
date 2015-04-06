package org.ndx.lifestream.rendering.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;


public class RelativeLinkBuilder implements PathBuilder {

	private final RelativeLink relative;

	public RelativeLinkBuilder(RelativeLink relative) {
		this.relative = relative;
	}

	@Override
	public PathNavigator build(String extension) {
		LinkedList<String> path = new LinkedList<>(Arrays.asList(relative.getDestination().split("/")));
		String filename = path.getLast();
		if(filename.endsWith(AbstractOutputter.MARKDOWN)) {
			filename = filename.substring(0, filename.lastIndexOf(AbstractOutputter.MARKDOWN));
			path.removeLast();
			path.add(filename);
		}
		return RelativePathBuilder.buildFrom(path, extension);
	}

}

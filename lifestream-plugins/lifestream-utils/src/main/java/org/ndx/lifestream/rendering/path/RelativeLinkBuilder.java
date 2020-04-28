package org.ndx.lifestream.rendering.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.output.Formats;


public class RelativeLinkBuilder implements PathBuilder {

	private final RelativeLink relative;

	public RelativeLinkBuilder(RelativeLink relative) {
		this.relative = relative;
	}

	@Override
	public PathNavigator build(String extension) {
		LinkedList<String> path = new LinkedList<>(Arrays.asList(relative.getDestination().split("/")));
		Formats.forFile(path.getLast()).stream().forEach(m -> {
			String filename = path.removeLast();
			path.add(filename.substring(0, filename.lastIndexOf(m.extension)));
		});
		return RelativePathBuilder.buildFrom(path, extension);
	}

}

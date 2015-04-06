package org.ndx.lifestream.rendering.path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.FileNameUtils;

public class RelativePathBuilder implements PathBuilder {

	protected final Input input;

	public RelativePathBuilder(Input input) {
		this.input = input;
	}

	@Override
	public PathNavigator build(String extension) {
		return buildFrom(input.getExpectedPath(), extension);
	}

	public static PathNavigator buildFrom(Collection<String> collection, String extension) {
		List<String> returned = new ArrayList<>();
		Iterator<String> expectedPathIterator = collection.iterator();
		while(expectedPathIterator.hasNext()) {
			String pathElement = expectedPathIterator.next();
			if(expectedPathIterator.hasNext()) {
				returned.add(FileNameUtils.simplify(pathElement));
			} else {
				returned.add(FileNameUtils.simplify(pathElement+extension));
			}
		}
		return new RelativePath(returned);
	}

}

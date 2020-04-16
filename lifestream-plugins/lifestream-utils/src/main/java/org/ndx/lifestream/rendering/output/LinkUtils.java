package org.ndx.lifestream.rendering.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.ndx.lifestream.rendering.path.PathNavigator;

public class LinkUtils {

	public static String relativePath(List<String> fromPath, List<String> toPath, String extension) {
		fromPath = new ArrayList<>(fromPath);
		// copy-protect path list
		toPath = new ArrayList<>(toPath);
		// and now, check if last element contains a dot
		if(toPath.get(toPath.size()-1).contains(".")) {
			String filename = toPath.get(toPath.size()-1);
			int lastDotPos = filename.lastIndexOf('.');
			filename = filename.substring(0, lastDotPos+1)+extension;
			toPath.set(toPath.size()-1, filename);
		}
		// remove last element, as we're no interested in
		fromPath = fromPath.subList(0, fromPath.size()-1);
		int size = Math.min(fromPath.size(), toPath.size());
		while((fromPath.size()>0 && toPath.size()>0) && fromPath.get(0).equals(toPath.get(0))) {
			fromPath.remove(0);
			toPath.remove(0);
		}
		String path = "";
		if(fromPath.size()>0) {
			path += fromPath.stream()
					.map(s -> "..")
					.collect(Collectors.joining("/", "", "/"));
		}
		if(toPath.size()>0) {
			path += toPath.stream().collect(Collectors.joining("/"));
		}
		return path;
	}

	public static String relativePath(PathNavigator fromPath, PathNavigator toPath, String extension) {
		try {
			return relativePath(fromPath.toPathList(), toPath.toPathList(), extension);
		} catch(Exception e) {
			return toPath.toURI();
		}
	}

}

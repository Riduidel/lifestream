package org.ndx.lifestream.rendering.output;

import java.util.List;

import org.ndx.lifestream.rendering.path.PathNavigator;

public class MarkdownUtils {

	public static String link(List<String> fromPath, List<String> toPath, String text, String extension) {
		String path = LinkUtils.relativePath(fromPath, toPath, extension);
		return link(text, path);
	}

	protected static String link(String text, String path) {
		return String.format("[%s](%s)", text, path);
	}

	public static String link(PathNavigator fromPath, PathNavigator toPath, String text, String extension) {
		try {
			return link(fromPath.toPathList(), toPath.toPathList(), text, extension);
		} catch(Exception e) {
			return link(text, toPath.toURI());
		}
	}
}

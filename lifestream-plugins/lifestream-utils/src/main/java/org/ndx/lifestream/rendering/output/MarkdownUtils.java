package org.ndx.lifestream.rendering.output;

import java.util.List;

public class MarkdownUtils {

	public static String link(List<String> fromPath, List<String> toPath, String text, String extension) {
		return String.format("[%s](%s)", text, LinkUtils.relativePath(fromPath, toPath, extension));
	}
}

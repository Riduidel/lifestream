package org.ndx.lifestream.rendering.output;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;

public class MarkdownUtils {

	public static String link(List<String> fromPath, List<String> toPath, String text) {
		// remove last element, as we're no interested in
		fromPath = fromPath.subList(0, fromPath.size()-1);
		int size = Math.min(fromPath.size(), toPath.size());
		while((fromPath.size()>0 && toPath.size()>0) && fromPath.get(0).equals(toPath.get(0))) {
			fromPath.remove(0);
			toPath.remove(0);
		}
		String path = "";
		if(fromPath.size()>0) {
			path += Joiner.on("/").join(Collections2.transform(fromPath, new Function<String, String>() {

				@Override
				public String apply(String input) {
					return "..";
				}}));
			path+="/";
		}
		if(toPath.size()>0) {
			path += Joiner.on("/").join(toPath);
		}

		return String.format("[%s](%s)", text, path);
	}
}
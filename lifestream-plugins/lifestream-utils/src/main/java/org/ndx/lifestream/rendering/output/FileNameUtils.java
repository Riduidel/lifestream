package org.ndx.lifestream.rendering.output;

import java.text.Normalizer;
import java.util.Collection;
import java.util.stream.Collectors;

public class FileNameUtils {
	/**
	 * Simplify a file name to make it easier to write (removing line returns
	 * and other non OS friendly characters)
	 * @param name source file name to simplify
	 * @return name with lots of characters replaced by "_"
	 */
	public static String simplify(String name) {
		if(name==null)
			return name;
		name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		name = name
				.replaceAll("[\\n\\x0B\\f\\r]", "")
				.replaceAll("\\p{Blank}", "_")
				.replace('/', '_')
				.replaceAll("[,*\"':?]", "_");
		// Thanks @glaforge : http://glaforge.appspot.com/article/how-to-remove-accents-from-a-string
		return name;
	}

	public static Collection<String> simplify(Collection<String> expectedPath) {
		return expectedPath.stream()
				.map(FileNameUtils::simplify)
				.collect(Collectors.toList());
	}
}

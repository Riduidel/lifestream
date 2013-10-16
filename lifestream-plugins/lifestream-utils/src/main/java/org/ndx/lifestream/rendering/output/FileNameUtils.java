package org.ndx.lifestream.rendering.output;

import java.text.Normalizer;
import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class FileNameUtils {
	/**
	 * Simplify a file name to make it easier to write (removing line returns
	 * and other non OS friendly characters)
	 * @param name
	 * @return
	 */
	public static String simplify(String name) {
		if(name==null)
			return name;
		name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		name = name
				.replaceAll("[\\n\\x0B\\f\\r]", "")
				.replace('\t', ' ')
				.replaceAll("[*\"':]", "_");
		// Thanks @glaforge : http://glaforge.appspot.com/article/how-to-remove-accents-from-a-string
		return name;
	}

	public static Iterable<String> simplify(Collection<String> expectedPath) {
		return Iterables.transform(expectedPath, new Function<String, String>() {
	
			@Override
			public String apply(String input) {
				return simplify(input);
			}
		});
	}
}

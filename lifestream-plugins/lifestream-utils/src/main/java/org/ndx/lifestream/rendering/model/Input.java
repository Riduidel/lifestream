package org.ndx.lifestream.rendering.model;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.ndx.lifestream.rendering.OutputWriter;

/**
 * Model interface for input objects.
 * @author ndx
 *
 */
public interface Input {
	/**
	 * List of known additional header keys 
	 * @author ndx
	 *
	 */
	public static interface Headers {
		static final String SMALL_IMAGE = "smallImage";
		static final String BIG_IMAGE = "bigImage";
		static final String SOURCE = "source";
		static final String STYLE = "style";
		static final String EXCERPT = "excerpt";
	}

	/**
	 * @return input basic text (that's to say no tags, no categories, no date, no title, nothing)
	 */
	String getText();

	/**
	 * Get file expected path. Folders are possible, but should be kept coherent
	 * @return a collection of string in which last element will be file name
	 */
	Collection<String> getExpectedPath();

	Collection<String> getTags();

	/**
	 * Get write date of entry. When null, this date is considered to be the 01/01/2001
	 * @return
	 */
	Date getWriteDate();

	String getTitle();

	/**
	 * Accept writer before writing object.
	 * This allow some link redirection in text, or other things like that ...
	 * @param writer writer used to finally write file
	 */
	void accept(OutputWriter writer);
	/**
	 * Allow input to provide additional header entries. 
	 * @return a non null map, please
	 */
	Map<String, String> getAdditionalHeaders();
}

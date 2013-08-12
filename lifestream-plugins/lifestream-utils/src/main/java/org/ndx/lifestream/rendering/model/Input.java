package org.ndx.lifestream.rendering.model;

import java.util.Collection;
import java.util.Date;

/**
 * Model interface for input objects.
 * @author ndx
 *
 */
public interface Input {

	/**
	 * @return input basic text (that's to say no tags, no categories, no date, no title, nothing)
	 */
	String getText();

	/**
	 * Get file simple name. Folders are possible, but should be kept coherent
	 * @return
	 */
	String getBasename();

	Collection<String> getTags();

	Date getWriteDate();

	String getTitle();

}

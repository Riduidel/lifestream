package org.ndx.lifestream.rendering;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.notifications.WriteListener;

/**
 * An output writer can, given an input object and a path, write the file corresponding to that input object below the path
 * @author ndx
 *
 */
public interface OutputWriter {

	void write(Input input, FileObject output);

	/**
	 * Generate the text of a link (using the current rendering engine) from the source input to the target input
	 * @param from source input
	 * @param to target input
	 * @param text link text
	 * @return a text that can be freely added to rendered output
	 */
	String link(Input from, Linkable to, String text);

	/**
	 * Lower level method than {@link #link(Input, Linkable, String)} : if link generates a full link, this one concentrates on 
	 * generating the href linking the two inputs
	 * @param from the object we want a link from
	 * @param to the destination of that link
	 * @return the usable link text
	 */
	String href(Input from, Linkable to);

	void addListener(WriteListener listener);
	void removeListener(WriteListener listener);

}

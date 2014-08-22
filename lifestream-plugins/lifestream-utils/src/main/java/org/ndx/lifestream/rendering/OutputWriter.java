package org.ndx.lifestream.rendering;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.model.Input;
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
	String link(Input from, Input to, String text);

	/**
	 * Lower level method than {@link #link(Input, Input, String)} : if link generates a full link, this one concentrates on 
	 * generating the href linking the two inputs
	 * @param post
	 * @param p
	 * @return
	 */
	String href(Input from, Input to);

	void addListener(WriteListener listener);
	void removeListener(WriteListener listener);

}

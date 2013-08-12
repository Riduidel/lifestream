package org.ndx.lifestream.rendering;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.model.Input;

/**
 * An output writer can, given an input object and a path, write the file corresponding to that input object below the path
 * @author ndx
 *
 */
public interface OutputWriter {

	void write(Input input, FileObject output);

}

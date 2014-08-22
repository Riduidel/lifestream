package org.ndx.lifestream.rendering.path;

import java.util.List;

import org.apache.commons.vfs2.FileObject;

/**
 * Allows navigation in a path. It's in fact a way to abstract the dofferent nature 
 * between relative local links and absolute distant ones.
 * @author ndx
 *
 */
public interface PathNavigator {

	/**
	 * Transform a local path navigator into a FileObject. Obviously this will fail for remote paths
	 * @param output
	 * @return
	 */
	FileObject toRealFile(FileObject output) throws IpossibleOnRemoteFileException;

	String toURI();

	String depth();

	List<String> toPathList();

}

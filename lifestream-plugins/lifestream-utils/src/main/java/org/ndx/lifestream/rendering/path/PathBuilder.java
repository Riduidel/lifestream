package org.ndx.lifestream.rendering.path;

/**
 * Interface building a path from a linkable object
 * @author ndx
 *
 */
public interface PathBuilder {

	PathNavigator build(String extension);
	
}
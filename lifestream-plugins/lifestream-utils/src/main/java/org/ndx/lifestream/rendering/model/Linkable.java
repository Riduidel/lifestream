package org.ndx.lifestream.rendering.model;

import java.util.Collection;

/**
 * Model interface for linkable elements. A linkable element is something we can link to.
 * It can either be an Input (that's to say content to be transformed) or external content (an external URL, in other words)
 * @author ndx
 *
 */
public interface Linkable {
	Collection<String> getSourceLinks();
}

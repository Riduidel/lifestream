package org.ndx.lifestream.rendering.model;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.Mode;

public interface InputLoader<Type extends Input> {

	Collection<Type> load();

	public void output(Mode mode, Collection<Type> inputs, FileObject outputRoot);
	
}

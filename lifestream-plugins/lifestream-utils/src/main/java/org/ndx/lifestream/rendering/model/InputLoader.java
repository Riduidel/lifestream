package org.ndx.lifestream.rendering.model;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.Mode;

import com.gargoylesoftware.htmlunit.WebClient;

public interface InputLoader<Type extends Input> {

	Collection<Type> load(WebClient client);

	public void output(Mode mode, Collection<Type> inputs, FileObject outputRoot);
	
}

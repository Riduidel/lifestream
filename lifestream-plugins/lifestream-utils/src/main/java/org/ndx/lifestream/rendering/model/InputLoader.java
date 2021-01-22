package org.ndx.lifestream.rendering.model;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.configuration.Configuration;
import org.ndx.lifestream.rendering.Mode;
import org.openqa.selenium.WebDriver;

public interface InputLoader<Type extends Input, ConfigurationType extends Configuration> {

	Collection<Type> load(WebDriver client, ConfigurationType configuration);

	public void output(Mode mode, Collection<Type> inputs, FileObject outputRoot, ConfigurationType configuration);

}

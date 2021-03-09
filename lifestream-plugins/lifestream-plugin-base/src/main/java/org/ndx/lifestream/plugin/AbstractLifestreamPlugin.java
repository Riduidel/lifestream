package org.ndx.lifestream.plugin;

import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ndx.lifestream.configuration.Configuration;
import org.ndx.lifestream.plugin.exceptions.UnableToResolveCacheFolderException;
import org.ndx.lifestream.rendering.Mode;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.InputLoader;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.web.WebClientUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.bridge.SLF4JBridgeHandler;

public abstract class AbstractLifestreamPlugin<Type extends Input, ConfigurationType extends Configuration> extends AbstractMojo {
	static {
		LogManager.getLogManager().reset();
		SLF4JBridgeHandler.install();
		Logger.getLogger("global").setLevel(Level.FINEST);
	}

	/**
	 * Long term configuration should only be created once
	 */
	private ConfigurationType configuration;

	protected abstract InputLoader<Type, ConfigurationType> loadInputLoader();

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		InputLoader<Type, ConfigurationType> loader = loadInputLoader();
		getLog().info("grabbing content");
		WebDriver webClient = WebClientUtils.getWebClient();
		try {
			Mode mode = Mode.valueOf(getModeName());
			getLog().info("Rendering will be made with \""+mode+"\"");
			Collection<Type> inputs = loader.load(webClient, getConfiguration());
			FileObject outputRoot = VFSHelper.getManager().resolveFile(getOutput().toURI().toURL().toString());
			outputRoot.createFolder();
			// Now output all using given mode
			loader.output(mode, inputs, outputRoot, getConfiguration());
		} catch (Exception e) {
			throw new MojoExecutionException("there was a failure during pages construction", e);
		} finally {
			webClient.close();
		}
	}

	protected abstract File getOutput();

	protected abstract String getModeName();

	protected ConfigurationType getConfiguration() {
		if(configuration==null) {
			configuration = createConfiguration();
		}
		return configuration;
	}

	protected abstract ConfigurationType createConfiguration();

	protected FileObject getCacheObject() {
		try {
			return VFSHelper.getManager().resolveFile(getCache().getAbsolutePath());
		} catch (FileSystemException e) {
			throw new UnableToResolveCacheFolderException("Unable to build file object for path "+getCache().getAbsolutePath(), e);
		}
	}

	/**
	 * @return the cache
	 * @category getter
	 * @category cache
	 */
	protected abstract File getCache();
}
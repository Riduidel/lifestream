package org.ndx.lifestream.rendering.notifications;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.output.UnableToBuildARelativePathException;

public class WriteEvent {
	private Input input;
	private FileObject path;
	private FileObject outputBase;
	/**
	 * @return the input
	 * @category getter
	 * @category input
	 */
	public Input getInput() {
		return input;
	}
	/**
	 * @param input the input to set
	 * @category setter
	 * @category input
	 */
	public void setInput(Input input) {
		this.input = input;
	}
	/**
	 * @param input new value for #input
	 * @category fluent
	 * @category setter
	 * @category input
	 * @return this object for chaining calls
	 */
	public WriteEvent withInput(Input input) {
		this.setInput(input);
		return this;
	}
	/**
	 * @return the path
	 * @category getter
	 * @category path
	 */
	public FileObject getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 * @category setter
	 * @category path
	 */
	public void setPath(FileObject path) {
		this.path = path;
	}
	/**
	 * @param path new value for #path
	 * @category fluent
	 * @category setter
	 * @category path
	 * @return this object for chaining calls
	 */
	public WriteEvent withPath(FileObject path) {
		this.setPath(path);
		return this;
	}
	/**
	 * @return the outputBase
	 * @category getter
	 * @category outputBase
	 */
	public FileObject getOutputBase() {
		return outputBase;
	}
	/**
	 * @param outputBase the outputBase to set
	 * @category setter
	 * @category outputBase
	 */
	public void setOutputBase(FileObject outputBase) {
		this.outputBase = outputBase;
	}
	/**
	 * @param outputBase new value for #outputBase
	 * @category fluent
	 * @category setter
	 * @category outputBase
	 * @return this object for chaining calls
	 */
	public WriteEvent withOutputBase(FileObject outputBase) {
		this.setOutputBase(outputBase);
		return this;
	}
	/**
	 * Build relative path from {@link #outputBase} to {@link #path}
	 */
	public String getRelativePath() {
		try {
			return outputBase.getName().getRelativeName(path.getName());
		} catch (FileSystemException e) {
			throw new UnableToBuildARelativePathException(e);
		}
	}
}

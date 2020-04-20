package org.ndx.lifestream.rendering.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.notifications.WriteEvent;
import org.ndx.lifestream.rendering.notifications.WriteListener;
import org.ndx.lifestream.rendering.path.PathBuilderFinder;
import org.ndx.lifestream.rendering.path.PathNavigator;
import org.ndx.lifestream.rendering.path.RelativePath;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractOutputter implements OutputWriter {
	private static final String DEFAULT_TEMPLATE_NAME = "page.ftl";
	protected static final String HTML = "html";

	public static final String MARKDOWN = ".md";

	private Configuration configuration;
	private String templateName;
	/**
	 * Collection of objects notified when one file is written
	 */
	private Collection<WriteListener> listeners = new LinkedList<>();
	

	public AbstractOutputter() {
		this(DEFAULT_TEMPLATE_NAME);
	}
	public AbstractOutputter(String templateName) {
		configuration = buildConfiguration();
		this.templateName = templateName; 
	}

	/**
	 * Build configuration from Freemarker reference.
	 * see http://freemarker.org/docs/pgui_quickstart_createconfiguration.html
	 */
	private Configuration buildConfiguration() {
		Configuration returned = Freemarker.getConfiguration();
		returned.setClassForTemplateLoading(getClass(), "");
		return returned;
	}
	public void write(Input input, FileObject output) {
		FileObject resultFile = toRealFile(input, output);
		try {
			input.accept(this);
			String rendered = render(input);
			writeFile(resultFile, rendered);
			notify(input, resultFile, output);
		} catch (Exception e) {
			throw new UnableToRenderException("unable to output render for input file "+resultFile.getName().getPath(), e);
		}
	}

	protected void writeHTMLAsMarkdown(FileObject resultFile, String resultText)
			throws IOException {
		writeFile(resultFile, HtmlToMarkdown.transformHtml(resultText));
	}
	
	protected void writeFile(FileObject resultFile, String resultText) throws IOException {
		if(!resultFile.exists())
			resultFile.createFile();
		try (OutputStream outputStream = resultFile.getContent()
				.getOutputStream()) {
			IOUtils.write(resultText,
					outputStream, Constants.UTF_8);
		}
	}


	/**
	 * Transform input object into output file by resolving each element
	 * @param input
	 * @param output
	 * @return
	 */
	protected FileObject toRealFile(Input input, FileObject output) {
		return toRealPath(input).toRealFile(output);
	}
	/**
	 * Get real path for that input.
	 * @param input
	 * @return path (from output root) to that file
	 */
	protected abstract PathNavigator toRealPath(Linkable input);

	/**
	 * Default implementation of {@link #toRealPath(Linkable)} adding an extension to last element
	 */
	protected  PathNavigator toRealPath(Linkable input, String extension) {
		return getPathBuilderFinder().get(input).build(extension);
	}

	/**
	 * Overrides this method to change how the path builder are associated with inputs.
	 * @return
	 */
	protected PathBuilderFinder getPathBuilderFinder() {
		return new PathBuilderFinder();
	}
	/**
	 * @param input
	 * @return path converted to a URI : all File separator are hard-replaced with "/" character
	 */
	protected String getUriOf(Input input) {
		return toRealPath(input).toURI();
	}

	/**
	 * Get a "depth" string indicating with "../" sequences how deep that file is in the hierarchy :
	 * a file in a subfolder will have a depth of 1, and so on ..
	 * @param input
	 * @return a ["../"] sequence with one ".." for each folder
	 * @see #toRealPath(Linkable)
	 */
	protected String getDepthOf(Input input) {
		return toRealPath(input).depth();
	}

	public String href(Input from, Linkable to, String extension) {
		PathNavigator fromPath = toRealPath(from);
		// remove last element of from path, as it's the file name
		PathNavigator toPath = toRealPath(to);
		return LinkUtils.relativePath(fromPath, toPath, extension);
	}

	protected String markdownLink(Input from, Linkable to, String text, String extension) {
		PathNavigator fromPath = toRealPath(from);
		// remove last element of from path, as it's the file name
		PathNavigator toPath = toRealPath(to);
		return MarkdownUtils.link(fromPath, toPath, text, extension);
	}

	protected String render(Map<String, Object> parameters) {
		Template template;
		try {
			template = configuration.getTemplate(templateName);
			return Freemarker.render(template, parameters);
		} catch (IOException e) {
			throw new UnableToRenderException("unable to render parameters using template named "+templateName, e);
		}
	}
	protected String render(Input input) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("input", input);
		return render(parameters);
	}
	/**
	 * @see java.util.Collection#add(java.lang.Object)
	 * @category delegate
	 */
	public void addListener(WriteListener e) {
		listeners.add(e);
	}
	/**
	 * @see java.util.Collection#remove(java.lang.Object)
	 * @category delegate
	 */
	public void removeListener(WriteListener o) {
		listeners.remove(o);
	}
	
	protected void notify(Input written, FileObject path, FileObject output) {
		WriteEvent event = new WriteEvent().withInput(written).withPath(path).withOutputBase(output);
		for(WriteListener l : listeners) {
			l.inputWritten(event);
		}
	}
}

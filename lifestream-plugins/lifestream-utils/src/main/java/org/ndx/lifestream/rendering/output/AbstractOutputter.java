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
import org.ndx.lifestream.rendering.notifications.WriteEvent;
import org.ndx.lifestream.rendering.notifications.WriteListener;
import org.ndx.lifestream.utils.Constants;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;

import com.google.common.base.Joiner;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractOutputter implements OutputWriter {

	private static final String DEFAULT_TEMPLATE_NAME = "page.ftl";
	protected static final String HTML = "html";
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
	 * Build configuration from Freemarker reference
	 * @return
	 * @see http://freemarker.org/docs/pgui_quickstart_createconfiguration.html
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
			writeFile(resultFile, render(input));
			notify(input, resultFile, output);
		} catch (Exception e) {
			throw new UnableToRenderException("unable to output render for input file "+resultFile.getName().getPath(), e);
		}
	}

	protected void writeFile(FileObject resultFile, String resultText)
			throws IOException, FileSystemException {
		if(!resultFile.exists())
			resultFile.createFile();
		try (OutputStream outputStream = resultFile.getContent()
				.getOutputStream()) {
			IOUtils.write(HtmlToMarkdown.transformHtml(resultText),
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
		Collection<String> realPath = toRealPath(input);
		try {
			FileObject resultFile = output;
			for(String pathElement : realPath) {
				resultFile = resultFile.resolveFile(pathElement);
			}
			return resultFile;
		} catch(FileSystemException e) {
			throw new UnableToresolveOutputFileException("unable to create output file below "+output.getName().getPath()+" for "+Joiner.on('/').join(realPath));
		}
	}
	/**
	 * Get real path for that input.
	 * @param input
	 * @return path (from output root) to that file
	 */
	protected abstract List<String> toRealPath(Input input);

	/**
	 * Default implementation of {@link #toRealPath(Input)} adding an extension to last element
	 */
	protected  List<String> toRealPath(Input input, String extension) {
		List<String> returned = new ArrayList<>();
		Collection<String> expectedPath = input.getExpectedPath();
		Iterator<String> expectedPathIterator = expectedPath.iterator();
		while(expectedPathIterator.hasNext()) {
			String pathElement = expectedPathIterator.next();
			if(expectedPathIterator.hasNext()) {
				returned.add(FileNameUtils.simplify(pathElement));
			} else {
				returned.add(FileNameUtils.simplify(pathElement+extension));
			}
		}
		return returned;
	}

	/**
	 * @param input
	 * @return path converted to a URI : all File separator are hard-replaced with "/" character
	 */
	protected String getUriOf(Input input) {
		return Joiner.on("/").join(toRealPath(input));
	}

	/**
	 * Get a "depth" string indicating with "../" sequences how deep that file is in the hierarchy :
	 * a file in a subfolder will have a depth of 1, and so on ..
	 * @param input
	 * @return a ["../"] sequence with one ".." for each folder
	 * @see #toRealPath(Input)
	 */
	protected String getDepthOf(Input input) {
		List<String> realPath = toRealPath(input);
		StringBuilder sOut = new StringBuilder();
		for (int i = 0; i < realPath.size()-1; i++) {
			sOut.append("../");
		}
		return sOut.toString();
	}

	public String href(Input from, Input to, String extension) {
		List<String> fromPath = toRealPath(from);
		// remove last element of from path, as it's the file name
		List<String> toPath = toRealPath(to);
		return LinkUtils.relativePath(fromPath, toPath, extension);
	}

	protected String markdownLink(Input from, Input to, String text, String extension) {
		List<String> fromPath = toRealPath(from);
		// remove last element of from path, as it's the file name
		List<String> toPath = toRealPath(to);
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
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 * @category delegate
	 */
	public void addListener(WriteListener e) {
		listeners.add(e);
	}
	/**
	 * @param o
	 * @return
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

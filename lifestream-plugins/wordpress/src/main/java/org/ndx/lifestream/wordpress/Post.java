package org.ndx.lifestream.wordpress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;

public class Post implements Input {
	private String title;
	private String uri;
	Collection<String> tags;
	private String text;
	private Date writeDate;
	Collection<Comment> comments = new ArrayList<>();

	private String basename;
	private String type;
	private String source;
	public String excerpt;
	private Collection<Linkable> links = new HashSet<>();
	public String status;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Collection<String> getExpectedPath() {
		Collection<String> returned = new LinkedList<>();
		returned.add("wordpress");
		for(String path : basename.split("/")) {
			if(path.trim().length()>0)
				returned.add(path);
		}
		return returned;
	}

	@Override
	public Collection<String> getTags() {
		return tags;
	}

	@Override
	public Date getWriteDate() {
		return writeDate;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return basename;
	}

	/**
	 * prepare links
	 */
	@Override
	public void accept(OutputWriter writer) {
		for(Linkable p : links) {
			String destination = writer.href(this, p);
			for(String source : p.getSourceLinks()) {
				text = text.replace(source, destination);
			}
		}
		// Old wordpress messages don't contain any <p> tag, so add it around each line if missing
		text = String.format("++++\n%s\n++++", improvePostText(text));
	}

	public static String improvePostText(String text) {
		if(text.contains("<p>")) return text;
		if(text.contains("<br")) return text;
		// First, detect line return that come in blocks of two or more
		List<String> lines = text.lines().collect(Collectors.toList());
		String previousLine = "",
				line = "",
				nextLine = "";
		List<String> returned = new ArrayList<>();
		for(int index = 0; index<lines.size(); index++) {
			if (index > 0)
				previousLine = line;
			if (index < lines.size() - 1)
				nextLine = lines.get(index + 1).trim();
			else
				nextLine = "";
			line = lines.get(index).trim();
			if (!line.isEmpty()) {
				if (previousLine.isBlank()) {
					returned.add("<p>");
				}
				returned.add(line);
				if (nextLine.isBlank()) {
					returned.add("</p>");
				} else {
					returned.add("<br/>");
				}
			}
		}
		return returned.stream().collect(Collectors.joining("\n"));
	}

	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = new TreeMap<>();
		returned.put(Headers.EXCERPT, excerpt);
		returned.put(Headers.STYLE, "wordpress");
		returned.put(Headers.SOURCE, source);
		return returned;
	}

	/**
	 * @return the uri
	 * @category getter
	 * @category uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 * @category setter
	 * @category uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the basename
	 * @category getter
	 * @category basename
	 */
	public String getBasename() {
		return basename;
	}

	/**
	 * @param basename the basename to set
	 * @category setter
	 * @category basename
	 */
	public void setBasename(String basename) {
		this.basename = basename;
	}

	/**
	 * @return the type
	 * @category getter
	 * @category type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 * @category setter
	 * @category type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the source
	 * @category getter
	 * @category source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 * @category setter
	 * @category source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @param title the title to set
	 * @category setter
	 * @category title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param text the text to set
	 * @category setter
	 * @category text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param writeDate the writeDate to set
	 * @category setter
	 * @category writeDate
	 */
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public void addLinkTo(Linkable post) {
		this.links .add(post);
	}

	@Override
	public Collection<String> getSourceLinks() {
		return Arrays.asList(getUri(), getSource());
	}
}

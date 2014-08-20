package org.ndx.lifestream.shaarli;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Input.Headers;
import org.ndx.lifestream.rendering.output.Freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MicroblogEntry implements Input {
	private static Template template;
	static {
		Configuration shaarliGroup = Freemarker.getConfiguration();
		shaarliGroup.setClassForTemplateLoading(MicroblogEntry.class, "/templates");
		try {
			template = shaarliGroup.getTemplate("link.ftl");
		} catch (IOException e) {
			throw new UnableToConfigureShaarliException(e);
		}

	}

	private String title;
	private String link;
	private String comment;
	private Collection<String> tags;
	private Date writeDdate;
	private boolean visible;
	/**
	 * Getenerated by the {@link #accept(OutputWriter)} method
	 */
	private String text;
	private String source;
	/**
	 * @return the title
	 * @category getter
	 * @category title
	 */
	public String getTitle() {
		return title;
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
	 * @return the link
	 * @category getter
	 * @category link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 * @category setter
	 * @category link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the text
	 * @category getter
	 * @category text
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param text the text to set
	 * @category setter
	 * @category text
	 */
	public void setComment(String text) {
		this.comment = text;
	}
	/**
	 * @return the tags
	 * @category getter
	 * @category tags
	 */
	public Collection<String> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 * @category setter
	 * @category tags
	 */
	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	/**
	 * @return the date
	 * @category getter
	 * @category date
	 */
	public Date getWriteDate() {
		return writeDdate;
	}
	/**
	 * @param date the date to set
	 * @category setter
	 * @category date
	 */
	public void setWriteDdate(Date date) {
		this.writeDdate = date;
	}
	/**
	 * @return the visible
	 * @category getter
	 * @category visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 * @category setter
	 * @category visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	@Override
	public Collection<String> getExpectedPath() {
		Collection<String> returned = new LinkedList<>();
		returned.add("shaarli");
		// should find a better way ...
		returned.add(Long.toString(getWriteDate().getTime()));
		return returned;
	}

	@Override
	public void accept(OutputWriter writer) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("link", this);
		text = Freemarker.render(template, parameters);
	}
	@Override
	public String getText() {
		return text;
	}
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MicroblogEntry [");
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (link != null) {
			builder.append("link=");
			builder.append(link);
		}
		builder.append("]");
		return builder.toString();
	}
	@Override
	public Map<String, String> getAdditionalHeaders() {
		Map<String, String> returned = new TreeMap<>();
		returned.put(Headers.STYLE, "shaarli");
		returned.put(Headers.SOURCE, source);
		return returned;
	}

	public void setSource(String sourceLink) {
		this.source = sourceLink;
	}
}

package org.ndx.lifestream.utils.transform;

import static org.junit.Assert.*;

import java.net.URL;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

public class HtmlToMarkdownTest {
	
	@Test
	public void markdonXSLTCanBeLoaded() {
		URL resource = HtmlToMarkdown.class.getResource(HtmlToMarkdown.MARKDOWN);
		assertThat(resource, IsNull.notNullValue());
	}

	@Test
	public void cleanupWork() {
		String text = HtmlToMarkdown.transformToValidXhtml("a simple text");
		assertThat(text, Is.is("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<html>"+
"\n\t\n<head />"+	
"\n\t\n<body>a simple text</body>"+
"\n</html>\n"));
	}

	@Test
	public void transformToMarkdown() {
		String sourceText = "a simple text";
		String markdown = HtmlToMarkdown.transformHtml(sourceText);
		assertThat(markdown, Is.is(sourceText));
	}
}

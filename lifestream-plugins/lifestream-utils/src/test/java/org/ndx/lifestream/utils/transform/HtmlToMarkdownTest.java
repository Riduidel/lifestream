package org.ndx.lifestream.utils.transform;

import static org.junit.Assert.*;

import java.net.URL;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

public class HtmlToMarkdownTest {

	@Test
	public void markdonXSLTCanBeLoaded() {
		URL resource = MarkdownTransformerLazyLoader.class.getResource(MarkdownTransformerLazyLoader.MARKDOWN);
		assertThat(resource, IsNull.notNullValue());
	}

	@Test
	public void cleanupWork() {
		String text = HtmlToMarkdown.transformToValidXhtml("a simple text");
		assertThat(text, Is.is(
						"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
						+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
						+ "<html>\r\n<head/>\r\n<body>a simple text</body>\r\n</html>\r\n"));
	}

	@Test
	public void transformSimpleTextToMarkdown() {
		String sourceText = "a simple text";
		String markdown = HtmlToMarkdown.transformHtml(sourceText);
		assertThat(markdown, Is.is(sourceText));
	}

	@Test
	public void transformTextWithBoldToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <b>text</b>");
		assertThat(markdown, Is.is("a simple <b>text</b>"));
	}

	@Test
	public void transformTextWithLineReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <br/>text");
		assertThat(markdown, Is.is("a simple <b>text</b>"));
	}
}

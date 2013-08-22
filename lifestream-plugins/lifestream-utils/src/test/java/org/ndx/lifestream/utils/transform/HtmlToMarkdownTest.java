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
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
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
		assertThat(markdown, Is.is("a simple **text**"));
	}

	@Test
	public void transformTextWithStrongToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <strong>text</strong>");
		assertThat(markdown, Is.is("a simple **text**"));
	}

	@Test
	public void transformTextWithLineReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <br/>text");
		assertThat(markdown, Is.is("a simple   \r\ntext"));
	}

	@Test
	public void transformTextWithH1ReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("<h1>a simple</h1>\n text");
		assertThat(markdown, Is.is("#a simple\r\n text"));
	}
}

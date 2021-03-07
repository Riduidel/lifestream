package org.ndx.lifestream.utils.transform;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

public class HtmlToMarkdownTest {

	@Test
	public void transformEmptyTextToMarkdown() {
		String sourceText = "";
		String markdown = HtmlToMarkdown.transformHtml(sourceText);
		assertThat(markdown, Is.is(sourceText));
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
		assertThat(markdown, Is.is("a simple  "+System.lineSeparator()+"text"));
	}

	@Test
	public void transformTextWithH1ReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("<h1>a simple</h1>\n text");
		assertThat(markdown, Is.is("# a simple #"+System.lineSeparator()+System.lineSeparator()+"text"));
	}

	@Test
	public void transformTextWithEACUTE() {
		String markdown = HtmlToMarkdown.transformHtml("&eacute;");
		assertThat(markdown, Is.is("é"));
	}

	@Test
	public void transformTextWithEGRAVE() {
		String markdown = HtmlToMarkdown.transformHtml("&egrave;");
		assertThat(markdown, Is.is("è"));
	}
}

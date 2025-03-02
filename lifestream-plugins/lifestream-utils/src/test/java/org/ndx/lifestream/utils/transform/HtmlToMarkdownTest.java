package org.ndx.lifestream.utils.transform;

import static org.hamcrest.MatcherAssert.assertThat;


import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class HtmlToMarkdownTest {

	@Test
	void transformEmptyTextToMarkdown() {
		String sourceText = "";
		String markdown = HtmlToMarkdown.transformHtml(sourceText);
		assertThat(markdown, Is.is(sourceText));
	}

	@Test
	void transformSimpleTextToMarkdown() {
		String sourceText = "a simple text";
		String markdown = HtmlToMarkdown.transformHtml(sourceText);
		assertThat(markdown, Is.is(sourceText));
	}

	@Test
	void transformTextWithBoldToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <b>text</b>");
		assertThat(markdown, Is.is("a simple **text**"));
	}

	@Test
	void transformTextWithStrongToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <strong>text</strong>");
		assertThat(markdown, Is.is("a simple **text**"));
	}

	@Test
	void transformTextWithLineReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("a simple <br/>text");
		assertThat(markdown, Is.is("a simple  "+System.lineSeparator()+"text"));
	}

	@Test
	void transformTextWithH1ReturnToMarkdown() {
		String markdown = HtmlToMarkdown.transformHtml("<h1>a simple</h1>\n text");
		assertThat(markdown, Is.is("# a simple #"+System.lineSeparator()+System.lineSeparator()+"text"));
	}

	@Test
	void transformTextWithEACUTE() {
		String markdown = HtmlToMarkdown.transformHtml("&eacute;");
		assertThat(markdown, Is.is("é"));
	}

	@Test
	void transformTextWithEGRAVE() {
		String markdown = HtmlToMarkdown.transformHtml("&egrave;");
		assertThat(markdown, Is.is("è"));
	}
}

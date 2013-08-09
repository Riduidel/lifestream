package org.ndx.lifestream.utils.transform;

import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;

import static org.ndx.lifestream.utils.transform.TransformerFactoryLazyLoader.getFactory;

public class MarkdownTransformerLazyLoader {
	public static final String MARKDOWN = "markdown.xslt";

	private static Transformer markdownTransformer;

	public static Transformer getMarkdownTransformer() {
		if(markdownTransformer==null) {
			InputStream markdown = HtmlToMarkdown.class.getResourceAsStream(MARKDOWN);
			Source xsltSource = new StreamSource(markdown);
			try {
				markdownTransformer = getFactory().newTransformer(xsltSource);
			} catch (TransformerConfigurationException e) {
				throw new UnableToTransformToMarkdownException(e);
			}
		}
		return markdownTransformer;
	}

}

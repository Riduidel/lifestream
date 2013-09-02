package org.ndx.lifestream.utils.transform;

import static org.ndx.lifestream.utils.transform.TransformerFactoryLazyLoader.getFactory;

import java.io.InputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.ndx.lifestream.utils.Constants;

public class MarkdownTransformerLazyLoader {
	public static final String MARKDOWN = "markdown.xslt";

	private static Transformer markdownTransformer;

	public static Transformer getMarkdownTransformer() {
		if(markdownTransformer==null) {
			InputStream markdown = HtmlToMarkdown.class.getResourceAsStream(MARKDOWN);
			Source xsltSource = new StreamSource(markdown);
			try {
				markdownTransformer = getFactory().newTransformer(xsltSource);
				markdownTransformer.setOutputProperty(OutputKeys.METHOD, "text");
				markdownTransformer.setOutputProperty(OutputKeys.ENCODING, Constants.UTF_8);

			} catch (TransformerConfigurationException e) {
				throw new UnableToTransformToMarkdownException(e);
			}
		}
		return markdownTransformer;
	}

}

package org.ndx.lifestream.utils.transform;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

public class HtmlToMarkdown {
	
	public static final String MARKDOWN = "markdown.xslt";
	
	/**
	 * Cleanup and transform html.
	 * Html is first cleaned up using htmlcleaner, then transformed using {@link #transformValidXhtml(String)}
	 * @param html source html fragment
	 * @return markdown, what else !
	 */
	public static String transformHtml(String html) {
		String validXhtml = transformToValidXhtml(html);
		return transformValidXhtml(validXhtml);
	}

	public static String transformToValidXhtml(String html) {
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties properties = cleaner.getProperties();
		properties.setAdvancedXmlEscape(true);
		properties.setCharset("UTF-8");
		properties.setOmitDoctypeDeclaration(false);
		properties.setOmitHtmlEnvelope(false);
		properties.setOmitXmlDeclaration(false);
		
		TagNode node = cleaner.clean(html);
		String validXhtml = new PrettyXmlSerializer(properties).getAsString(node);
		return validXhtml;
	}

	/**
	 * Transform valid XHTML to Markdown. What is valid XHTML ? You're kidding, aren't you ?
	 * Well, see the tests ... or do consider that each element in valid xhtml include
	 * the namespace declaration. So it starts with h:html, contain h:body, and so on ...
	 * @see http://stackoverflow.com/a/59710/15619
	 * @param sourceXhtml
	 * @return a valid Markdown file
	 */
	public static String transformValidXhtml(String sourceXhtml) {
		try {
	        InputStream markdown = HtmlToMarkdown.class.getResourceAsStream(MARKDOWN);
	
	        Source xmlSource = new StreamSource(new StringReader(sourceXhtml));
	        Source xsltSource = new StreamSource(markdown);
	
	        TransformerFactory factory =
	                TransformerFactory.newInstance();
	        Transformer transformer = factory.newTransformer(xsltSource);
	
	        StringWriter result = new StringWriter();
	        transformer.transform(xmlSource, new StreamResult(result));
	        
	        return result.toString();
		} catch(Exception e) {
			throw new UnableToTransformToMarkdownException(e);
		}
	}
}

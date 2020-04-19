package org.ndx.lifestream.utils.transform;

import static org.ndx.lifestream.utils.transform.MarkdownTransformerLazyLoader.getMarkdownTransformer;
import static org.ndx.lifestream.utils.transform.XHTMLTransformerLazyLoader.getXHTMLTransformer;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.ndx.lifestream.utils.Constants;
import org.w3c.dom.Document;

public class HtmlToMarkdown {

	/**
	 * Cleanup and transform html. Html is first cleaned up using htmlcleaner,
	 * then transformed using {@link #transformValidXhtml(String)}
	 *
	 * @param html
	 *            source html fragment
	 * @return markdown, what else !
	 */
	public static String transformHtml(String html) {
		if(html==null)
			return html;
		if(html.trim().length()==0)
			return html.trim();
		String validXhtml = transformToValidXhtml(html);
		String markdown =  transformValidXhtml(validXhtml);
		String returned = StringEscapeUtils.unescapeHtml4(markdown);
		if(returned.trim().isEmpty()) {
			return html;
		} else {
			return returned;
		}
	}

	public synchronized static String transformToValidXhtml(String html) {
		try {
			Document output = transformToValidXhtmlDocument(html);

			// create string from xml tree
			return transformXMLToString(output);
		} catch(Exception e) {
			throw new UnableToTransformToValidXHTML(e);
		}
	}

	public synchronized static Document transformToValidXhtmlDocument(String html) throws ParserConfigurationException {
		if(html==null)
			return null;
		if(html.trim().length()==0)
			return null;
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties properties = cleaner.getProperties();
		properties.setAdvancedXmlEscape(true);
		properties.setCharset(Constants.UTF_8);
		properties.setOmitDoctypeDeclaration(true);
		properties.setOmitHtmlEnvelope(false);
		properties.setOmitXmlDeclaration(true);

		TagNode node = cleaner.clean(html);
		Document output = new DomSerializer(properties).createDOM(node);
		return output;
	}

	public synchronized static String transformXMLToString(Document input) throws TransformerException {
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(input);
		getXHTMLTransformer().transform(source, result);
		String xmlString = sw.toString();
		return xmlString.trim();
	}

	/**
	 * Transform valid XHTML to Markdown. What is valid XHTML ? You're kidding,
	 * aren't you ? Well, see the tests ... or do consider that each element in
	 * valid xhtml include the namespace declaration. So it starts with h:html,
	 * contain h:body, and so on ...
	 *
	 * see http://stackoverflow.com/a/59710/15619
	 * @param sourceXhtml source XML file that will be transformed
	 * @return a valid Markdown file
	 */
	public static String transformValidXhtml(String sourceXhtml) {
		try {
			if(sourceXhtml==null)
				return sourceXhtml;
			if(sourceXhtml.trim().length()==0)
				return sourceXhtml.trim();
			Source xmlSource = new StreamSource(new StringReader(sourceXhtml));
	        StringWriter result = new StringWriter();
	        getMarkdownTransformer().transform(xmlSource, new StreamResult(result));

	        return result.toString();
		} catch (Exception e) {
			throw new UnableToTransformToMarkdownException(e);
		}
	}
}

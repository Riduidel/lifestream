package org.ndx.lifestream.utils.transform;

import java.io.StringReader;
import java.io.StringWriter;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import static org.ndx.lifestream.utils.transform.MarkdownTransformerLazyLoader.getMarkdownTransformer;
import static org.ndx.lifestream.utils.transform.XHTMLTransformerLazyLoader.getXHTMLTransformer;

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
		String validXhtml = transformToValidXhtml(html);
		return transformValidXhtml(validXhtml);
	}

	public static String transformToValidXhtml(String html) {
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties properties = cleaner.getProperties();
			properties.setAdvancedXmlEscape(true);
			properties.setCharset("UTF-8");
			properties.setOmitDoctypeDeclaration(false);
			properties.setOmitHtmlEnvelope(false);
			properties.setOmitXmlDeclaration(false);

			TagNode node = cleaner.clean(html);
			Document output = new DomSerializer(properties).createDOM(node);


			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(output);
			getXHTMLTransformer().transform(source, result);
			String xmlString = sw.toString();
			return xmlString;
		} catch(Exception e) {
			throw new UnableToTransformToValidXHTML(e);
		}
	}

	/**
	 * Transform valid XHTML to Markdown. What is valid XHTML ? You're kidding,
	 * aren't you ? Well, see the tests ... or do consider that each element in
	 * valid xhtml include the namespace declaration. So it starts with h:html,
	 * contain h:body, and so on ...
	 *
	 * @see http://stackoverflow.com/a/59710/15619
	 * @param sourceXhtml
	 * @return a valid Markdown file
	 */
	public static String transformValidXhtml(String sourceXhtml) {
		try {
			SAXSource ss = NonDownloadingSaxSource.getNonDownloadingSaxSource(sourceXhtml);

	        StringWriter result = new StringWriter();
	        getMarkdownTransformer().transform(ss, new StreamResult(result));

	        return result.toString();
		} catch (Exception e) {
			throw new UnableToTransformToMarkdownException(e);
		}
	}
}

package org.ndx.lifestream.utils.transform;

import javax.xml.parsers.ParserConfigurationException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.JDomSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Document;
import org.ndx.lifestream.utils.Constants;

public class HtmlToJDOM {
	public synchronized static Document transformToValidJDom(String html) throws ParserConfigurationException {
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
		Document output = new JDomSerializer(properties).createJDom(node);
		return output;
	}


}

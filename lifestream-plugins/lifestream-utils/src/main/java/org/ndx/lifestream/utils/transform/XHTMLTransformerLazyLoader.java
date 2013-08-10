package org.ndx.lifestream.utils.transform;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import static org.ndx.lifestream.utils.transform.TransformerFactoryLazyLoader.getFactory;

public class XHTMLTransformerLazyLoader {
	private static Transformer xhtmlTransformer;

	public static Transformer getXHTMLTransformer() {
		if(xhtmlTransformer==null) {
			// set up a transformer
			try {
				xhtmlTransformer = getFactory().newTransformer();
				xhtmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				xhtmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				xhtmlTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			} catch (TransformerConfigurationException e) {
				throw new UnableToTransformToValidXHTML(e);
			}
		}
		return xhtmlTransformer;
	}
}

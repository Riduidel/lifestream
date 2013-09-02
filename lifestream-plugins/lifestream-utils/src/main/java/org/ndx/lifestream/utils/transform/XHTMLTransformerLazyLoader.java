package org.ndx.lifestream.utils.transform;

import static org.ndx.lifestream.utils.transform.TransformerFactoryLazyLoader.getFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import org.ndx.lifestream.utils.Constants;

public class XHTMLTransformerLazyLoader {
	private static Transformer xhtmlTransformer;

	public static Transformer getXHTMLTransformer() {
		if(xhtmlTransformer==null) {
			// set up a transformer
			try {
				xhtmlTransformer = getFactory().newTransformer();
				xhtmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				xhtmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				xhtmlTransformer.setOutputProperty(OutputKeys.ENCODING, Constants.UTF_8);
			} catch (TransformerConfigurationException e) {
				throw new UnableToTransformToValidXHTML(e);
			}
		}
		return xhtmlTransformer;
	}
}

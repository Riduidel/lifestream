package org.ndx.lifestream.utils.transform;

import static org.ndx.lifestream.utils.transform.TransformerFactoryLazyLoader.getFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import org.ndx.lifestream.utils.Constants;

/**
 * Weird how Java classes are bad at multi-threading.
 * To avoid that, the {@link Transformer} is kept in a {@link ThreadLocal} object.
 * @author ndx
 *
 */
public class XHTMLTransformerLazyLoader {
	private static ThreadLocal<Transformer> xhtmlTransformer = new ThreadLocal<Transformer>() {
		@Override
		protected Transformer initialValue() {
			try {
				Transformer xhtmlTransformer = getFactory().newTransformer();
				xhtmlTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
				xhtmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				xhtmlTransformer.setOutputProperty(OutputKeys.ENCODING, Constants.UTF_8);
				return xhtmlTransformer;
			} catch(TransformerConfigurationException e) {
				throw new UnableToConfigureTransformerException(e);
			}
		}
	};

	public static Transformer getXHTMLTransformer() {
		return xhtmlTransformer.get();
	}
}

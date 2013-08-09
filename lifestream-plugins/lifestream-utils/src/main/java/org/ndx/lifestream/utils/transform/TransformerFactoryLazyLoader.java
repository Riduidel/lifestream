package org.ndx.lifestream.utils.transform;

import javax.xml.transform.TransformerFactory;

public class TransformerFactoryLazyLoader {

	private static TransformerFactory factory;

	public static TransformerFactory getFactory() {
		if(TransformerFactoryLazyLoader.factory==null) {
			TransformerFactoryLazyLoader.factory = TransformerFactory.newInstance();
		}
		return TransformerFactoryLazyLoader.factory;
	}
}

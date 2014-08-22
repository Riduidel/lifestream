package org.ndx.lifestream.wordpress.resolvers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dooapp.gaedo.finders.FinderCrudService;

public abstract class AbstractLinkResolver {
	private static final Logger logger = Logger.getLogger(AbstractLinkResolver.class.getName());

	public void resolve(FinderCrudService<Post,PostInformer> bookService, Post p) {
		try {
			Document d = HtmlToMarkdown.transformToValidXhtmlDocument(p.getText());
			if(d==null)
				return;
			NodeList allLinks = d.getElementsByTagName("a");
			// can't believe NodeList isn't even a fuckin iterable !
			for(int index = 0 ; index < allLinks.getLength(); index++) {
				Node node = allLinks.item(index);
				Node destination = node.getAttributes().getNamedItem("href");
				if(destination!=null) {
					// All right, node has a destination (hopefully)
					// Now check if this destination is any other link 
					final String url = destination.getNodeValue();
					resolve(bookService, p, url);
				}
			}
		} catch (ParserConfigurationException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.log(Level.SEVERE, "unable to load parser. No internal link will be rewritten", e);
			}
		}
	}

	protected abstract void resolve(FinderCrudService<Post, PostInformer> bookService, Post p, String url);

}

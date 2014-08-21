package org.ndx.lifestream.wordpress.resolvers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.ndx.lifestream.rendering.output.LinkUtils;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;
import com.dooapp.gaedo.utils.CollectionUtils;

public class InternalLinksResolver implements Resolver {
	private static final Logger logger = Logger.getLogger(InternalLinksResolver.class.getName());

	/**
	 * Resolve links from loaded Post to other loaded Posts. How ? well, that's
	 * quite simple : here, text has not yet been markdownified. As a
	 * consequence, we can load post text as xml and scan it for <a href>
	 * objects. When those objects's href are urls of other posts, we've found a
	 * link we can rewrite.
	 * @param bookService 
	 * 
	 * @param p
	 */
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
					List<Post> matching = CollectionUtils.asList(bookService.find().matching(new QueryBuilder<PostInformer>() {

						@Override
						public QueryExpression createMatchingExpression(PostInformer informer) {
							return informer.getSource().equalsTo(url);
						}
					}).getAll());
					if(matching.size()>0) {
						p.addInternalLinkTo(matching.get(0));
					}
				}
			}
		} catch (ParserConfigurationException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.log(Level.SEVERE, "unable to load parser. No internal link will be rewritten", e);
			}
		}
	}
}

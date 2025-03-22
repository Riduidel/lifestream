package org.ndx.lifestream.wordpress.resolvers;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Should resolve links to goodreads by first removing their ending, then, tryi,ng to see if they're in known links
 * @author ndx
 *
 */
public class GoodreadsResolver extends AbstractLinkResolver implements Resolver {

	private WordpressConfiguration configuration;

	public GoodreadsResolver(WordpressConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void resolve(FinderCrudService<Post, PostInformer> bookService, Post p, String url) {
		if(url.contains("goodreads.com/book")) {
			int lastSlash = url.lastIndexOf('/');
			int lastDot = url.lastIndexOf('.');
			// fix name, as goodreads seems to allow everything after the last dot
			if(lastDot>lastSlash) {
				url = url.substring(0, lastDot);
			}
			// now lookup in resolved links
			p.addLinkTo(configuration.getLinkResolver().resolve(configuration.getBaseFolder(), url));
		}
	}

}

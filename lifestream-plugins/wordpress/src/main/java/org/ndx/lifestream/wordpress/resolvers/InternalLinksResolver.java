package org.ndx.lifestream.wordpress.resolvers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;
import com.dooapp.gaedo.utils.CollectionUtils;

public class InternalLinksResolver extends AbstractLinkResolver implements Resolver {
	private static final Logger logger = Logger.getLogger(InternalLinksResolver.class.getName());


	/**
	 * Resolve links from loaded Post to other loaded Posts. How ? well, that's
	 * quite simple : here, text has not yet been markdownified. As a
	 * consequence, we can load post text as xml and scan it for &lt;a href&gt;
	 * objects. When those objects's href are urls of other posts, we've found a
	 * link we can rewrite.
	 * @param bookService 
	 * @param p
	 */
	@Override
	protected void resolve(ExecutorService executor, FinderCrudService<Post, PostInformer> bookService, Post p, final String url) {
		List<Post> matching = CollectionUtils.asList(bookService.find().matching(new QueryBuilder<PostInformer>() {

			@Override
			public QueryExpression createMatchingExpression(PostInformer informer) {
				return informer.getSource().equalsTo(url);
			}
		}).getAll());
		if(matching.size()>0) {
			p.addLinkTo(p);
		}
	}
}

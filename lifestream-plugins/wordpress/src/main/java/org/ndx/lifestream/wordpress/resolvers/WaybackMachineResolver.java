package org.ndx.lifestream.wordpress.resolvers;

import java.util.concurrent.ExecutorService;

import org.ndx.lifestream.configuration.LinkResolver;
import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.gargoylesoftware.htmlunit.WebClient;

public class WaybackMachineResolver extends AbstractLinkResolver implements Resolver {

	private WebClient client;
	private WordpressConfiguration configuration;
	private LinkResolver resolver;

	public WaybackMachineResolver(WebClient client,
			WordpressConfiguration configuration) {
		this.client = client;
		this.configuration = configuration;
		this.resolver = configuration.getLinkResolver();
	}

	/**
	 * Only links that are neither internal, nor goodreads books, will be resolved against
	 * internet wayback machine web service (if not already known by link resolver).
	 * Well, in fact, all three conditions can be summed up as one test : is link present in 
	 * resolver data ? If not, try to load its history from wayback machine
	 * @param bookService
	 * @param p
	 * @param url
	 */
	@Override
	protected void resolve(ExecutorService executor,
			FinderCrudService<Post, PostInformer> bookService, final Post p, final String url) {
		if(!resolver.containsKey(url)) {
			// as web service call is long, push it into thread group
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					resolver.preventRottingWithWaybackMachine(client, url);
					p.addLinkTo(resolver.resolve(configuration.getBaseFolder(), url));
				}
			});
		}
	}

}

package org.ndx.lifestream.wordpress.resolvers;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.microsoft.playwright.Page;

public class MultiResolver {

	private final Page client;
	public MultiResolver(Page client, WordpressConfiguration configuration) {
		super();
		this.client = client;
		this.configuration = configuration;
	}

	private final WordpressConfiguration configuration;

	public void resolveIn(ExecutorService executor, FinderCrudService<Post, PostInformer> service) {
		for (Post p : service.findAll()) {
			resolveLazily(executor, service, p);
		}
	}

	private void resolveLazily(final ExecutorService executor, final FinderCrudService<Post, PostInformer> service, final Post p) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				resolve(executor, service, p);
			}
		});
	}

	protected void resolve(ExecutorService executor, FinderCrudService<Post, PostInformer> bookService, Post p) {
		for(Resolver r : getResolvers()) {
			r.resolve(executor, bookService, p);
		}
	}

	private Collection<Resolver> getResolvers() {
		return Arrays.asList(
						new InternalLinksResolver() 
						, new ShortCodeResolver(client, configuration) 
						, new GoodreadsResolver(configuration)
//						, new WaybackMachineResolver(client, configuration)
						);
	}

}

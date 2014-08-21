package org.ndx.lifestream.wordpress.resolvers;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.gargoylesoftware.htmlunit.WebClient;

public class MultiResolver {

	private final WebClient client;
	public MultiResolver(WebClient client, WordpressConfiguration configuration) {
		super();
		this.client = client;
		this.configuration = configuration;
	}

	private final WordpressConfiguration configuration;

	public void resolveIn(ExecutorService executor, FinderCrudService<Post, PostInformer> bookService) {
		for (Post p : bookService.findAll()) {
			resolveLazily(executor, bookService, p);
		}
	}

	private void resolveLazily(ExecutorService executor, final FinderCrudService<Post, PostInformer> bookService, final Post p) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				resolve(bookService, p);
			}
		});
	}

	protected void resolve(FinderCrudService<Post, PostInformer> bookService, Post p) {
		for(Resolver r : getResolvers()) {
			r.resolve(bookService, p);
		}
	}

	private Collection<Resolver> getResolvers() {
		return Arrays.asList(new InternalLinksResolver(), new ShortCodeResolver(client, configuration));
	}

}

package org.ndx.lifestream.wordpress.resolvers;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.StreamSupport;

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

	public void resolveIn(FinderCrudService<Post, PostInformer> service) {
		StreamSupport.stream(service.findAll().spliterator(), true)
			.forEach(post -> resolve(service, post));
	}

	protected void resolve(FinderCrudService<Post, PostInformer> bookService, Post p) {
		getResolvers().stream().parallel()
			.forEach(r -> r.resolve(bookService, p));
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

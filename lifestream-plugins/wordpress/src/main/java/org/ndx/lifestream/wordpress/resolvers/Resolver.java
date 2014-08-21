package org.ndx.lifestream.wordpress.resolvers;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

public interface Resolver {
	public void resolve(FinderCrudService<Post,PostInformer> bookService, Post p);
}

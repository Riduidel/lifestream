package org.ndx.lifestream.wordpress.resolvers;

import java.util.concurrent.ExecutorService;

import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

public interface Resolver {
	public void resolve(ExecutorService executor, FinderCrudService<Post,PostInformer> bookService, Post p);
}

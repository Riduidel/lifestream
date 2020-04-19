package org.ndx.lifestream.wordpress.resolvers;

import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

public abstract class AbstractLinkResolver {
	private static final Logger logger = Logger.getLogger(AbstractLinkResolver.class.getName());

	public void resolve(ExecutorService executor, FinderCrudService<Post,PostInformer> bookService, Post p) {
		Document d = Jsoup.parse(p.getText());
		if(d==null)
			return;
		Elements allLinks = d.select("a");
		// can't believe NodeList isn't even a fuckin iterable !
		for(Element e : allLinks) {
			String url = e.attr("href");
			if(url!=null) {
				// All right, node has a destination (hopefully)
				// Now check if this destination is any other link 
				resolve(executor, bookService, p, url);
			}
		}
	}

	protected abstract void resolve(ExecutorService executor, FinderCrudService<Post, PostInformer> bookService, Post p, String url);

}

package org.ndx.lifestream.wordpress.resolvers;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.rendering.output.Freemarker;
import org.ndx.lifestream.rendering.output.UnableToRenderException;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.gargoylesoftware.htmlunit.WebClient;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TweetLoader implements CacheLoader {
	private static Template template;
	
	static {
		Configuration freemarker = Freemarker.getConfiguration();
		freemarker.setClassForTemplateLoading(TweetLoader.class, "");
		try {
			template = freemarker.getTemplate("twitter_status.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final String tweetId;
	private final String url;
	/**
	 * we create a Twitter object that is recognized as unauthorized by design 
	 */
	private Twitter twitter;
	private WebClient client;
	

	public TweetLoader(WebClient client, WordpressConfiguration configuration, String tweetId, String url) {
		this.client = client;
		this.tweetId = tweetId;
		this.url = url;
		this.twitter = new TwitterFactory(configuration.getTwitterConfiguration()).getInstance();
	}

	@Override
	public String load() throws Exception {
		try {
			// using twitter factory constructor to ahve anonymous access (read https://groups.google.com/forum/#!searchin/twitter4j/anonymous/twitter4j/XhsEXPGpwbA/ZBGarxpEW9oJ)
			ResponseList<Status> response = twitter.lookup(new long[] {Long.parseLong(tweetId)});
			for(Status status : response) {
				// we've got our status. Let's create some HTML from it
				// And for that, let's use some template !
				Map<String, Object> parameters = new TreeMap<String, Object>();
				parameters.put("status", status);
				return Freemarker.render(template, parameters);
			}
		} catch(IllegalStateException e) {
			if (ShortCodeResolver.logger.isLoggable(Level.SEVERE)) {
				ShortCodeResolver.logger.log(Level.SEVERE, "Seems like twitter access is not correctly configured.\n"
								+ "Please take a look at the ugly details for more infos : http://twitter4j.org/en/configuration.html\n"
								+ "And pelase configure access through either system properties or environment variables\n"
								+ "Finally, app is registered at https://apps.twitter.com/app/7646367/keys", e);
			}
			throw e;
		}
		return url;
	}
}
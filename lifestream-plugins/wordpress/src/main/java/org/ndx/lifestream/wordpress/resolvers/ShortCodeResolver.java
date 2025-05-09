package org.ndx.lifestream.wordpress.resolvers;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.utils.ThreadLocalPattern;
import org.ndx.lifestream.wordpress.Post;
import org.ndx.lifestream.wordpress.PostInformer;
import org.ndx.lifestream.wordpress.WordpressConfiguration;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.microsoft.playwright.Page;

/**
 * Tries to detect and transform as much shortcodes as possible
 * @author ndx
 *
 */
public class ShortCodeResolver implements Resolver {
	static final Logger logger = Logger.getLogger(ShortCodeResolver.class.getName());
	private static final String GIST_GITHUB_COM = "gist.github.com/";
	/**
	 * That regexp was built using http://www.regexplanet.com/advanced/java/index.html and test strings
	 * <pre>Et vous pouvez même lui demander :https://gist.github.com/Riduidel/5337125</pre>
	 * <pre>nnue des composants Maven. D'où cette petite méthode qui facilite bien la vie : [gist https://gist.github.com/2df79e859bfbf83c0e5c /]</pre>
	 * <pre>L'une des toutes premières lignes de rss-bridge est https://gist.github.com/Riduidel/9113030 Du coup, quand on développe un bridge, au début, on ne comprend pas (moi au moins) </pre>
	 * <pre>L'une des toutes premières lignes de rss-bridge est http://gist.github.com/Riduidel/9113030</pre> (here there is no httpS)
	 * <pre>nnue des composants Maven. D'où cette petite méthode qui facilite bien la vie : [gist https://gist.github.com/2df79e859bfbf83c0e5c] [/gist]</pre>
	 * <pre>Et dix minutes plus tard ... [gist]Riduidel/c7705854a21a5800304e[/gist]</pre>
	 * 
	 */
	private static ThreadLocal<Pattern> gistDetectorPattern = new ThreadLocalPattern("(?:\\[gist\\]|(?:\\[gist\\s*)?https?://gist.github.com/)([\\d\\w/]+)\\s?\\]?(?:\\[/gist\\])?");
	
	private static ThreadLocal<Pattern> tweetDetectorPattern = new ThreadLocalPattern("https?://twitter.com/([^/]+)/(?:status|statuses)/([\\d\\w]+)");

	public class Gist implements Decoder {
		/**
		 * Locate each [gist ...] text or even simpler http://gist... and try to replace it
		 * @see Decoder#decode(java.lang.String)
		 */
		@Override
		public String decode(String text) {
			Matcher matcher = gistDetectorPattern.get().matcher(text);
			while(matcher.find()) {
				String found = matcher.group(0);
				String url = matcher.group(1);
				// notice URL never contains the gist.github.com domain BY DESIGN
				url = "https://"+GIST_GITHUB_COM + url;
				String gistText = getGistAt(url);
				text = text.replace(found, gistText);
			}
			return text;
		}
	}

	public interface Decoder {
		public String decode(String text);
	}
	
	public Collection<Decoder> decoders = Arrays.asList(
					(Decoder) new Gist());

	private Page client;
	private WordpressConfiguration configuration;

	public ShortCodeResolver(Page client, WordpressConfiguration configuration) {
		this.client = client;
		this.configuration = configuration;
	}

	@Override
	public void resolve(FinderCrudService<Post, PostInformer> bookService, Post p) {
		String text = p.getText();
		if(text==null||text.trim().length()==0)
			return;
		for(Decoder decoder : decoders) {
			text = decoder.decode(text);
		}
		p.setText(text);
	}

	/**
	 * Download and cache gist raw content
	 * @param url
	 * @return
	 */
	protected String getGistAt(final String url) {
		try {
			String filename = url.substring(url.indexOf(GIST_GITHUB_COM));
			FileObject storage = configuration.getCacheFolder().resolveFile(filename);
			return configuration.getFromCacheOrLoad(new GistLoader(client, url), storage, -1);
		} catch(Exception e) {
			return url;
		}
	}
}

package org.ndx.lifestream.wordpress.resolvers;

import org.ndx.lifestream.configuration.CacheLoader;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class GistLoader implements CacheLoader {
	private final String url;
	private WebClient client;

	public GistLoader(WebClient client, String url) {
		this.client = client;
		this.url = url;
	}

	@Override
	public String load() throws Exception {
		// used to resoolve potential redirections
		Page loaded = client.getPage(url);
		return "<pre class='github'>\n<code>\n"+
						client.getPage(loaded.getUrl().toString()+"/raw").getWebResponse().getContentAsString()
						+"\n</code>"
						+"\n</pre>";
	}
}
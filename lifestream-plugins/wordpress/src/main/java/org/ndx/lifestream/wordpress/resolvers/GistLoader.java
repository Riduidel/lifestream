package org.ndx.lifestream.wordpress.resolvers;

import org.ndx.lifestream.configuration.CacheLoader;

import com.microsoft.playwright.Page;

public class GistLoader implements CacheLoader {
	private final String url;
	private Page client;

	public GistLoader(Page client, String url) {
		this.client = client;
		this.url = url;
	}

	@Override
	public String load() throws Exception {
		// used to resolve potential redirections
		client.navigate(url);
		client.navigate(client.url().toString()+"/raw");
		String text = client.content();
		return "<pre class='github'>\n<code>\n"+
						text
						+"\n</code>"
						+"\n</pre>";
	}
}
package org.ndx.lifestream.wordpress.resolvers;

import org.ndx.lifestream.configuration.CacheLoader;
import org.ndx.lifestream.rendering.model.Page;
import org.openqa.selenium.WebDriver;

public class GistLoader implements CacheLoader {
	private final String url;
	private WebDriver client;

	public GistLoader(WebDriver client, String url) {
		this.client = client;
		this.url = url;
	}

	@Override
	public String load() throws Exception {
		// used to resolve potential redirections
		client.get(url);
		client.get(client.getCurrentUrl().toString()+"/raw");
		String text = client.getPageSource();
		return "<pre class='github'>\n<code>\n"+
						text
						+"\n</code>"
						+"\n</pre>";
	}
}
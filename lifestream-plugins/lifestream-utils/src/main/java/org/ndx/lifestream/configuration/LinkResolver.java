package org.ndx.lifestream.configuration;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.vfs2.FileObject;
import org.ndx.lifestream.rendering.model.Input.Headers;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.notifications.WriteEvent;
import org.ndx.lifestream.rendering.notifications.WriteListener;
import org.ndx.lifestream.rendering.path.ExternalLink;
import org.ndx.lifestream.rendering.path.RelativeLink;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

public class LinkResolver implements WriteListener {
	private final FileObject storePath;
	private Map<String, String> resolvedLinks = new TreeMap<>();
	private final AbstractConfiguration configuration;

	public LinkResolver(AbstractConfiguration abstractConfiguration, FileObject storePath) {
		super();
		this.configuration = abstractConfiguration;
		this.storePath = storePath;
		try {
			if (storePath.exists()) {
				// At startup, immediatly read properties and transform them to
				// meaningfull data
				Properties p = new Properties();
				InputStream inputStream = storePath.getContent().getInputStream();
				p.loadFromXML(inputStream);
				for (Map.Entry e : p.entrySet()) {
					resolvedLinks.put(e.getKey().toString(), e.getValue().toString());
				}
			}
		} catch (Exception e) {
			throw new UnableToLoadLinksCatalogException(e);
		}
	}

	/**
	 * Save updated link list to "a file" ...
	 */
	public void save() {
		try {
			Properties p = new Properties();
			for (Map.Entry e : resolvedLinks.entrySet()) {
				p.put(e.getKey().toString(), e.getValue().toString());
			}
			OutputStream outputStream = storePath.getContent().getOutputStream(false);
			p.storeToXML(outputStream, "written on " + new Date());
		} catch (Exception e) {
			throw new UnableToLoadLinksCatalogException(e);
		}
	}

	/**
	 * Each time an input is written, its source url is associated with its path
	 * and stored for alter saving ...
	 * 
	 * @see org.ndx.lifestream.rendering.notifications.WriteListener#inputWritten(org.ndx.lifestream.rendering.notifications.WriteEvent)
	 */
	@Override
	public void inputWritten(WriteEvent event) {
		String inputSourceUrl = event.getInput().getAdditionalHeaders().get(Headers.SOURCE);
		if(inputSourceUrl!=null) {
			resolvedLinks.put(inputSourceUrl, event.getRelativePath());
		}
	}

	/**
	 * Resolves an URL into a ... thingie 
	 */
	public Linkable resolve(FileObject fileObject, String url) {
		if(resolvedLinks.containsKey(url)) {
			String destination = resolvedLinks.get(url);
			if(destination.endsWith(".md")) {
				return new RelativeLink(url, destination);
			} else {
				return new ExternalLink(url, destination);
			}
		} else {
			return new ExternalLink(url, url);
		}
	}

	public boolean containsKey(Object key) {
		return resolvedLinks.containsKey(key);
	}

	/**
	 * Prevent link rotting by ensuring link is not dead through Internet Wayback machine API call
	 */
	public void preventRottingWithWaybackMachine(WebClient client, String url) {
		try {
			WebRequest request = new WebRequest(new URL("http://archive.org/wayback/available"));
		} catch (MalformedURLException e) {
			throw new UnableToQueryWaybackMachineException(e);
		}
	}

}

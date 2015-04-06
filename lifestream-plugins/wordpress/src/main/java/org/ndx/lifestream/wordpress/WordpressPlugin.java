package org.ndx.lifestream.wordpress;

import java.io.File;

import org.ndx.lifestream.configuration.Configuration;
import org.ndx.lifestream.plugin.AbstractLifestreamPlugin;
import org.ndx.lifestream.rendering.model.InputLoader;

import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Nicolas
 * @goal wordpress
 * @phase process-resources
 * @requiresDependencyResolution runtime
 */
public class WordpressPlugin extends AbstractLifestreamPlugin<Post, WordpressConfiguration> {
	
	/**
	 * Path for caching any kind of data, be it data downloaded from the web, or built by plugin
	 * @parameter
	 *            default-value="${project.basedir}/.cache/"
	 */
	protected File cache;
	/**
	 * username on Wordpress site
	 *
	 * @parameter alias="username"
	 * @required
	 */
	protected String username;


	/**
	 * password on Wordpress site
	 *
	 * @parameter alias="password"
	 * @required
	 */
	protected String password;

	/**
	 * wordpress site address
	 *
	 * @parameter alias="site"
	 * @required
	 */
	protected String site;

	/**
	 * Currently used rendering mode
	 * @parameter alias="mode" default-value="gollum"
	 */
	protected String modeName;

	/**
	 * Output file where those markdown generated files will be written.
	 * Notice the goodreads subfolder is automatically added to each file, so no need to add it by hand !
	 *
	 * @parameter
	 *            default-value="${project.basedir}/src/main/site/markdown/"
	 */
	protected File output;
	
	/**
	 * Twitter access token for Riduidel's lifestream twitter application
	 * @parameter 
	 */
	protected String twitterAccessToken;
	
	/**
	 * Twitter access token secret for Riduidel's lifestream twitter application
	 * @parameter 
	 */
	protected String twitterAccessTokenSecret;

	protected final File getOutput() {
		return output;
	}

	protected final String getModeName() {
		return modeName;
	}

	@Override
	protected InputLoader<Post, WordpressConfiguration> loadInputLoader() {
		return new Wordpress();
	}

	@Override
	protected WordpressConfiguration createConfiguration() {
		return new WordpressConfiguration(getCacheObject()).withLogin(username).withPassword(password).withSite(site).withTwitterConfiguration(createTwitterConfiguration());
	}

	protected twitter4j.conf.Configuration createTwitterConfiguration() {
		ConfigurationBuilder builder =  new ConfigurationBuilder();
		builder.setOAuthConsumerKey(Configuration.Twitter.CONSUMER_KEY);
		builder.setOAuthConsumerSecret(Configuration.Twitter.CONSUMER_SECRET);
		builder.setOAuthAccessToken(getTwitterAccessToken());
		builder.setOAuthAccessTokenSecret(getTwitterAccessTokenSecret());
		return builder.build();
	}

	@Override
	protected File getCache() {
		return cache;
	}

	/**
	 * @return the twitterAccessToken
	 * @category getter
	 * @category twitterAccessToken
	 */
	public String getTwitterAccessToken() {
		return twitterAccessToken;
	}

	/**
	 * @return the twitterAccessTokenSecret
	 * @category getter
	 * @category twitterAccessTokenSecret
	 */
	public String getTwitterAccessTokenSecret() {
		return twitterAccessTokenSecret;
	}

}

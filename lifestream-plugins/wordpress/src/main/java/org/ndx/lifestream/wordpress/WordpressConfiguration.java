package org.ndx.lifestream.wordpress;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.configuration.Configuration;

public class WordpressConfiguration extends AbstractConfiguration implements Configuration {

	private String login;
	private String password;
	private String site;
	private int threadCount = 2;

	public WordpressConfiguration(FileObject baseFolder) {
		super(baseFolder, "wordpress/");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public WordpressConfiguration withLogin(String login) {
		setLogin(login);
		return this;
	}

	public WordpressConfiguration withPassword(String password) {
		setPassword(password);
		return this;
	}

	public WordpressConfiguration withSite(String site) {
		setSite(site);
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSiteLoginPage(String domain) {
		if(!domain.endsWith("/")) {
			domain += "/";
		}
		return domain + "wp-login.php";
	}

	public String getSiteExportPage() {
		return site + "wp-admin/export.php?type=export";
	}

	public String getCloudExportPage(String domain) {
		domain = domain.substring(domain.indexOf("//")+2, domain.lastIndexOf('/'));
		return "https://wordpress.com/export/"+ domain;
	}

	@Override
	public FileObject getCachedExport() throws FileSystemException {
		return getCacheFolder().resolveFile("export.xml");
	}

	/**
	 * @return the threadCount
	 * @category getter
	 * @category threadCount
	 */
	public int getThreadCount() {
		return threadCount;
	}

	/**
	 * @param threadCount the threadCount to set
	 * @category setter
	 * @category threadCount
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public WordpressConfiguration withTwitterConfiguration(twitter4j.conf.Configuration configuration) {
		setTwitterConfiguration(configuration);
		return this;
	}
	
	@Override
	public long getCacheTimeout() {
		if(getSite().contains("wordpress.com")) {
			// As long as I don't know how to login into wordpress.com, I will use a cached version
			return -1;
		} else {
			return super.getCacheTimeout();
		}
	}
}

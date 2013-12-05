package org.ndx.lifestream.wordpress;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.configuration.Configuration;

public class WordpressConfiguration extends AbstractConfiguration implements Configuration {

	private String login;
	private String password;
	private String site;

	public WordpressConfiguration(FileObject baseFolder) {
		super(baseFolder, CACHE_BASE_PATH+"wordpress/");
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

	public String getSiteLoginPage() {
		return site + "wp-login.php";
	}

	public String getSiteExportPage() {
		return site + "wp-admin/export.php?type=export";
	}

	public FileObject getCachedExport() throws FileSystemException {
		return getCacheFolder().resolveFile("export.xml");
	}

}

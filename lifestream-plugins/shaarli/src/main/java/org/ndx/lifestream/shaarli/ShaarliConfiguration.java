package org.ndx.lifestream.shaarli;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.configuration.AbstractConfiguration;

public class ShaarliConfiguration extends AbstractConfiguration {

	private String login;
	private String password;
	private String site;

	public ShaarliConfiguration(FileObject baseFolder) {
		super(baseFolder, CACHE_BASE_PATH+"shaarli/");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ShaarliConfiguration withLogin(String login) {
		setLogin(login);
		return this;
	}

	public ShaarliConfiguration withPassword(String password) {
		setPassword(password);
		return this;
	}

	public ShaarliConfiguration withSite(String site) {
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
		return site + "?do=login";
	}

	public String getSiteExportPage() {
		return site + "?do=export&what=all";
	}

	public FileObject getCachedExport() throws FileSystemException {
		return getCacheFolder().resolveFile("bookmarks.html");
	}

	public String getAuthenticationFailureMessage() {
		return "unable to login into "+getSite()+" using following credentials\n\tlogin : "+getLogin()+"\n\tpassword : \""+getPassword()+"\"";
	}

	public String getDownloadFailureMessage() {
		return "unable to download from "+getSite()+" using following credentials\n\tlogin : "+getLogin()+"\n\tpassword : HIDDEN THERE";
	}

}

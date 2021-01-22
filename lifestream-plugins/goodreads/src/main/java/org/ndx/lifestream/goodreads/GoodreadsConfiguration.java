package org.ndx.lifestream.goodreads;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.configuration.Configuration;

public class GoodreadsConfiguration extends AbstractConfiguration implements Configuration {
	private String mail;
	private String password;
	private int threadCount = 2;
	public static final String GOODREADS_BASE = "https://www.goodreads.com/";
	public static final String SIGN_IN = GOODREADS_BASE+"user/sign_in";
	/**
	 * Yeah this is my dev key. Use it with care
	 */
	public static final String DEVELOPPER_KEY = "vzlZHr69We4utsOyP508tg";

	public GoodreadsConfiguration(FileObject baseFolder) {
		super(baseFolder, "goodreads");
	}

	public FileObject getCachedExport() throws FileSystemException {
		return getCacheFolder().resolveFile("export.csv");
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String username) {
		this.mail = username;
	}
	
	public GoodreadsConfiguration withUsername(String username) {
		setMail(username);
		return this;
	}
	
	public GoodreadsConfiguration withPassword(String password) {
		setPassword(password);
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getThreadCount() {
		return threadCount ;
	}
}

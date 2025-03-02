package org.ndx.lifestream.goodreads;

import static org.hamcrest.MatcherAssert.assertThat;


import org.apache.commons.vfs2.FileSystemException;
import org.hamcrest.core.IsNull;
import org.ndx.lifestream.rendering.output.VFSHelper;

public class ConnectionUtils {

	static String password;
	static String mail;

	static {
		ConnectionUtils.mail = System.getProperty("goodreads.mail");
		assertThat(ConnectionUtils.mail, IsNull.notNullValue());
		ConnectionUtils.password = System.getProperty("goodreads.password");
		assertThat(ConnectionUtils.password, IsNull.notNullValue());
	}

	public static  GoodreadsConfiguration createConfiguration() {
		return new GoodreadsConfiguration(VFSHelper.getRunningDir_for_tests_only()).withUsername(ConnectionUtils.mail).withPassword(ConnectionUtils.password);
	}

}

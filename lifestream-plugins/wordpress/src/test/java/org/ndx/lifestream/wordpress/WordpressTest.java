package org.ndx.lifestream.wordpress;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

public class WordpressTest {
	private String site;
	private String login;
	private String password;
	private Wordpress tested;

	@Before
	public void loadCredentials() {
		login = System.getProperty("wordpress.login");
		assertThat(login, IsNull.notNullValue());
		password = System.getProperty("wordpress.password");
		assertThat(password, IsNull.notNullValue());
		site = System.getProperty("wordpress.password");
		assertThat(site, IsNull.notNullValue());

		tested = new Wordpress();
		tested.login = login;
		tested.password = password;
		tested.site = site;
	}

	@Test
	public void canLoadXMLData() {
		String xml = tested.loadXML();
		assertThat(xml, IsNull.notNullValue());
	}
}

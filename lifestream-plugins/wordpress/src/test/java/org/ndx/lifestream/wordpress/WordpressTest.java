package org.ndx.lifestream.wordpress;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ndx.lifestream.utils.web.WebClientFactory;

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
		site = System.getProperty("wordpress.address");
		assertThat(site, IsNull.notNullValue());

		tested = new Wordpress();
		tested.login = login;
		tested.password = password;
		tested.site = site;
	}

	@Test @Ignore
	public void canLoadXMLData() {
		String xml = tested.loadXML(WebClientFactory.getWebClient());
		assertThat(xml, IsNull.notNullValue());
	}

	@Test
	public void canTransformStringInPostCollection() throws IOException {
		InputStream testXmlFile = getClass().getResourceAsStream("/riduidel039swordpress.wordpress.2013-08-16.xml");
		Collection<Post> posts = tested.buildPostCollection(testXmlFile);
		assertThat(posts.size(), IsNot.not(0));
	}
}

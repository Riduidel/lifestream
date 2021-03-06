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
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;

public class WordpressTest {
	private String site;
	private String login;
	private String password;
	private Wordpress tested;
	private WordpressConfiguration configuration;

	@Before
	public void loadCredentials() {
		login = System.getProperty("wordpress.login");
		assertThat(login, IsNull.notNullValue());
		password = System.getProperty("wordpress.password");
		assertThat(password, IsNull.notNullValue());
		site = System.getProperty("wordpress.address");
		assertThat(site, IsNull.notNullValue());

		configuration = new WordpressConfiguration(VFSHelper.getRunningDir_for_tests_only()).withLogin(login).withPassword(password).withSite(site);

		tested = new Wordpress();
	}

	@Test @Ignore
	public void canLoadXMLData() {
		String xml = tested.downloadXMLFromSelfhostedWordpress(WebClientUtils.getWebClient(), configuration);
		assertThat(xml, IsNull.notNullValue());
	}

	@Test @Ignore
	public void canTransformStringInPostCollection() throws IOException {
		InputStream testXmlFile = getClass().getResourceAsStream("/riduidel039swordpress.wordpress.2013-12-04.xml");
		FinderCrudService<Post, PostInformer> posts = tested.buildPostCollection(WebClientUtils.getWebClient(), testXmlFile, configuration);
		assertThat(CollectionUtils.asList(posts.findAll()).size(), IsNot.not(0));
	}
}

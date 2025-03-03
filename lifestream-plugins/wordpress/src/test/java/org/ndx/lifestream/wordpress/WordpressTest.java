package org.ndx.lifestream.wordpress;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.test.PlaywrightTest;
import org.ndx.lifestream.utils.web.WebClientUtils;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;

class WordpressTest extends PlaywrightTest {
	private String site;
	private String login;
	private String password;
	private Wordpress tested;
	private WordpressConfiguration configuration;

	@BeforeEach
	void loadCredentials() {
		login = System.getProperty("wordpress.login");
		assertThat(login, IsNull.notNullValue());
		password = System.getProperty("wordpress.password");
		assertThat(password, IsNull.notNullValue());
		site = System.getProperty("wordpress.address");
		assertThat(site, IsNull.notNullValue());

		configuration = new WordpressConfiguration(VFSHelper.getRunningDir_for_tests_only()).withLogin(login).withPassword(password).withSite(site);

		tested = new Wordpress();
	}

	@Test
	@Disabled
	void canLoadXMLData() {
		String xml = tested.downloadXMLFromSelfhostedWordpress(browser.newPage(), configuration);
		assertThat(xml, IsNull.notNullValue());
	}

	@Test
	@Disabled
	void canTransformStringInPostCollection() throws IOException {
		InputStream testXmlFile = getClass().getResourceAsStream("/riduidel039swordpress.wordpress.2013-12-04.xml");
		FinderCrudService<Post, PostInformer> posts = tested.buildPostCollection(browser.newPage(), testXmlFile, configuration);
		assertThat(CollectionUtils.asList(posts.findAll()).size(), IsNot.not(0));
	}
}

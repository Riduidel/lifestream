package org.ndx.lifestream.shaarli;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.test.PlaywrightTest;
import org.ndx.lifestream.utils.web.WebClientUtils;

class ShaarliTest extends PlaywrightTest {

	private String site;
	private String login;
	private String password;
	private Shaarli tested;
	private ShaarliConfiguration configuration;

	@BeforeEach
	void loadCredentials() {
		login = System.getProperty("shaarli.login");
		assertThat(login, IsNull.notNullValue());
		password = System.getProperty("shaarli.password");
		assertThat(password, IsNull.notNullValue());
		site = System.getProperty("shaarli.address");
		assertThat(site, IsNull.notNullValue());

		configuration = new ShaarliConfiguration(VFSHelper.getRunningDir_for_tests_only()).withLogin(login).withPassword(password).withSite(site);

		tested = new Shaarli();
	}

	@Test
	@Disabled
	void canLoadXMLData() {
		String xml = tested.downloadXML(browser, configuration);
		assertThat(xml, notNullValue());
		assertThat(xml.length(), not(0));
	}

	@Test
	void canTransformStringInPostCollection() throws IOException {
		InputStream testXmlFile = getClass().getResourceAsStream("/bookmarks_all_20131204_154343.html");
		String text = IOUtils.toString(testXmlFile);
		Document document = Jsoup.parse(text);
		Collection<MicroblogEntry> posts = tested.buildEntriesCollection(configuration, document);
		assertThat(posts.size(), IsNot.not(0));
	}

}

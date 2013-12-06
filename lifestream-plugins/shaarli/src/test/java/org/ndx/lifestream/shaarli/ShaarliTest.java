package org.ndx.lifestream.shaarli;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.transform.HtmlToMarkdown;
import org.ndx.lifestream.utils.web.WebClientFactory;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import static org.junit.Assert.assertThat;

public class ShaarliTest {

	private String site;
	private String login;
	private String password;
	private Shaarli tested;
	private ShaarliConfiguration configuration;

	@Before
	public void loadCredentials() {
		login = System.getProperty("shaarli.login");
		assertThat(login, IsNull.notNullValue());
		password = System.getProperty("shaarli.password");
		assertThat(password, IsNull.notNullValue());
		site = System.getProperty("shaarli.address");
		assertThat(site, IsNull.notNullValue());

		configuration = new ShaarliConfiguration(VFSHelper.getRunningDir()).withLogin(login).withPassword(password).withSite(site);

		tested = new Shaarli();
	}

	@Test @Ignore
	public void canLoadXMLData() {
		String xml = tested.downloadXML(WebClientFactory.getWebClient(), configuration);
		assertThat(xml, notNullValue());
		assertThat(xml.length(), not(0));
	}

	@Test
	public void canTransformStringInPostCollection() throws IOException, ParserConfigurationException, JDOMException {
		InputStream testXmlFile = getClass().getResourceAsStream("/bookmarks_all_20131204_154343.html");
		String text = IOUtils.toString(testXmlFile);
		DOMBuilder builder = new DOMBuilder();
		Document document = builder.build(HtmlToMarkdown.transformToValidXhtmlDocument(text));
		Collection<MicroblogEntry> posts = tested.buildEntriesCollection(document);
		assertThat(posts.size(), IsNot.not(0));
	}

}

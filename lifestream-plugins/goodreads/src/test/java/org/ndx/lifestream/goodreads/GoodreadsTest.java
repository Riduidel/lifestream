package org.ndx.lifestream.goodreads;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileSystemException;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.web.WebClientFactory;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;

import static org.junit.Assert.assertThat;

public class GoodreadsTest {
	private static String mail;
	private static String password;
	private GoodreadsConfiguration configuration;

	@BeforeClass
	public static void loadUserInfos() throws FileSystemException {
		mail = System.getProperty("goodreads.mail");
		assertThat(mail, IsNull.notNullValue());
		password = System.getProperty("goodreads.password");
		assertThat(password, IsNull.notNullValue());
	}

	private Goodreads tested;

	@Before
	public void loadCredentials() {
		configuration = new GoodreadsConfiguration(VFSHelper.getRunningDir()).withUsername(mail).withPassword(password);
		tested = new Goodreads();
	}

	@Test(expected=AuthenticationFailedException.class)
	public void cantReadBooksWithInvalidCredentials() throws Exception {
		configuration.setMail("an invalid login for test purpose, coming from https://github.com/Riduidel/lifestream");
		tested.loadCSV(WebClientFactory.getWebClient(), configuration);
	}

	@Test @Ignore
	public void canReadBooksAsListOfStringsFromServer() throws Exception {
		List<String[]> rows = tested.loadCSV(WebClientFactory.getWebClient(), configuration);
		assertThat(rows.size(), IsNot.not(0));
		// check all rows have the same number of columns, which is not 1
		assertColumnCountIsOK(rows);
	}

	private void assertColumnCountIsOK(List<String[]> rows) {
		int columntCount = 0;
		for (String[] row : rows) {
			if (columntCount == 0)
				columntCount = row.length;
			assertThat(row.length, Is.is(columntCount));
			assertThat(row.length, IsNot.not(1));
		}
	}

	/**
	 * Seems like we loose some CSV rows during parsing
	 */
	@Test
	public void noRowIsLostDuringCSVParsing() throws Exception {
		InputStream testCsvFile = getClass().getResourceAsStream("/goodreads_export_Riduidel_20310812.csv");
		String testedString = IOUtils.toString(testCsvFile);
		List<String[]> rows = tested.splitIntoRows(testedString);
		assertThat(rows.size(), IsNot.not(0));
		// check all rows have the same number of columns, which is not 1
		assertColumnCountIsOK(rows);
		FinderCrudService<BookInfos, BookInfosInformer> books = tested.buildBooksCollection(null /* no web client is given for faster test */, rows, configuration);
		Collection<BookInfos> all = CollectionUtils.asList(books.findAll());
		assertThat(all.size(), Is.is(rows.size()-1));
	}
}
package org.ndx.lifestream.goodreads;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.plugin.exceptions.AuthenticationFailedException;
import org.ndx.lifestream.utils.web.WebClientFactory;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;
import com.dooapp.gaedo.utils.CollectionUtils;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

public class GoodreadsTest {
	private GoodreadsConfiguration configuration;

	private Goodreads tested;

	@Before
	public void loadCredentials() {
		configuration = ConnectionUtils.createConfiguration();
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
}
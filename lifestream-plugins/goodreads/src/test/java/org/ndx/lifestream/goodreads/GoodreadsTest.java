package org.ndx.lifestream.goodreads;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class GoodreadsTest {
	private String mail;
	private String password;
	private Goodreads tested;

	@Before
	public void loadCredentials() {
		mail = System.getProperty("goodreads.mail");
		assertThat(mail, IsNull.notNullValue());
		password = System.getProperty("goodreads.password");
		assertThat(password, IsNull.notNullValue());

		tested = new Goodreads();
		tested.username = mail;
		tested.password = password;
	}

	@Test(expected=AuthenticationFailedException.class) @Ignore
	public void cantReadBooksWithInvalidCredentials() throws Exception {
		tested.username = "an invalid login for test purpose, coming from https://github.com/Riduidel/lifestream";
		tested.loadCSV();
	}

	@Test @Ignore
	public void canReadBooksAsListOfStringsFromServer() throws Exception {
		List<String[]> rows = tested.loadCSV();
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
		Collection<Book> books = tested.buildBooksCollection(rows);
		assertThat(books.size(), Is.is(rows.size()-1));
	}
}
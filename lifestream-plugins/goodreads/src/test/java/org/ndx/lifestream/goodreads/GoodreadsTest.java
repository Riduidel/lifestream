package org.ndx.lifestream.goodreads;

import java.util.List;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

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

	@Test(expected=AuthenticationFailedException.class)
	public void cantReadBooksWithInvalidCredentials() throws Exception {
		tested.username = "an invalid login for test purpose, coming from https://github.com/Riduidel/lifestream";
		tested.getCSV();
	}

	@Test
	public void canReadBooksAsListOfStringsFromServer() throws Exception {
		List<String[]> rows = tested.getCSV();
		assertThat(rows.size(), IsNot.not(0));
		// check all rows have the same number of columns, which is not 1
		int columntCount = 0;
		for (String[] row : rows) {
			if (columntCount == 0)
				columntCount = row.length;
			assertThat(row.length, Is.is(columntCount));
			assertThat(row.length, IsNot.not(1));
		}
	}
}
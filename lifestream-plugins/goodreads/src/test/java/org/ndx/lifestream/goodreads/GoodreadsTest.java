package org.ndx.lifestream.goodreads;

import java.util.List;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class GoodreadsTest {
	private String mail;
	private String password;

	@Before
	public void loadCredentials() {
		mail = System.getProperty("goodreads.mail");
		assertThat(mail, IsNull.notNullValue());
		password = System.getProperty("goodreads.password");
		assertThat(password, IsNull.notNullValue());
	}

	@Test
	public void canReadBooksAsListOfStringsFromServer() throws Exception {

		Goodreads exporter = new Goodreads();
		exporter.username = mail;
		exporter.password = password;

		List<String[]> rows = exporter.getCSV();
		assertThat(rows.size(), IsNot.not(0));
	}
}
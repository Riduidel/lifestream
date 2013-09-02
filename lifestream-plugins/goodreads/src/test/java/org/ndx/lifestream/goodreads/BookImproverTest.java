package org.ndx.lifestream.goodreads;

import static org.junit.Assert.assertThat;

import java.util.LinkedList;

import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ndx.lifestream.utils.web.WebClientFactory;

import com.gargoylesoftware.htmlunit.WebClient;

public class BookImproverTest {
	private static WebClient webClient;
	
	@BeforeClass public static void loadWebClient() {
		webClient = WebClientFactory.getWebClient();
	}
	
	@Test public void canImproveHunterAndHisPrey() throws Exception {
		Book toImprove = new Book();
		toImprove.isbn13 = "9782070448524";

		BookImprover tested = new BookImprover(webClient, toImprove, new LinkedList<Book>());
		ImprovedBook improved = tested.call();
		assertThat(improved.imageUrl, Is.is("http://d202m5krfqbpi5.cloudfront.net/books/1369382445m/17973574.jpg"));
		assertThat(improved.title, Is.is("Le chasseur et son ombre"));
	}

	@Test public void canImproveTheLastHero() throws Exception {
		Book toImprove = new Book();
		toImprove.isbn13 = "9782841722518";

		BookImprover tested = new BookImprover(webClient, toImprove, new LinkedList<Book>());
		ImprovedBook improved = tested.call();
		assertThat(improved.imageUrl, Is.is("http://d202m5krfqbpi5.cloudfront.net/books/1364826873m/3203074.jpg"));
		assertThat(improved.title, Is.is("Le Dernier héros (Les Annales du Disque-monde, #27)"));
		// TODO check membership of cycle
	}

}

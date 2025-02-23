package org.ndx.lifestream.goodreads;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.commons.vfs2.FileSystemException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ndx.lifestream.configuration.AbstractConfiguration;
import org.ndx.lifestream.goodreads.references.Reference;
import org.ndx.lifestream.plugin.GaedoEnvironmentProvider;
import org.ndx.lifestream.test.PlaywrightTest;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;
import com.microsoft.playwright.Page;

public class BookImproverTest extends PlaywrightTest {
	private static GoodreadsConfiguration configuration;
	private GaedoEnvironmentProvider goodreadsEnvironment;
	private TreeMap<String, Reference> references;

	@BeforeClass public static void loadConfiguration() throws FileSystemException {
		configuration = ConnectionUtils.createConfiguration();
	}

	@Before public void loadGaedo() throws IOException {
		goodreadsEnvironment = new GaedoEnvironmentProvider();
		references = new TreeMap<>();
	}

	@Test public void canImproveHunterAndHisPrey() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782070448524");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(toImprove, service,
				configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		// Don't forget there are ALSO authors infos there
		assertThat(returned.size(), is(4));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.bigImage, CoreMatchers.endsWith(".jpg"));
		assertThat(improved.getTitle(), Is.is("Le Chasseur et son ombre"));
	}

	@Test public void canImproveTheLastHero() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782841722518");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(toImprove, service,
				configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		assertThat(returned.size(), Is.is(6));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.getTitle(), Is.is("Le dernier Héros (Les Annales du Disque-Monde, #27)"));
		assertThat(improved.getSeries().size(), Is.is(2));
	}

	@Test public void canParserSerieDescriptionForBattleAngelAlita() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9781591162810");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(toImprove, service,
				configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		assertThat(returned.size(), Is.is(3));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.getTitle(), Is.is("Battle Angel Alita - Last Order, Vol. 4: Angel of Protest"));
		assertThat(improved.getSeries().size(), Is.is(1));
	}

	@Test public void canImproveoneOfVorkosiganSaga() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782277239253");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(toImprove, service,
				configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		assertThat(returned.size(), Is.is(4));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.getTitle(), Is.is("Un clone encombrant"));
		assertThat(improved.getSeries().size(), Is.is(2));
	}
}

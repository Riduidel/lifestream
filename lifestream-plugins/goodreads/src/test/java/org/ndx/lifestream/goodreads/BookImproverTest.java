package org.ndx.lifestream.goodreads;

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
import org.ndx.lifestream.rendering.output.VFSHelper;
import org.ndx.lifestream.utils.web.WebClientFactory;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.utils.CollectionUtils;
import com.gargoylesoftware.htmlunit.WebClient;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

public class BookImproverTest {
	private static WebClient webClient;
	private static AbstractConfiguration configuration;
	private GaedoEnvironmentProvider goodreadsEnvironment;
	private TreeMap<String, Reference> references;

	@BeforeClass public static void loadWebClient() throws FileSystemException {
		webClient = WebClientFactory.getWebClient();
		configuration = new GoodreadsConfiguration(VFSHelper.getRunningDir_for_tests_only());
	}

	@Before public void loadGaedo() throws FileSystemException {
		goodreadsEnvironment = new GaedoEnvironmentProvider();
		references = new TreeMap<>();
	}

	@Test public void canImproveHunterAndHisPrey() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782070448524");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(webClient, toImprove,
				service, configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		// Don't forget there are ALSO authors infos there
		assertThat(returned.size(), is(4));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.bigImage, CoreMatchers.endsWith("17973574.jpg"));
		assertThat(improved.getTitle(), Is.is("Le chasseur et son ombre"));
	}

	@Test public void canImproveTheLastHero() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782841722518");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(webClient, toImprove,
				service, configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		assertThat(returned.size(), Is.is(6));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.getTitle(), Is.is("Le Dernier héros (Les Annales du Disque-monde, #27)"));
		assertThat(improved.getSeries().size(), Is.is(2));
	}

	@Test public void canParserSerieDescriptionForBattleAngelAlita() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9781591162810");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(webClient, toImprove,
				service, configuration);
		tested.call();
		Collection<BookInfos> returned = CollectionUtils.asList(service.findAll());
		assertThat(returned.size(), Is.is(3));
		BookInfos book = returned.iterator().next();
		assertThat(book, IsInstanceOf.instanceOf(Book.class));
		Book improved = (Book) book;
		assertThat(improved.getTitle(), Is.is("Battle Angel Alita - Last Order : Angel of Protest, Vol. 04"));
		assertThat(improved.getSeries().size(), Is.is(1));
	}

	@Test public void canImproveoneOfVorkosiganSaga() throws Exception {
		Book toImprove = new Book();
		toImprove.setIsbn13("9782277239253");

		FinderCrudService<BookInfos, BookInfosInformer> service = goodreadsEnvironment.getServiceFor(BookInfos.class, BookInfosInformer.class);
		BookImprover tested = new BookImprover(webClient, toImprove,
				service, configuration);
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

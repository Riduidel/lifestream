package org.ndx.lifestream.goodreads.references;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ndx.lifestream.goodreads.Author;
import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;
import org.ndx.lifestream.goodreads.Goodreads;
import org.ndx.lifestream.goodreads.GoodreadsConfiguration;
import org.ndx.lifestream.rendering.output.VFSHelper;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;
import com.dooapp.gaedo.utils.CollectionUtils;

import static org.junit.Assert.assertThat;

@Ignore
public class GoodreadsReferencesTest {
	private GoodreadsConfiguration configuration;
	private Goodreads tested;
	private FinderCrudService<BookInfos, BookInfosInformer> books;

	@Before
	public void loadAndAssembleData() throws Exception {
		configuration = new GoodreadsConfiguration(VFSHelper.getRunningDir_for_tests_only());
		tested = new Goodreads();
		InputStream testCsvFile = getClass().getResourceAsStream("/goodreads_export_Riduidel_20310812.csv");
		String testedString = IOUtils.toString(testCsvFile);
		List<String[]> rows = tested.splitIntoRows(testedString);
		assertThat(rows.size(), IsNot.not(0));
		books = tested.buildBooksCollection(rows, configuration);
		Collection<BookInfos> all = CollectionUtils.asList(books.findAll());
	}

	/**
	 * That book has a review text containing one direct author reference and one direct book reference.
	 * Text contains <pre>
	 * J'ai relu, durant mes vacances, les trois tomes de ce bouquin totallement fascinant pour le geek science-fictif que je suis.
	 * Fascinant car l'un des personnages principaux est quasiment moi : Randy Waterhouse est un geek tout à fait typique : plus tout à
	 * fait en forme, assez intelligent pour avoir de bonnes idées, mais trop procrastinateur pour les mettre en oeuvre, et de surcroit
	 * plutôt à la traîne dans sa compréhension de l'univers.<br/><br/>A sa décharge, l'univers présenté, aussi bien dans la partie
	 * moderne du roman que dans la partie seconde guerre mondiale, est foisonnant, bourré de personnages hauts en couleurs et
	 * d'explications à la limite du compréhensible sur la cryptographie, la nécessité masturbatoiredans la rédaction de code, et
	 * autres sujets d'intérêt.<br/><br/>Bon, enfin, j'ai déja eu l'occasion d'en parler, mais je trouve qu'il s'agit d'une oeuvre
	 * séminale de [a:Neal Stephenson|545|Neal Stephenson|http://photo.goodreads.com/authors/1192826259p2/545.jpg]. on y retrouve
	 * en effet la richesse du contenu déja présente dans
	 * [b:Le samouraï virtuel|830|Snow Crash|Neal Stephenson|http://photo.goodreads.com/books/1157396730s/830.jpg|493634], alliée
	 * à la richesse de contenu technique qu'on est en droit d'attendre de l'auteur de <q>in the beginning was the command line</q>.
	 * <br/><br/>Bref, c'était une excellente lecture, et je ne regrette absolument pas de l'avoir pris dans mon sac de vacances.
	 * </pre>
	 *
	 * There are two references here : one to Neal Stephenson, and the other to Snow Crash. Do they resolve into anything ?
	 *
	 * THEY SHOULD !
	 */
	@Test
	public void testForDirectBookReference_In_EnigmaCode() {
		BookInfos infos = getBook("9782253072362");
		assertThat(infos, IsInstanceOf.instanceOf(Book.class));
		Book book = (Book) infos;
		assertThat(book.getReferences().size(), Is.is(2));
		Iterator<Reference> references = book.getReferences().iterator();
		Reference first = references.next();
		assertThat(first, Is.is((Class) DirectAuthorReference.class));
		DirectAuthorReference firstAuthor = (DirectAuthorReference) first;
		assertThat(firstAuthor.getDestination(), IsNull.notNullValue());
		assertThat(firstAuthor.getDestination().getName(), Is.is("Neal Stephenson"));
		Reference second = references.next();
		assertThat(second, Is.is((Class) DirectBookReference.class));
		DirectBookReference firstBook = (DirectBookReference) second;
		// Guess what, text case here si different from "official" Snow crash french title.
		// And this is a thing gaedo can't resolve until https://github.com/Riduidel/gaedo/issues/76 is solved. Sad, but true.
		assertThat(firstBook.getDestination(), IsNull.notNullValue());
		assertThat(firstBook.getDestination(), Is.is((Class)Book.class));
		assertThat(firstBook.getDestination().getTitle().toLowerCase(), Is.is("le samouraï virtuel"));
	}

	private BookInfos getBook(final String isbn13) {
		BookInfos infos = books.find().matching(new QueryBuilder<BookInfosInformer>() {

			@Override
			public QueryExpression createMatchingExpression(BookInfosInformer informer) {
				return informer.getId().equalsTo(isbn13);
			}
		}).getFirst();
		return infos;
	}

	/**
	 * Interesting part of text is
	 * <pre>
	 * (je pense par exemple [book:Ames perdues] de [author:Poppy Z Brite] qui se passe au même endroit, il me semble, et n'arrive qu'à sortir un truc misérable)
	 * </pre>
	 *
	 * As you may notice, references are expressed here in terms of [book: and [author: tags, which shows Goodreads was unable to resovle those references.
	 * It could be useful to remind user these references are fuzzy - any possible way (typically by marking the text on page).
	 * Anyway, we know those references (in this test, "Ames perdues" refers to "Âmes perdues", and "Poppy Z Brite" to "Poppy Z. Brite")
	 * and they should be resolved ... I guess levenstein distance could help, but it will be costful
	 */
	@Test
	public void testForFuzzyReferences_in_Riverdream() {
		BookInfos infos = getBook("9782290006733");
		assertThat(infos, IsInstanceOf.instanceOf(Book.class));
		Book book = (Book) infos;
		assertThat(book.getReferences().size(), Is.is(4));
		Iterator<Reference> references = book.getReferences().iterator();
		Reference first= references.next();
		assertThat(first, Is.is((Class) FuzzyAuthorReference.class));
		FuzzyAuthorReference firstAuthor = (FuzzyAuthorReference) first;
//		assertThat(firstBook.getDestination().getTitle(), Is.is("Poppy Z. Brite"));
		Reference second= references.next();
		assertThat(second, Is.is((Class) FuzzyAuthorReference.class));
		FuzzyAuthorReference secondAuthor = (FuzzyAuthorReference) second;
		Reference third = references.next();
		assertThat(third, Is.is((Class) FuzzyBookReference.class));
		FuzzyBookReference firstFuzzyBook = (FuzzyBookReference) third;
		assertThat(firstFuzzyBook.getDestination().getTitle().trim(), Is.is("Âmes perdues"));
		Reference fourth = references.next();
		assertThat(fourth, Is.is((Class) FuzzyAuthorReference.class));
		FuzzyAuthorReference firstBook = (FuzzyAuthorReference) fourth;
		assertThat(firstBook.getDestination().getTitle(), Is.is("Poppy Z. Brite"));
	}
}
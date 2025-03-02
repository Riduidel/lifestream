package org.ndx.lifestream.goodreads.references;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.isA;
import static org.hamcrest.core.IsNot.not;

import java.util.Collection;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

class ReferencesTest {

	@Test
	void can_find_direct_author_reference() {
		Collection<Reference> tested = new References().prepareReferences("a text and a fuzzy reference to "
				+ "[a:Eddings|8732|David Eddings|http://photo.goodreads.com/authors/1223870462p2/8732.jpg] "
				+ "(which is quite OK, but great)"
				+ "and look,here is a non reference with [a particularly bad test]");
		assertThat(tested.size(), not(0));
		DirectAuthorReference firstAuthor = (DirectAuthorReference) tested.iterator().next();
		assertThat(firstAuthor.getName(), Is.is("David Eddings"));
	}

	@Test
	void can_find_fuzzy_author_reference() {
		Collection<Reference> tested = new References().prepareReferences("a text and"
				+ " a fuzzy reference to [author:Cordwainer Smith] (which is soo great)"
				+ "and look,here is a non reference with [a particularly bad test]");
		assertThat(tested.size(), not(0));
		FuzzyAuthorReference firstAuthor = (FuzzyAuthorReference) tested.iterator().next();
		assertThat(firstAuthor.getSearchedText(), Is.is("Cordwainer Smith"));
	}

	@Test
	void can_find_direct_book_reference() {
		Collection<Reference> tested = new References().prepareReferences("a text and a fuzzy reference to "
				+ "[b:Le samouraï virtuel|830|Snow Crash|Neal Stephenson|http://photo.goodreads.com/books/1157396730s/830.jpg|493634] "
				+ "(which is quite OK, but great)"
				+ "and look,here is a non reference with [a particularly bad test]");
		assertThat(tested.size(), not(0));
		DirectBookReference firstBook = (DirectBookReference) tested.iterator().next();
		assertThat(firstBook.getTitle(), Is.is("Snow Crash"));
		assertThat(firstBook.getText(), Is.is("Le samouraï virtuel"));
	}

	@Test
	void can_find_fuzzy_book_reference() {
		Collection<Reference> tested = new References().prepareReferences("a text and"
				+ " a fuzzy reference to [book:Ames perdues] (which is not really great)"
				+ "and look,here is a non reference with [a particularly bad test]");
		assertThat(tested.size(), not(0));
		FuzzyBookReference firstBook = (FuzzyBookReference) tested.iterator().next();
		assertThat(firstBook.getSearchedText(), Is.is("Ames perdues"));
	}

}

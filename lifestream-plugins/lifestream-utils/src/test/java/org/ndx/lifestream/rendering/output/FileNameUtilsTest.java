package org.ndx.lifestream.rendering.output;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.ndx.lifestream.rendering.output.FileNameUtils.simplify;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class FileNameUtilsTest {

	@Test
	void lineReturnAreRemoved() {
		assertThat(simplify("a\nb"), is("ab"));
	}

	@Test
	void quotesAreRemoved() {
		assertThat(simplify("a'b"), is("a_b"));
	}

	@Test
	void doubleQuotesAreRemoved() {
		assertThat(simplify("a\"b"), is("a_b"));
	}

	@Test
	void startsAreRemoved() {
		assertThat(simplify("a*b"), is("a_b"));
	}

	@Test
	void circumflexAccentsAreRemoved() {
		assertThat(simplify("aêb"), is("aeb"));
	}

	@Test
	void blankCharactersAreRemoved() {
		assertThat(simplify("a b\t"), is("a_b_"));
	}
}

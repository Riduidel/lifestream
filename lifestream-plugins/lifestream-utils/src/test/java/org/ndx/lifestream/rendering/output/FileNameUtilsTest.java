package org.ndx.lifestream.rendering.output;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.ndx.lifestream.rendering.output.FileNameUtils.simplify;

import org.hamcrest.core.Is;
import org.junit.Test;

public class FileNameUtilsTest {

	@Test
	public void lineReturnAreRemoved() {
		assertThat(simplify("a\nb"), is("ab"));
	}

	@Test
	public void quotesAreRemoved() {
		assertThat(simplify("a'b"), is("a_b"));
	}

	@Test
	public void doubleQuotesAreRemoved() {
		assertThat(simplify("a\"b"), is("a_b"));
	}

	@Test
	public void startsAreRemoved() {
		assertThat(simplify("a*b"), is("a_b"));
	}

	@Test
	public void circumflexAccentsAreRemoved() {
		assertThat(simplify("aêb"), is("aeb"));
	}

	@Test
	public void blankCharactersAreRemoved() {
		assertThat(simplify("a b\t"), is("a_b_"));
	}
}

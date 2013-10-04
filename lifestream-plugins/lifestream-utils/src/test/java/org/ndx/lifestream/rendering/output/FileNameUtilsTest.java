package org.ndx.lifestream.rendering.output;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;

public class FileNameUtilsTest {

	@Test
	public void lineReturnAreRemoved() {
		assertThat(FileNameUtils.simplify("a\nb"), Is.is("ab"));
	}

	@Test
	public void quotesAreRemoved() {
		assertThat(FileNameUtils.simplify("a'b"), Is.is("a_b"));
	}

	@Test
	public void doubleQuotesAreRemoved() {
		assertThat(FileNameUtils.simplify("a\"b"), Is.is("a_b"));
	}

	@Test
	public void startsAreRemoved() {
		assertThat(FileNameUtils.simplify("a*b"), Is.is("a_b"));
	}

	@Test
	public void circumflexAccentsAreRemoved() {
		assertThat(FileNameUtils.simplify("aêb"), Is.is("aeb"));
	}
}

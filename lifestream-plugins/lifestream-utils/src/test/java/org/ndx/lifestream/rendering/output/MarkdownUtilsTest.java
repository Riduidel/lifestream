package org.ndx.lifestream.rendering.output;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class MarkdownUtilsTest {

	@Test
	void ensureLinkWorkWithSimpleNames() {
		assertThat(MarkdownUtils.link(Arrays.asList("a"), Arrays.asList("b"), "text", "html"), Is.is("[text](b)"));
	}

	/**
	 * When there are two level with different names, go up and down
	 */
	@Test
	void ensureLinkWorkWithOneDifferentLevelOnEachSideNames() {
		assertThat(MarkdownUtils.link(Arrays.asList("a", "a"), Arrays.asList("b", "b.c"), "text", "html"), Is.is("[text](../b/b.html)"));
	}

	/**
	 * When there are two level with same name, don'tgo up and down
	 */
	@Test
	void ensureLinkWorkWithOneSameLevelOnEachSideNames() {
		assertThat(MarkdownUtils.link(Arrays.asList("a", "a"), Arrays.asList("a", "b"), "text", "html"), Is.is("[text](b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	void ensureLinkWorkWithTwoLevelsOnLeftAndOneLevelOnRight() {
		assertThat(MarkdownUtils.link(Arrays.asList("a", "a", "a"), Arrays.asList("b", "b"), "text", "html"), Is.is("[text](../../b/b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	void ensureLinkWorkWithOneLevelOnLeftAndTwoLevelsOnRight() {
		assertThat(MarkdownUtils.link(Arrays.asList("a", "a"), Arrays.asList("b", "b", "b"), "text", "html"), Is.is("[text](../b/b/b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	void ensureLinkWorkWithIdenticalSubHierarchyButDifferentBase() {
		assertThat(MarkdownUtils.link(Arrays.asList("a", "b", "b"), Arrays.asList("b", "b", "b"), "text", "html"), Is.is("[text](../../b/b/b)"));
	}
}

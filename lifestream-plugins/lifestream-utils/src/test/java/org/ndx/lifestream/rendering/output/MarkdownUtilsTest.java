package org.ndx.lifestream.rendering.output;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.Lists;

import static org.junit.Assert.*;

public class MarkdownUtilsTest {

	@Test
	public void ensureLinkWorkWithSimpleNames() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a"), Lists.newArrayList("b"), "text"), Is.is("[text](b)"));
	}

	/**
	 * When there are two level with different names, go up and down
	 */
	@Test
	public void ensureLinkWorkWithOneDifferentLevelOnEachSideNames() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a", "a"), Lists.newArrayList("b", "b"), "text"), Is.is("[text](../b/b)"));
	}

	/**
	 * When there are two level with same name, don'tgo up and down
	 */
	@Test
	public void ensureLinkWorkWithOneSameLevelOnEachSideNames() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a", "a"), Lists.newArrayList("a", "b"), "text"), Is.is("[text](b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	public void ensureLinkWorkWithTwoLevelsOnLeftAndOneLevelOnRight() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a", "a", "a"), Lists.newArrayList("b", "b"), "text"), Is.is("[text](../../b/b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	public void ensureLinkWorkWithOneLevelOnLeftAndTwoLevelsOnRight() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a", "a"), Lists.newArrayList("b", "b", "b"), "text"), Is.is("[text](../b/b/b)"));
	}

	/**
	 * When there are different levels, do the right thing
	 */
	@Test
	public void ensureLinkWorkWithIdenticalSubHierarchyButDifferentBase() {
		assertThat(MarkdownUtils.link(Lists.newArrayList("a", "b", "b"), Lists.newArrayList("b", "b", "b"), "text"), Is.is("[text](../../b/b/b)"));
	}
}

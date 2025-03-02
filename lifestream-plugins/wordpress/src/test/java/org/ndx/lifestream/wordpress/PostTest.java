package org.ndx.lifestream.wordpress;

import static org.hamcrest.MatcherAssert.assertThat;


import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class PostTest {

	@Test
	void do_not_improve_modern_text_with_line_return() {
		assertThat(Post.improvePostText("a\n<br/>b"), Is.is("a\n<br/>b"));
	}

	@Test
	void do_not_improve_modern_text_with_paragraph() {
		assertThat(Post.improvePostText("a\n<br/>b"), Is.is("a\n<br/>b"));
	}

	@Test
	void can_improve_simple_text_with_line_return() {
		assertThat(Post.improvePostText("a\nb"), Is.is("<p>\na\n<br/>\nb\n</p>"));
	}

	@Test
	void can_improve_simple_text_with_paragraph() {
		assertThat(Post.improvePostText("a\n\nc"), Is.is("<p>\na\n</p>\n<p>\nc\n</p>"));
	}

	@Test
	void can_improve_simple_text_with_line_return_and_paragraph() {
		assertThat(Post.improvePostText("a\n\nc"), Is.is("<p>\na\n</p>\n<p>\nc\n</p>"));
	}

}

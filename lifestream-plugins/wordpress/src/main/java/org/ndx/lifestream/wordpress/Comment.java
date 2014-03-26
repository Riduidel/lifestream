package org.ndx.lifestream.wordpress;

import java.util.Date;

import org.jdom.Element;

public class Comment {
	private String author;
	private String date;
	private String text;
	public Comment(Element e) {
		author = e.getChildText("comment_author");
		date = e.getChildText("comment_date");
		text = e.getChildTextTrim("comment_content");
	}

}

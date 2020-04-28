package org.ndx.lifestream.utils;

import java.util.Date;

public class TagUtils {

	public static final ThreadSafeSimpleDateFormat MONTH_FORMATTER = new ThreadSafeSimpleDateFormat("MMM");

	public static final ThreadSafeSimpleDateFormat YEAR_FORMATTER = new ThreadSafeSimpleDateFormat("yyyy");

	public static String monthAsTag(Date d) {
		return "month_"+MONTH_FORMATTER.format(d);
	}

	public static String ratingAsTag(Number rating) {
		return "rated_"+rating;
	}
	public static String yearAsTag(Date d) {
		return "year_"+YEAR_FORMATTER.format(d);
	}
}

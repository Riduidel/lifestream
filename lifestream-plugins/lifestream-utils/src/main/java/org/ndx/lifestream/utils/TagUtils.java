package org.ndx.lifestream.utils;

import java.util.Date;

public class TagUtils {

	public static final String PREFIX_YEAR = "_année_";

	public static final String PREFIX_RATED = "_note_";

	public static final String PREFIX_MONTH = "_mois_";

	public static final ThreadSafeSimpleDateFormat FORMATTER_MONTH = new ThreadSafeSimpleDateFormat("MMM");

	public static final ThreadSafeSimpleDateFormat FORMATTER_YEAR = new ThreadSafeSimpleDateFormat("yyyy");

	public static String monthAsTag(Date d) {
		return PREFIX_MONTH+FORMATTER_MONTH.format(d);
	}

	public static String ratingAsTag(Number rating) {
		return PREFIX_RATED+rating;
	}
	public static String yearAsTag(Date d) {
		return PREFIX_YEAR+FORMATTER_YEAR.format(d);
	}
}

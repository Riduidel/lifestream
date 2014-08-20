package org.ndx.lifestream.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simpler way to use DateFormat without being bothered by their
 * non-thread-safety
 * 
 * @author ndx
 *
 */
public class ThreadSafeSimpleDateFormat {

	private String format;

	private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(format);
		}
	};

	public ThreadSafeSimpleDateFormat(String format) {
		this.format = format;
	}

	public Date parse(String text) throws ParseException {
		return df.get().parse(text);
	}

	public String format(Date d) {
		return df.get().format(d);
	}

}

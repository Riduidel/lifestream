package org.ndx.lifestream.utils;

import java.util.regex.Pattern;

public class ThreadLocalPattern extends ThreadLocal<Pattern> {
	private String patternText;

	public ThreadLocalPattern(String patternText) {
		this.patternText = patternText;
	}

	@Override
	protected Pattern initialValue() {
		return Pattern.compile(patternText);
	}
}
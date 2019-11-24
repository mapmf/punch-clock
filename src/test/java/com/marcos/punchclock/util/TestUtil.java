package com.marcos.punchclock.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtil {

	public static String randomString(int length) {

		boolean useLetters = true;
		boolean useNumbers = false;

		return RandomStringUtils.random(length, useLetters, useNumbers);
	}
	
	public static String randomOnlyNumberString(int length) {

		boolean useLetters = false;
		boolean useNumbers = true;

		return RandomStringUtils.random(length, useLetters, useNumbers);
	}
}

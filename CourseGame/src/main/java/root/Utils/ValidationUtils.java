package com.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
	public static boolean isEmpty(String param) {
		boolean isEmpty = false;
		if (param == "" || param.trim().length() <= 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isValidEmailAddress(String email) {
		Pattern pattern = Pattern.compile(
				"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() && (email.endsWith("@gmail.com")) || (email.endsWith("@yahoo.com")))
			return true;
		return false;
	}

	public static boolean isValidIpAddress(String email) {
		Pattern pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches())
			return true;
		return false;
	}
}

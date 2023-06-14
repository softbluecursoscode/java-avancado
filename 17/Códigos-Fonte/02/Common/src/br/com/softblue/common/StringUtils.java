package br.com.softblue.common;

public class StringUtils {

	public static boolean isEmpty(String s) {
		return s == null ? false : s.trim().length() == 0;
	}
}

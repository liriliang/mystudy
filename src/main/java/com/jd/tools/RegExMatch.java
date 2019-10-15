package com.jd.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExMatch {

	private RegExMatch() {
	}

	/**
	 * get能够匹配到的指定序号的指定组的内容
	 *
	 * @param str    要匹配的字符串
	 * @param regex  正则表达式
	 * @param number 指定的序号（从1开始）
	 * @param group  指定的捕获组
	 * @return 匹配到的字符串，匹配失败则返回null
	 */
	public static String get(String str, String regex, int number, int group) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		for (int i = 0; i < number - 1; i++) {
			m.find();
		}
		while (m.find()) {
			int start = m.start(group);
			int end = m.end(group);
			return str.substring(start, end);
		}
		return null;
	}

	public static boolean matches(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * get能够第一个匹配到的内容
	 *
	 * @param str   要匹配的字符串
	 * @param regex 正则表达式
	 * @return 匹配到的字符串，匹配失败则返回null
	 */
	public static String get(String str, String regex) {
		return get(str, regex, 1);
	}

	/**
	 * get能够匹配到的指定序号的内容
	 *
	 * @param str    要匹配的字符串
	 * @param regex  正则表达式
	 * @param number 指定的序号（从1开始）
	 * @return 匹配到的字符串，匹配失败则返回null
	 */
	public static String get(String str, String regex, int number) {
		return get(str, regex, number, 0);
	}

}

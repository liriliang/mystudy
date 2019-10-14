package com.jd.tools;

public class RandomString {
	/**
	 * 数字模式：获取的字符串只包含数字
	 */
	protected static int NUMBER = 0;

	/**
	 * 小写字母模式：获取的字符串只包含小写字母
	 */
	protected static int LOWERCASE = 1;

	/**
	 * 大写字母模式：获取的字符串只包含大写字母
	 */
	protected static int UPPERCASE = 2;

	/**
	 * 特殊字符模式：获取的字符串只包含特殊字符
	 */
	protected static int SPECIALCASE = 3;

	/**
	 * 数字-小写字母模式：获取的字符串包含数字及小写字母
	 */
	protected static int NUMBER_LOWERCASE = 4;

	/**
	 * 数字-大写字母模式：获取的字符串包含数字及大写字母
	 */
	protected static int NUMBER_UPPERCASE = 5;

	/**
	 * 数字-特殊字符模式：获取的字符串包含数字及特殊字符
	 */
	protected static int NUMBER_SPECIALCASE = 6;

	/**
	 * 字母模式：获取的字符串包含大小写字母
	 */
	protected static int LETTER = 7;

	/**
	 * 小写字母-特殊字符模式：获取的字符串包含小写字母及特殊字符
	 */
	protected static int LOWERCASE_SPECIALCASE = 8;

	/**
	 * 大写字母-特殊字符模式：获取的字符串包含大写字母及特殊字符
	 */
	protected static int UPPERCASE_SPECIALCASE = 9;

	/**
	 * 数字-字母模式：获取的字符串包含数字及大小写字母
	 */
	protected static int NUMBER_LETTER = 10;

	/**
	 * 数字-小写字母-特殊字母模式：获取的字符串包含数字、小写字母及特殊字符
	 */
	protected static int NUMBER_LOWERCASE_SPECIALCASE = 11;

	/**
	 * 数字-大写字母-特殊字母模式：获取的字符串包含数字、大写字母及特殊字符
	 */
	protected static int NUMBER_UPPERCASE_SPECIALCASE = 12;

	/**
	 * 字母-特殊字符模式：获取的字符串包含大小写字母及特殊字符
	 */
	protected static int LETTER_SPECIALCASE = 13;

	/**
	 * 任意模式：获取的字符串包含任意字符
	 */
	protected static int ALL_CHAR_CASE = 14;

	public String getString(int mode, int length) {
		if (length <= 0)
			return null;

		if (mode < 0 || mode > 14)
			mode = Math.abs(mode) % 15;

		return null;
	}

}

package com.jd.tools;

import java.io.File;
import java.util.Properties;

public class RandomCharacter extends RandomChar {

	// 数字类型的字符数组
	private char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private int num;

	// 小写字母类型的字符数组
	private char[] lowercase = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private int low;

	// 大写字母类型的字符数组
	private char[] uppercase = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private int upp;

	// 特殊字符类型的字符数组
	private char[] specialcase = { '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[',
			'{', ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?', ' ' };
	private int spe;

	/**
	 ** 构造函数，默认字符集
	 */
	public RandomCharacter() {
		this(false);
	}

	/**
	 ** 构造函数，设置是否从配置文件读取字符集
	 *
	 * @param fromFile 是否来自配置文件
	 */
	public RandomCharacter(boolean fromFile) {
		if (fromFile) {
			File file = new File("src/main/resources/chars.properties");
			Properties p = GetConf.get(file);
			number = p.getProperty("NUMBER").toCharArray();
			lowercase = p.getProperty("LOWERCASE").toCharArray();
			uppercase = p.getProperty("UPPERCASE").toCharArray();
			specialcase = p.getProperty("SPECIALCASE").toCharArray();
		}
		num = number.length;
		low = lowercase.length;
		upp = uppercase.length;
		spe = specialcase.length;
	}

	@Override
	public char getChar(int type) {
		if (type < 0 || type > 4)
			type = Math.abs(type) % 4;

		switch (type) {
		case 0:
			return number[(int) (Math.random() * num)];
		case 1:
			return lowercase[(int) (Math.random() * low)];
		case 2:
			return uppercase[(int) (Math.random() * upp)];
		case 3:
			return specialcase[(int) (Math.random() * spe)];
		default:
			return '\0';
		}
	}

}

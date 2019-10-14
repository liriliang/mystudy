package com.jd.test.tools;

/**
 ** 用于获取一个随机字符串
 * @author Administrator
 *
 */
public abstract class RandomChar {

	/**
	 ** 数字类型
	 */
	protected static int NUMBER = 0;

	/**
	 ** 小写字母类型
	 */
	protected static int LOWERCASE = 1;

	/**
	 ** 大写字母类型
	 */
	protected static int UPPERCASE = 2;

	/**
	 ** 特殊字符类型
	 */
	protected static int SPECIALCASE = 3;

	/**
	 ** 获取一个随机字符
	 * @param type 字符类型<br>
	 * 0、数字字符<br>
	 * 1、小写字母<br>
	 * 2、大写字母<br>
	 * 3、特殊字符
	 * @return 一个指定类型的随机字符
	 */
	abstract char getChar(int type);

}

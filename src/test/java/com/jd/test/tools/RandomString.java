package com.jd.test.tools;

/**
 ** 获取一个随机字符串
 *
 * @author Administrator
 *
 */
public class RandomString {

	/**
	 ** 数字模式: 获取的字符串只包含数字
	 */
	protected static int NUMBER = 0;

	/**
	 ** 小写字母模式: 获取的字符串只包含小写字母
	 */
	protected static int LOWERCASE = 1;

	/**
	 ** 大写字母模式: 获取的字符串只包含大写字母
	 */
	protected static int UPPERCASE = 2;

	/**
	 ** 特殊字符模式: 获取的字符串只包含特殊字符
	 */
	protected static int SPECIALCASE = 3;

	/**
	 ** 数字小写字母模式: 获取的字符串包含数字及小写字母
	 */
	protected static int NUMBER_LOWERCASE = 4;

	/**
	 ** 数字大写字母模式: 获取的字符串包含数字及大写字母
	 */
	protected static int NUMBER_UPPERCASE = 5;

	/**
	 ** 数字特殊字符模式: 获取的字符串包含数字及特殊字符
	 */
	protected static int NUMBER_SPECIALCASE = 6;

	/**
	 ** 字母模式: 获取的字符串包含大小写字母
	 */
	protected static int LETTER = 7;

	/**
	 ** 小写字母特殊字符模式: 获取的字符串包含小写字母及特殊字符
	 */
	protected static int LOWERCASE_SPECIALCASE = 8;

	/**
	 ** 大写字母特殊字符模式: 获取的字符串包含大写字母及特殊字符
	 */
	protected static int UPPERCASE_SPECIALCASE = 9;

	/**
	 ** 数字字母模式: 获取的字符串包含数字及大小写字母
	 */
	protected static int NUMBER_LETTER = 10;

	/**
	 ** 数字小写字母特殊字母模式: 获取的字符串包含数字小写字母及特殊字符
	 */
	protected static int NUMBER_LOWERCASE_SPECIALCASE = 11;

	/**
	 ** 数字大写字母特殊字母模式: 获取的字符串包含数字大写字母及特殊字符
	 */
	protected static int NUMBER_UPPERCASE_SPECIALCASE = 12;

	/**
	 ** 字母特殊字符模式: 获取的字符串包含大小写字母及特殊字符
	 */
	protected static int LETTER_SPECIALCASE = 13;

	/**
	 ** 任意模式: 获取的字符串包含任意字符
	 */
	protected static int ALL_CHAR_CASE = 14;

	/**
	 ** 获取指定模式 指定长度的字符串(默认指定的字符类型可以不必全部包含)
	 *
	 * @param mode   指定的模式
	 * @param length 指定的长度
	 * @return 随机字符串(指定的长度小于0是，返回内容为null)
	 */
	public String getString(int mode, int length) {
		return getString(mode, length, false, false);
	}

	/**
	 ** 获取指定模式 指定长度的字符串
	 *
	 * @param mode       指定的模式
	 * @param length     指定的长度
	 * @param includeAll 指定的字符类型是否全部包含
	 * @return 随机字符串(指定的长度小于0是，返回内容为null)
	 */
	public String getString(int mode, int length, boolean includeAll) {
		return getString(mode, length, false, includeAll);
	}

	/**
	 ** 获取指定模式 指定长度的字符串
	 *
	 * @param mode         指定的模式
	 * @param length       指定的长度
	 * @param includeAll   指定的字符类型是否全部包含
	 * @param charFromFile 字符是否来源与配置文件
	 * @return 随机字符串(指定的长度小于0时，返回内容为"")
	 */
	public String getString(int mode, int length, boolean includeAll, boolean charFromFile) {
		// 如果长度小于0，则返回null
		if (length <= 0)
			return "";

		// 修正mode值
		if (mode < 0 || mode > 14)
			mode = Math.abs(mode) % 15;

		// 定义字符容器
		CharContainer ac = new CharContainer();
		ac.setLength(length);
		if (includeAll) {
			int strlong = ac.getModeIncludeType(mode).length;
			if (length < strlong)
				throw new RuntimeException("设置的长度无法包含全部类型的字符: 需要长度为：" + strlong + ", 而设置的长度为：" + length);
			ac.setInclude(true);
			ac.setMode(mode);
		}

		// 定义向容中添加各类字符的线程
		Thread th1 = new Thread(new AddChars(ac, RandomChar.NUMBER), "number");
		Thread th2 = new Thread(new AddChars(ac, RandomChar.LOWERCASE), "lowercase");
		Thread th3 = new Thread(new AddChars(ac, RandomChar.UPPERCASE), "uppercase");
		Thread th4 = new Thread(new AddChars(ac, RandomChar.SPECIALCASE), "specialcase");

		if (charFromFile) {
			th1 = new Thread(new AddChars(ac, RandomChar.NUMBER, true), "number");
			th2 = new Thread(new AddChars(ac, RandomChar.LOWERCASE, true), "lowercase");
			th3 = new Thread(new AddChars(ac, RandomChar.UPPERCASE, true), "uppercase");
			th4 = new Thread(new AddChars(ac, RandomChar.SPECIALCASE, true), "specialcase");
		}

		// 根据需要启动相应的线程
		switch (mode) {
		case 0:
			th1.start();
			break;
		case 1:
			th2.start();
			break;
		case 2:
			th3.start();
			break;
		case 3:
			th4.start();
			break;
		case 4:
			th1.start();
			th2.start();
			break;
		case 5:
			th1.start();
			th3.start();
			break;
		case 6:
			th1.start();
			th4.start();
			break;
		case 7:
			th3.start();
			th2.start();
			break;
		case 8:
			th4.start();
			th2.start();
			break;
		case 9:
			th3.start();
			th4.start();
			break;
		case 10:
			th1.start();
			th2.start();
			th3.start();
			break;
		case 11:
			th1.start();
			th2.start();
			th4.start();
			break;
		case 12:
			th1.start();
			th4.start();
			th3.start();
			break;
		case 13:
			th4.start();
			th2.start();
			th3.start();
			break;
		case 14:
			th1.start();
			th2.start();
			th3.start();
			th4.start();
			break;
		}

		// 等待容器添加完成后被唤醒
		synchronized (CharContainer.class) {
			try {
				CharContainer.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 获取容器中的所有字符
		return new String(ac.getStr());
	}

	protected static int FIRSTINDEX = 0;

	protected static int LASTINDEX = Integer.MAX_VALUE;

	protected static int RANDOMINDEX = Integer.MAX_VALUE - 1;

	/**
	 ** 获取随机字符串，并在标识位包含指定的字符串
	 *
	 * @param mode         字符串模式
	 * @param lenght       指定字符串的长度
	 * @param includeAll   是否包含全部指定的字符
	 * @param charFromFile 字符是否来源与配置文件
	 * @param note         要添加字符串的标识位
	 * @param contains     要添加的字符串(字符串的长度必须不大于length - new
	 *                     CharContainer().getModeIncludeType().length)
	 * @return 包含指定字符串的随机字符串
	 */
	public String getString(int mode, int lenght, boolean includeAll, boolean charFromFile, int note, String contains) {
		String str = getString(mode, lenght - contains.length(), includeAll, charFromFile);

		note = Math.abs(note);

		if (note == 0)
			return contains + str;

		if (note == Integer.MAX_VALUE)
			return str + contains;

		if (note == Integer.MAX_VALUE - 1)
			note = (int) (Math.random() * (lenght - contains.length()));

		String str1 = str.substring(0, note);
		String str2 = str.substring(note);

		return str1 + contains + str2;
	}

}

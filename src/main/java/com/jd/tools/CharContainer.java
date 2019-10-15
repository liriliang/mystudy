package com.jd.tools;

import org.apache.log4j.Logger;

/**
 ** 字符串数组的公共资源池，
 *
 * @author Administrator
 *
 */
public class CharContainer {
	private Logger log = Logger.getLogger(this.getClass());

	// 数组的长度
	private int length = 10;

	// 数组当前赋值的角标
	private int index = 0;

	// 数组资源
	private char str[];

	// 是否包含指定类型的全部字符
	private boolean includeAll = false;

	//
	private int mode;

	/**
	 ** 构造函数，并初始化资源池
	 */
	public CharContainer() {
		str = new char[length];
	};

	/**
	 ** 重置资源池
	 *
	 * @param length 新资源池的长度
	 */
	public void setLength(int length) {
		this.length = length;
		str = new char[length];
		con = new boolean[4];
		index = 0;
	}

	/**
	 ** 设置是否全部包含指定类型的字符
	 *
	 * @param includeAll
	 */
	public void setInclude(boolean includeAll) {
		this.includeAll = includeAll;
	}

	/**
	 ** 设置字符串模式
	 *
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode % 15;
	}

	/**
	 ** 获取资源容器的长度
	 *
	 * @return 容器的长度
	 */
	public int getLength() {
		return length;
	}

	/**
	 ** 获取容器中当前未赋值的角标
	 *
	 * @return 容器当前角标
	 */
	public int getIndex() {
		return index;
	}

	/**
	 ** 按顺序向容器中添加字符
	 *
	 * @param c 要添加的字符
	 */
	public void getChar(char c) {
		getChar(c, includeAll);
	}

	// 各个类型的字符是否包含的状态 分别对应为数字 小写字母 大写字母 特殊字符
	private boolean con[] = { false, false, false, false };

	/**
	 ** 按顺序向容器中添加字符
	 *
	 * @param c          要添加的字符
	 * @param includeAll 是否指定字符类型全部包括
	 */
	private void getChar(char c, boolean includeAll) {
		// 不要求全部包含时
		if (!includeAll) {
			synchronized (this) {
				// 资源池满时不在添加
				if (index >= length)
					return;
				str[index++] = c;
				log.debug("向数组中添加第" + index + "个元素：" + c);
			}
		} else { // 要求全包含时
			switch (mode) {
			case 0:
			case 1:
			case 2:
			case 3:
				// 单一类型字符，只简单做资源池满判断，直接添加
				synchronized (this) {
					if (index >= length)
						return;
					log.debug("向数组中添加第" + index + "个元素：" + c);
					str[index++] = c;
				}
				return;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				// 多字符类型要求时
				synchronized (this) {
					if (index >= length)
						return;
					// 当资源还能够放置所有指定类型字符时，直接添加
					if (length - index >= getModeIncludeType(mode).length) {
						log.debug("向数组中添加第" + index + "个元素：" + c);
						str[index++] = c;
						con[charType(c)] = true;
					} else {
						// 判断是否还能向资源池中添加该类型的字符
						if (canAdd(c)) {
							log.debug("向数组中添加第" + index + "个元素：" + c);
							str[index++] = c;
							con[charType(c)] = true;
						} else {
							return;
						}
					}
				}
				return;
			default:
				return;
			}
		}
	}

	/**
	 ** 当要求资源池中必须包含全部指定类型的字符时，判断能否添加该类型的字符
	 *
	 * @param c 要添加的字符
	 * @return 能否添加的Boolean值
	 */
	private boolean canAdd(char c) {
		// 当该字符类型的标识状态为false时，可以添加
		if (!con[charType(c)])
			return true;
		//
		for (int i : getModeIncludeType(mode)) {
			if (!con[i])
				return false;
		}
		return true;
	}

	/**
	 ** 获取字符类型
	 *
	 * @param c 指定字符
	 * @return 字符的类型
	 */
	private int charType(char c) {
		String str = "" + c;
		if (str.matches("\\d")) // 数字类型
			return 0;
		if (str.matches("[a-z]")) // 小写字母
			return 1;
		if (str.matches("[A-Z]")) // 大写字母
			return 2;
		return 3; // 默认为特殊字符
	}

	/**
	 ** 获取指定mode下对应的字符类型
	 *
	 * @param mode 字符串类型
	 * @return 相应的数组
	 */
	public int[] getModeIncludeType(int mode) {
		int types[] = {};
		switch (mode) {
		case 0:
			types = new int[] { 0 };
			break;
		case 1:
			types = new int[] { 1 };
			break;
		case 2:
			types = new int[] { 2 };
			break;
		case 3:
			types = new int[] { 3 };
			break;
		case 4:
			types = new int[] { 0, 1 };
			break;
		case 5:
			types = new int[] { 0, 2 };
			break;
		case 6:
			types = new int[] { 0, 3 };
			break;
		case 7:
			types = new int[] { 1, 2 };
			break;
		case 8:
			types = new int[] { 1, 3 };
			break;
		case 9:
			types = new int[] { 2, 3 };
			break;
		case 10:
			types = new int[] { 0, 1, 2 };
			break;
		case 11:
			types = new int[] { 0, 1, 3 };
			break;
		case 12:
			types = new int[] { 0, 2, 3 };
			break;
		case 13:
			types = new int[] { 1, 2, 3 };
			break;
		case 14:
			types = new int[] { 0, 1, 2, 3 };
			break;
		default:
			break;
		}
		return types;
	}

	/**
	 ** 获取容器的全部资源
	 *
	 * @return 容器数组
	 */
	public char[] getStr() {
		return str;
	}

}

package com.jd.tools;

public class AddChars implements Runnable {

	// 定义字符容器
	private CharContainer ac;
	// 定义字符类型
	private int type;
	// 随机字符类对象
	private RandomCharacter rc;

	/**
	 ** 构造函数,指定了容器及向容器添加字符的类型
	 *
	 * @param ac   容器
	 * @param type 添加字符的类型
	 */
	public AddChars(CharContainer ac, int type) {
		this(ac, type, false);
	}

	/**
	 ** 构造函数,指定了容器、向容器添加字符的类型及字符来源
	 *
	 * @param ac           容器
	 * @param type         字符类型
	 * @param charFromFile 字符是否来源与配置文件
	 */
	public AddChars(CharContainer ac, int type, boolean charFromFile) {
		this.ac = ac;
		this.type = type;
		this.rc = new RandomCharacter(charFromFile);
	}

	/**
	 ** 循环向容器内添加字符
	 */
	@Override
	public void run() {
		int length = ac.getLength();
		// 容器未满时,向容器内添加字符
		while (ac.getIndex() < length) {
			ac.getChar(rc.getChar(type));
		}
		// 容器满后,唤醒等待取资源的线程
		synchronized (CharContainer.class) {
			CharContainer.class.notifyAll();
		}
	}

}

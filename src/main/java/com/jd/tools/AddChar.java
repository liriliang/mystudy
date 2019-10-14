package com.jd.tools;

public class AddChar implements Runnable {

	private int index = 0;
	private char c;
	private char str[];

	public AddChar(int length) {
		str = new char[length];
	}

	@Override
	public void run() {
		c = new RandomCharacter().getChar((int)(Math.random()*4));
		getChars();
	}

	public char[] getChars() {
		str[index] = c;
		return str;
	}

}

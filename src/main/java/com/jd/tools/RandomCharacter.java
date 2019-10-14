package com.jd.tools;

/**
 * :获取随机字符
 *
 * @author Administrator
 *
 */
public class RandomCharacter extends RandomChar {

	private char[] number = {'0','1','2','3','4','5','6','7','8','9'};

	private char[] lowercase = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

	private char[] uppercase = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	private char[] specialcase = {' ','`','!','"','#','$','%','&','\'','(',')','*','+',',','-','.','/',':',';','<','=','<','?','[','{','\\','|',']','}','^','~','_'};

	@Override
	public char getChar(int type) {
		if(type < 0 || type > 4)
			type = Math.abs(type) % 4;

		switch (type) {
		case 0:
			return number[(int) (Math.random()*10)];
		case 1:
			return lowercase[(int) (Math.random()*26)];
		case 2:
			return uppercase[(int) (Math.random()*26)];
		case 3:
			return specialcase[(int) (Math.random()*32)];
		default:
			return '\0';
		}
	}

}

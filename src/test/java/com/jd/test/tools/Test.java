package com.jd.test.tools;

public class Test {

	public static void main(String[] args) {
		RandomString rs = new RandomString();
		String str = rs.getString(RandomString.ALL_CHAR_CASE, 15, true, true, 0, "AA");
		System.out.println(str);
	}

}

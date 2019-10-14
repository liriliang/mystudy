package com.jd.test.tools;

import org.apache.log4j.Logger;

public class Parameter {

	private Parameter() {
	}

	private int STRING = 0;

	private static Parameter p = new Parameter();

	private Logger log = Logger.getLogger(Parameter.class);

	public static Parameter getParameter() {
		return p;
	}

	public String stringNext(String mode, int shortest, int longest) {
		if (STRING == 0) {
			STRING++;
			log.info("get the String 'NULL'!");
			return null;
		}
		if (STRING == 1) {
			STRING++;
			log.info("get the String \"\"!");
			return "";
		}
		return null;
	}

}

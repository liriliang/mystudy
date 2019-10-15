package com.jd.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import net.minidev.json.JSONObject;

public class GetConf {

	private static Logger log = Logger.getLogger(GetConf.class);

	private GetConf(File file) {
	}

	/**
	 * :读取配置文件，获取信息集合
	 *
	 * @param file 配置文件
	 * @return 信息集合
	 */
	public static Map<String, String> getHead(File file) {
		Map<String, String> map = new HashMap<String, String>();
		Properties p = get(file);
		String str = "";
		for (Entry<Object, Object> m : p.entrySet()) {
			map.put(m.getKey().toString(), m.getValue().toString());
			str += m.getKey().toString() + ":" + m.getValue().toString() + ";";
		}
		log.info("get map:" + str.substring(0, str.length() - 1));
		return map;
	}

	/**
	 * :通过配置文件获取参数信息
	 *
	 * @param file 配置文件
	 * @param mode 参数的模式
	 * @return 参数字符串
	 */
	public static String getParam(File file, String mode, String encode) {
		if (!mode.equalsIgnoreCase("form") && !mode.equalsIgnoreCase("json"))
			throw new RuntimeException("请求参数格式错误：只有JSON和FORM两种格式");
		Map<String, String> map = new HashMap<String, String>();
		Properties p = get(file);
		if (mode.equalsIgnoreCase("JSON")) {
			for (Entry<Object, Object> m : p.entrySet()) {
				map.put(encode(m.getKey(), encode), encode(m.getValue(), encode));
			}
			return new JSONObject(map).toString();
		} else {
			String str = "";
			for (Entry<Object, Object> m : p.entrySet()) {
				str += encode(m.getKey(), encode) + "=" + encode(m.getValue(), encode) + "&";
			}
			return str.substring(0, str.length() - 1);
		}
	}

	/**
	 * :对参数进行编码
	 *
	 * @param obj    参数
	 * @param encode 编码方式
	 * @return 编码后的字符串
	 */
	private static String encode(Object obj, String encode) {
		String str = obj.toString();
		String string = null;
		try {
			string = URLEncoder.encode(str, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * :将配置文件信息读入到Properties类中
	 *
	 * @param file 配置文件
	 * @return 配置文件信息
	 */
	public static Properties get(File file) {
		InputStream in = null;
		Properties p = new Properties();
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

}

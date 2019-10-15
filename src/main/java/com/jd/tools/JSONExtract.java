package com.jd.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.Predicate;
import com.mysql.cj.xdevapi.JsonArray;

/**
 * Json提取
 *
 * @author Administrator
 *
 */
public class JSONExtract {

	private JSONExtract() {
	}

	/**
	 ** 根据json路径，提取相应的参数值
	 *
	 * @param json     json格式字符串
	 * @param jsonPath json路径标识
	 * @param num      匹配到项的角标值
	 * @return 提取到的值
	 */
	public static String get(String json, String jsonPath, int num) {
		Object obj = JsonPath.read(json, jsonPath, new Predicate[0]);
		return getStep(obj, jsonPath, num);
	}

	/**
	 ** 根据json路径，提取相应的参数值
	 *
	 * @param file     文件名
	 * @param jsonPath json路径标识
	 * @param num      匹配到项的角标值
	 * @return 提取到的值
	 */
	public static String get(File file, String jsonPath, int num) {
		Object obj = null;
		try {
			obj = JsonPath.read(file, jsonPath, new Predicate[0]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return getStep(obj, jsonPath, num);
	}

	/**
	 ** 根据json路径，提取相应的参数值
	 *
	 * @param object   能够获取json字符串的对象
	 * @param jsonPath json路径标识
	 * @param num      匹配到项的角标值
	 * @return 提取到的值
	 */
	public static String get(Object object, String jsonPath, int num) {
		Object obj = JsonPath.read(object, jsonPath, new Predicate[0]);
		return getStep(obj, jsonPath, num);
	}

	/**
	 ** 根据json路径，提取相应的参数值
	 *
	 * @param in       输入流
	 * @param jsonPath json路径标识
	 * @param num      匹配到项的角标值
	 * @return 提取到的值
	 */
	public static String get(InputStream in, String jsonPath, int num) {
		Object obj = null;
		try {
			obj = JsonPath.read(in, jsonPath, new Predicate[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getStep(obj, jsonPath, num);
	}

	/**
	 ** 根据json路径，提取相应的参数值
	 *
	 * @param obj      json对象
	 * @param jsonPath json路径标识
	 * @param num      匹配到项的角标值
	 * @return 提取到的值
	 */
	private static String getStep(Object obj, String jsonPath, int num) {
		if (obj == null)
			throw new PathNotFoundException("The jsonPath is error: " + jsonPath);
		if (obj instanceof JsonArray) {
			JsonArray jar = (JsonArray) obj;
			if (jar.isEmpty())
				return "";
			else
				return jar.get(num).toFormattedString();
		} else {
			return obj.toString();
		}
	}
}

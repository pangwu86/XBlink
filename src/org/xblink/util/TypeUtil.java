package org.xblink.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供方法来判断对象或类的类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class TypeUtil {

	private static Map<Class<?>, Integer> basicTypeMap = new HashMap<Class<?>, Integer>();

	static {
		Integer n = new Integer(1);
		// 八种基本类型
		basicTypeMap.put(int.class, n);
		basicTypeMap.put(byte.class, n);
		basicTypeMap.put(short.class, n);
		basicTypeMap.put(float.class, n);
		basicTypeMap.put(double.class, n);
		basicTypeMap.put(long.class, n);
		basicTypeMap.put(boolean.class, n);
		basicTypeMap.put(char.class, n);
		// TODO 八种基本类型的包装类

		// JAVA中常见类型
		basicTypeMap.put(String.class, n);
		basicTypeMap.put(StringBuffer.class, n);
		basicTypeMap.put(StringBuilder.class, n);
		basicTypeMap.put(Date.class, n);
	}

	private TypeUtil() {
	}

	public static boolean isBasicType(Class<?> clz) {
		return null != basicTypeMap.get(clz);
	}

	public static boolean isCollectionType(Class<?> clz) {
		// TODO
		return false;
	}

	public static boolean isMapType(Class<?> clz) {
		// TODO
		return false;
	}
}

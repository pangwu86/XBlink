package org.xblink.util;

import java.util.Collection;
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

	private static Map<Class<?>, Integer> singleValueTypeMap = new HashMap<Class<?>, Integer>();

	static {
		Integer n = new Integer(1);
		// 八种基本类型
		singleValueTypeMap.put(int.class, n);
		singleValueTypeMap.put(byte.class, n);
		singleValueTypeMap.put(short.class, n);
		singleValueTypeMap.put(float.class, n);
		singleValueTypeMap.put(double.class, n);
		singleValueTypeMap.put(long.class, n);
		singleValueTypeMap.put(boolean.class, n);
		singleValueTypeMap.put(char.class, n);
		// TODO 八种基本类型的包装类

		// JAVA中常见类型
		singleValueTypeMap.put(String.class, n);
		singleValueTypeMap.put(StringBuffer.class, n);
		singleValueTypeMap.put(StringBuilder.class, n);
		singleValueTypeMap.put(Date.class, n);
	}

	private TypeUtil() {
	}

	/**
	 * 是否是单值的基本类型。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isSingleValueType(Class<?> clz) {
		return null != singleValueTypeMap.get(clz);
	}

	/**
	 * 是否是数组，List，Set类型之一。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isCollectionType(Class<?> clz) {
		if (clz.isArray() || Collection.class.isAssignableFrom(clz)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是Map类型。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isMapType(Class<?> clz) {
		if (Map.class.isAssignableFrom(clz)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是八种基本类型之一。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isPrimitiveType(Class<?> clz) {
		if (byte.class == clz || short.class == clz || int.class == clz || float.class == clz || double.class == clz
				|| long.class == clz || boolean.class == clz || char.class == clz) {
			return true;
		}
		return false;
	}
}

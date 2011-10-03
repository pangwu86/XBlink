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

	private TypeUtil() {
	}

	private static Map<Class<?>, Integer> singleValueTypeMap = new HashMap<Class<?>, Integer>();
	private static Integer n = new Integer(1);

	static {

		// 八种基本类型
		singleValueTypeMap.put(int.class, n);
		singleValueTypeMap.put(byte.class, n);
		singleValueTypeMap.put(short.class, n);
		singleValueTypeMap.put(float.class, n);
		singleValueTypeMap.put(double.class, n);
		singleValueTypeMap.put(long.class, n);
		singleValueTypeMap.put(boolean.class, n);
		singleValueTypeMap.put(char.class, n);
		// 八种基本类型的包装类
		singleValueTypeMap.put(Integer.class, n);
		singleValueTypeMap.put(Byte.class, n);
		singleValueTypeMap.put(Short.class, n);
		singleValueTypeMap.put(Float.class, n);
		singleValueTypeMap.put(Double.class, n);
		singleValueTypeMap.put(Long.class, n);
		singleValueTypeMap.put(Boolean.class, n);
		singleValueTypeMap.put(Character.class, n);
		// JAVA中常见类型
		singleValueTypeMap.put(String.class, n);
		singleValueTypeMap.put(StringBuffer.class, n);
		singleValueTypeMap.put(StringBuilder.class, n);
		singleValueTypeMap.put(Date.class, n);
	}

	/**
	 * 添加一个类型到单值集合中。
	 * 
	 * @param clz
	 */
	public static void add2SingaleValueMap(Class<?> clz) {
		singleValueTypeMap.put(clz, n);
	}

	/**
	 * 是否是枚举类型。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isEnum(Class<?> clz) {
		return clz.isEnum() || (clz.getSuperclass() != null && clz.getSuperclass().isEnum());
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
		return clz.isArray() || Collection.class.isAssignableFrom(clz);
	}

	/**
	 * 是否是Map类型。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isMapType(Class<?> clz) {
		return Map.class.isAssignableFrom(clz);
	}

	/**
	 * 是否是八种基本类型之一。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isPrimitiveType(Class<?> clz) {
		return byte.class == clz || short.class == clz || int.class == clz || float.class == clz || double.class == clz
				|| long.class == clz || boolean.class == clz || char.class == clz;
	}
}

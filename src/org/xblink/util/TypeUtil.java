package org.xblink.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xblink.annotation.XBlinkAsAttribute;
import org.xblink.annotation.XBlinkConverter;

/**
 * 提供方法来判断对象或类的类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class TypeUtil {

	private TypeUtil() {
	}

	private static Set<Class<?>> singleValueTypeSet = new HashSet<Class<?>>();

	/**
	 * 添加一个类型到单值集合中。
	 * 
	 * @param clz
	 */
	public static void add2SingaleValueMap(Class<?> clz) {
		singleValueTypeSet.add(clz);
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
		return singleValueTypeSet.contains(clz);
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
	 * 是否是Map.Entry类型。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isEntryType(Class<?> clz) {
		return Map.Entry.class.isAssignableFrom(clz);
	}

	/**
	 * 是否是八种基本类型之一。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isPrimitiveType(Class<?> clz) {
		return clz.isPrimitive();
	}

	/**
	 * 判断是否是使用了XBlinkAsAttribute注解的字段。
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isAttributeField(Field field) {
		return null != field.getAnnotation(XBlinkAsAttribute.class);
	}

	/**
	 * 判断是否是使用了XBlinkConverter注解的字段。
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isCustomizedField(Field field) {
		return null != field.getAnnotation(XBlinkConverter.class);
	}

	/**
	 * 尝试寻找并加载这个类。
	 * 
	 * @param clzName
	 * @return
	 */
	public static Class<?> tryFindThisClass(String clzName) {
		Class<?> clz = null;
		try {
			clz = Class.forName(clzName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("无法加载[%s]这个类。", clzName), e);
		}
		return clz;
	}
}

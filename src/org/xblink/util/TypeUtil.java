package org.xblink.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	private static Map<Class<?>, Integer> singleValueTypeMap = new HashMap<Class<?>, Integer>();
	private static Integer n = new Integer(1);

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
}

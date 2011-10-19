package org.xblink.core.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.xblink.annotation.XBlinkAlias;
import org.xblink.core.Constant;
import org.xblink.util.ArrayUtil;
import org.xblink.util.StringUtil;

/**
 * 别名的缓存。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AliasCache {

	private AliasCache() {
	}

	private static Map<Class<?>, String> classAliasMap = new ConcurrentHashMap<Class<?>, String>();

	private static Map<String, Class<?>> aliasClassMap = new ConcurrentHashMap<String, Class<?>>();

	private static Map<Class<?>, Map<Field, String>> fieldAliasMap = new ConcurrentHashMap<Class<?>, Map<Field, String>>();

	/**
	 * 获得成员名称。
	 * 
	 * @param clz
	 * @param field
	 * @return 成员名称
	 */
	public static String getFieldName(Class<?> clz, Field field) {
		String fieldName = null;
		Map<Field, String> fiedlNameMap = fieldAliasMap.get(clz);
		if (null == fiedlNameMap) {
			fiedlNameMap = new HashMap<Field, String>();
			fieldAliasMap.put(clz, fiedlNameMap);
		}
		fieldName = fiedlNameMap.get(field);
		if (null == fieldName) {
			// 查看是否有别名，没有就采用class.getName()
			fieldName = getFieldNameByAlias(field);
			fiedlNameMap.put(field, fieldName);
		}
		return fieldName;
	}

	private static String getFieldNameByAlias(Field field) {
		XBlinkAlias fieldNameAlias = (XBlinkAlias) field.getAnnotation(XBlinkAlias.class);
		String name = null;
		if (null != fieldNameAlias) {
			name = fieldNameAlias.value();
		} else {
			name = field.getName();
		}
		return name;
	}

	/**
	 * 获得类名称。
	 * 
	 * @param clz
	 * @return 名称
	 */
	public static String getClassName(Class<?> clz) {
		String className = null;
		className = classAliasMap.get(clz);
		if (null == className) {
			// 查看是否有别名
			className = getClassNameByAlias(clz);
			classAliasMap.put(clz, className);
			aliasClassMap.put(className, clz);
		}
		return className;
	}

	private static String getClassNameByAlias(Class<?> clz) {
		XBlinkAlias classNameAlias = (XBlinkAlias) clz.getAnnotation(XBlinkAlias.class);
		String name = null;
		if (null != classNameAlias) {
			name = classNameAlias.value();
		} else {
			// 如果没有被缓存过，则使用类的全名
			if (UsedClassCache.hasThisClass(clz)) {
				String simpleName = clz.getSimpleName();
				if (StringUtil.isAllUpperCase(simpleName)) {
					name = simpleName;
				} else {
					name = StringUtil.lowerFirst(simpleName);
				}
			} else {
				name = clz.getName();
			}
		}
		return name;
	}

	/**
	 * 根据类名(别名)名称拿到对应的类。
	 * 
	 * @param aliasName
	 * @return 类
	 */
	public static Class<?> getClassByAliasName(String aliasName) {
		// 数组类需要修正
		Class<?> clz = aliasClassMap.get(aliasName);
		if (null == clz) {
			// 如果是数组类的话
			if (ArrayUtil.tagNameIsArrayClass(aliasName)) {
				clz = ArrayUtil.getComponentClassByArrayName(aliasName);
			}
		}
		return clz;
	}

	/**
	 * 手动注册一个数组类。
	 * 
	 * @param clz
	 *            数组类
	 * 
	 */
	public static void registerArrayClass(Class<?> clz) {
		String clzName = Constant.ARRAY_CLZ + AliasCache.getClassName(ArrayUtil.getComponentClass(clz.getName()));
		aliasClassMap.put(clzName, clz);
		classAliasMap.put(clz, clzName);
	}
}

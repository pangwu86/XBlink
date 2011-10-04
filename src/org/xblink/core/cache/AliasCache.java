package org.xblink.core.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.xblink.annotation.XBlinkAlias;

/**
 * 别名的缓存。
 * 
 * TODO 可以加个开关，是否需要缓存
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AliasCache {

	private AliasCache() {
	}

	private static boolean useClassAliasCache = true;

	private static boolean useFieldAliasCache = true;

	// TODO 需要同步吗？会有多少性能影响呢
	private static Map<Class<?>, String> classAliasMap = new HashMap<Class<?>, String>();

	private static Map<Class<?>, Map<Field, String>> fieldAliasMap = new HashMap<Class<?>, Map<Field, String>>();

	static {
		// TODO 几个特殊的别名，先加入到Map中，例如Map.class等等

	}

	public static void setUseClassAliasCache(boolean use) {
		useClassAliasCache = use;
	}

	public static void setUseFieldAliasCache(boolean use) {
		useFieldAliasCache = use;
	}

	/**
	 * 获得成员名称。
	 * 
	 * @param clz
	 * @param field
	 * @return
	 */
	public static String getFieldName(Class<?> clz, Field field) {
		String fieldName = null;
		if (useFieldAliasCache) {
			Map<Field, String> fiedlNameMap = fieldAliasMap.get(clz);
			if (null == fiedlNameMap) {
				fiedlNameMap = new HashMap<Field, String>();
				fieldAliasMap.put(clz, fiedlNameMap);
			}
			fieldName = fiedlNameMap.get(field);
			if (null == fieldName) {
				// 查看是否有别名，没有就采用class.getName()
				fieldName = getFieldNameByAlias(field);
			}
			fiedlNameMap.put(field, fieldName);
		} else {
			fieldName = getFieldNameByAlias(field);
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
	 * @return
	 */
	public static String getClassName(Class<?> clz) {
		String className = null;
		if (useClassAliasCache) {
			className = classAliasMap.get(clz);
			if (null == className) {
				// 查看是否有别名，没有就采用class.getName()
				className = getClassNameByAlias(clz);
			}
			classAliasMap.put(clz, className);
		} else {
			className = getClassNameByAlias(clz);
		}
		return className;
	}

	private static String getClassNameByAlias(Class<?> clz) {
		XBlinkAlias classNameAlias = (XBlinkAlias) clz.getAnnotation(XBlinkAlias.class);
		String name = null;
		if (null != classNameAlias) {
			name = classNameAlias.value();
		} else {
			// TODO 这里到底采用哪种格式呢？
			// name = StringUtil.lowerFirst(clz.getSimpleName());
			// name = clz.getSimpleName().toLowerCase();
			name = clz.getSimpleName();
		}
		return name;
	}
}

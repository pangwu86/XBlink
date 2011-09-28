package org.xblink.core.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.xblink.annotation.XBlinkAlias;
import org.xblink.util.StringUtil;

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

	// TODO 需要同步吗？会有多少性能影响呢
	private static Map<Class<?>, String> classAliasMap = new HashMap<Class<?>, String>();

	private static Map<Class<?>, Map<Field, String>> fieldAliasMap = new HashMap<Class<?>, Map<Field, String>>();

	static {
		// TODO 几个特殊的别名，先加入到Map中，例如Map.class等等

	}

	/**
	 * 获得成员名称。
	 * 
	 * @param clz
	 * @param field
	 * @return
	 */
	public static String getFieldName(Class<?> clz, Field field) {
		Map<Field, String> fiedlNameMap = fieldAliasMap.get(clz);
		if (null == fiedlNameMap) {
			fiedlNameMap = new HashMap<Field, String>();
			fieldAliasMap.put(clz, fiedlNameMap);
		}

		String fieldName = fiedlNameMap.get(field);
		if (null == fieldName) {
			// 查看是否有别名，没有就采用class.getName()
			XBlinkAlias fieldNameAlias = (XBlinkAlias) field.getAnnotation(XBlinkAlias.class);
			fieldName = getFieldNameByAlias(fieldNameAlias, field, fiedlNameMap);
		}
		return fieldName;
	}

	private static String getFieldNameByAlias(XBlinkAlias fieldNameAlias, Field field, Map<Field, String> fiedlNameMap) {
		String name = null;
		if (null != fieldNameAlias) {
			name = fieldNameAlias.value();
		} else {
			name = field.getName();
		}
		fiedlNameMap.put(field, name);
		return name;
	}

	/**
	 * 获得类名称。
	 * 
	 * @param clz
	 * @return
	 */
	public static String getClassName(Class<?> clz) {
		String className = classAliasMap.get(clz);
		if (null == className) {
			// 查看是否有别名，没有就采用class.getName()
			XBlinkAlias classNameAlias = (XBlinkAlias) clz.getAnnotation(XBlinkAlias.class);
			className = getClassNameByAlias(classNameAlias, clz);
		}
		return className;
	}

	private static String getClassNameByAlias(XBlinkAlias classNameAlias, Class<?> clz) {
		String name = null;
		if (null != classNameAlias) {
			name = classNameAlias.value();
		} else {
			name = StringUtil.lowerFirst(clz.getSimpleName());
		}
		classAliasMap.put(clz, name);
		return name;
	}
}

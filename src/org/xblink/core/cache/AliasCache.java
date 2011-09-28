package org.xblink.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.xblink.annotation.XBlinkAlias;

/**
 * 类别名的缓存。
 * 
 * TODO Field的别名，不同的Class中可以有相同的field，别名可能会冲突，所以采用怎样一种形式缓存，需要研究下。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AliasCache {

	private AliasCache() {
	}

	// TODO 需要同步吗？会有多少性能影响呢
	private static Map<Class<?>, String> classAliasMap = new HashMap<Class<?>, String>();

	static {
		// TODO 几个特殊的别名，先加入到Map中，例如Map.class等等

	}

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
		if (null != classNameAlias) {
			return classNameAlias.value();
		}
		return clz.getName();
	}
}

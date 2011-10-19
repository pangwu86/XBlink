package org.xblink.util;

import java.util.HashMap;
import java.util.Map;

import org.xblink.core.Constant;

/**
 * 为数组类型提供几个方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ArrayUtil {

	private static Map<String, Class<?>> pMap = new HashMap<String, Class<?>>();

	static {
		pMap.put("[I", int.class);
		pMap.put("[B", byte.class);
		pMap.put("[S", short.class);
		pMap.put("[J", long.class);
		pMap.put("[F", float.class);
		pMap.put("[D", double.class);
		pMap.put("[Z", boolean.class);
		pMap.put("[C", char.class);
	}

	private ArrayUtil() {
	}

	public static boolean tagNameIsArrayName(String tagName) {
		// 不论什么类型的数组，都是以[开头
		return tagName.startsWith("[");
	}

	public static boolean tagNameIsArrayClass(String tagName) {
		// TODO 更加严谨的判断
		return tagName.startsWith(Constant.ARRAY_CLZ);
	}

	public static Class<?> getComponentClass(String tagName) {
		String cName = tagName.substring(tagName.lastIndexOf("["));
		Class<?> cClz = pMap.get(cName);
		if (null != cClz) {
			return cClz;
		}
		cName = tagName.substring(tagName.lastIndexOf("[") + 2, tagName.length() - 1);
		cClz = TypeUtil.tryFindThisClass(cName);
		return cClz;
	}

	public static Class<?> getComponentClassByArrayName(String arrayName) {
		String cName = arrayName.substring(Constant.ARRAY_CLZ.length());
		Class<?> cClz = TypeUtil.tryFindThisClass(cName);
		return cClz;
	}

}

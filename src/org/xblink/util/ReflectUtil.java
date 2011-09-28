package org.xblink.util;

import java.lang.reflect.Field;

/**
 * 提供反射的一些方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ReflectUtil {

	private ReflectUtil() {
	}

	public static Field[] getField(Class<?> clz) {
		return clz.getDeclaredFields();
	}

}

package org.xblink.core.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * 记录所有被使用过的类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class UsedClassCache {

	private static Set<Class<?>> usedClass = new HashSet<Class<?>>();

	/**
	 * 判断该类是否已经被注册过。
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean hasThisClass(Class<?> clz) {
		return usedClass.contains(clz);
	}

	/**
	 * 注册将要被使用的类。
	 * 
	 * @param clzes
	 */
	public static void registerClassToBeUsed(Class<?> clz) {
		usedClass.add(clz);
	}

	/**
	 * 注册将要被使用的类。
	 * 
	 * @param clzs
	 */
	public static void registerClassesToBeUsed(Class<?>[] clzs) {
		for (Class<?> clz : clzs) {
			registerClassToBeUsed(clz);
		}
	}
}

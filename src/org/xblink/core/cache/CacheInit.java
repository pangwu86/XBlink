package org.xblink.core.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;

/**
 * 对各个缓存进行初始化。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class CacheInit {

	private CacheInit() {
	}

	/**
	 * 别名缓存，使用类缓存初始化。
	 */
	public static void init() {
		// 首先是转换器中的类，包含的JAVA中大部分的常用类
		Collection<Converter> converters = ConverterWarehouse.getAllConverters();
		for (Converter converter : converters) {
			Class<?>[] types = converter.getTypes();
			for (Class<?> type : types) {
				registerClassToBeUsedAndSetClassNameInAliasCache(type);
			}
		}
		// 转换器中不包含的其他的JAVA中常用的几个类
		List<Class<?>> otherClzs = new ArrayList<Class<?>>();
		// 集合
		otherClzs.add(Queue.class);
		otherClzs.add(Collection.class);

		otherClzs.add(List.class);
		otherClzs.add(ArrayList.class);
		otherClzs.add(LinkedList.class);

		otherClzs.add(Stack.class);
		otherClzs.add(Vector.class);

		otherClzs.add(Set.class);
		otherClzs.add(HashSet.class);
		otherClzs.add(LinkedHashSet.class);
		otherClzs.add(SortedSet.class);
		otherClzs.add(TreeSet.class);
		otherClzs.add(EnumSet.class);

		otherClzs.add(Map.class);
		otherClzs.add(Map.Entry.class);
		otherClzs.add(HashMap.class);
		otherClzs.add(LinkedHashMap.class);
		otherClzs.add(SortedMap.class);
		otherClzs.add(TreeMap.class);
		otherClzs.add(WeakHashMap.class);
		otherClzs.add(EnumMap.class);

		otherClzs.add(Hashtable.class);
		otherClzs.add(Dictionary.class);

		otherClzs.add(ConcurrentMap.class);
		otherClzs.add(ConcurrentHashMap.class);
		otherClzs.add(ConcurrentLinkedQueue.class);

		// 数字类型的基类
		otherClzs.add(Number.class);
		// 字符类型的基类
		otherClzs.add(CharSequence.class);

		// TODO 再补充

		for (Class<?> type : otherClzs) {
			registerClassToBeUsedAndSetClassNameInAliasCache(type);
		}
	}

	/**
	 * 将一个类记录到使用类缓存中，并在别名缓存中初始化。
	 * 
	 * @param clz
	 */
	public static void registerClassToBeUsedAndSetClassNameInAliasCache(Class<?> clz) {
		UsedClassCache.registerClassToBeUsed(clz);
		AliasCache.getClassName(clz);
	}

}

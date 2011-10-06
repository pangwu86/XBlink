package org.xblink.core.reflect;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.xblink.core.reflect.impl.JavaReflectObjectOperator;
import org.xblink.core.reflect.impl.Sun14ReflectionObjectOperator;

/**
 * 根据当前使用的JDK版本情况，生产合适的对象操作器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ObjectOperatorFactory {

	private ObjectOperatorFactory() {
	}

	private static boolean useSun14ReflectionObjectCreater;

	private static ObjectOperator defaultObjectOperator;

	static {
		// TODO 如何获取JDK版本信息，并以此为依据判断使用哪个转换器。
		// System.getProperty("");
		useSun14ReflectionObjectCreater = true;
		if (useSun14ReflectionObjectCreater) {
			defaultObjectOperator = Sun14ReflectionObjectOperator.INSTANCE;
		} else {
			defaultObjectOperator = JavaReflectObjectOperator.INSTANCE;
		}
		// 添加几个默认的接口与实现
		defaultObjectOperator.addImpl(Map.class, HashMap.class);
		defaultObjectOperator.addImpl(List.class, ArrayList.class);
		defaultObjectOperator.addImpl(Set.class, HashSet.class);
		defaultObjectOperator.addImpl(SortedSet.class, TreeSet.class);
		defaultObjectOperator.addImpl(Calendar.class, GregorianCalendar.class);
	}

	/**
	 * 生成一个对象操作者。
	 * 
	 * @return
	 */
	public static ObjectOperator createObjectOperator() {
		// 感觉设置成单例就够用了。
		return defaultObjectOperator;
	}

}

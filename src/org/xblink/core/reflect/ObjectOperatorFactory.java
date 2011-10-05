package org.xblink.core.reflect;

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

	static {
		// TODO 如何获取JDK版本信息，并以此为依据判断使用哪个转换器。
		// System.getProperty("");
		useSun14ReflectionObjectCreater = true;
	}

	/**
	 * 生成一个对象操作者。
	 * 
	 * @return
	 */
	public static ObjectOperator createObjectOperator() {
		// 感觉设置成单例就够用了。
		if (useSun14ReflectionObjectCreater) {
			return Sun14ReflectionObjectOperator.INSTANCE;
		}
		return JavaReflectObjectOperator.INSTANCE;
	}

}

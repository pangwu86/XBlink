package org.xblink.core.reflect;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为ObjectCreater提供一个缓存机制。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractObjectOperator implements ObjectOperator {

	protected static Map<Class<?>, Constructor<?>> constructorCache = new ConcurrentHashMap<Class<?>, Constructor<?>>();

	/**
	 * 通过调用构造函数生成对象实例。
	 * 
	 * @param constructor
	 * @return
	 */
	protected Object newInstanceUsingConstructor(Constructor<?> constructor) {
		Object instance = null;
		try {
			instance = constructor.newInstance(new Object[0]);
		} catch (Exception e) {
			throw new UnsupportedOperationException("无法通过newInstance方法实例化对象。", e);
		}
		return instance;
	}
}

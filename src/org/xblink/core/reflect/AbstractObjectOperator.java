package org.xblink.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为ObjectCreater提供一个缓存机制。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractObjectOperator implements ObjectOperator {

	protected static Map<Class<?>, Constructor<?>> constructorCache = new ConcurrentHashMap<Class<?>, Constructor<?>>();

	protected static Map<Class<?>, Class<?>> implCache = new ConcurrentHashMap<Class<?>, Class<?>>();

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
		}
		catch (Exception e) {
			throw new UnsupportedOperationException("Can't instance an Object by the method newInstance",
													e);
		}
		return instance;
	}

	public void addImpl(Class<?> type, Class<?> defaultImpl) {
		implCache.put(type, defaultImpl);
	}

	public Class<?> getImpl(Class<?> type) {
		Class<?> impl = implCache.get(type);
		if (null == impl) {
			throw new RuntimeException(String.format(	"Can't find the impl class [%s]",
														type.getName()));
		}
		return impl;
	}

	public Object newInstance(Class<?> clz) {
		// 如果是接口实例化，需要提供对应的实现类
		Class<?> impl = clz;
		if (clz.isInterface()) {
			impl = getImpl(clz);
		}
		return newInstanceByImpl(impl);
	}

	protected abstract Object newInstanceByImpl(Class<?> clz);

	public void setField(Object obj, Field field, Object fieldValue) {
		// 防止往基本类型中赋值null引发的错误
		if (field.getType().isPrimitive() && null == fieldValue) {
			// 暂时什么也不做
		} else {
			setFieldWithoutNull(obj, field, fieldValue);
		}
	}

	protected abstract void setFieldWithoutNull(Object obj, Field field, Object fieldValue);

	public Class<?> getCollectionGenericType(Type gtype) {
		return getGenericType(gtype, 0);
	}

	public Class<?> getMapKeyGenericType(Type gtype) {
		return getGenericType(gtype, 0);
	}

	public Class<?> getMapValueGenericType(Type gtype) {
		return getGenericType(gtype, 1);
	}

	private Class<?> getGenericType(Type gtype, int index) {
		if (gtype instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) gtype).getActualTypeArguments()[index];
		}
		return null;
	}
}

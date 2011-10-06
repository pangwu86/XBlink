package org.xblink.core.reflect.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.xblink.core.reflect.AbstractObjectOperator;

/**
 * 采用常见的反射方法，生成实例。
 * 
 * 目前只支持具有默认构造函数的类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class JavaReflectObjectOperator extends AbstractObjectOperator {

	public static JavaReflectObjectOperator INSTANCE = new JavaReflectObjectOperator();

	protected Object newInstanceByImpl(Class<?> clz) {
		Constructor<?> constructor = constructorCache.get(clz);
		if (null != constructor) {
			return newInstanceUsingConstructor(constructor);
		}
		// 不能采用clz.newInstance()这个方法，因为可能对象的构造函数是非公开的
		try {
			Constructor<?>[] constructors = clz.getDeclaredConstructors();
			for (int i = 0; i < constructors.length; i++) {
				final Constructor<?> ctor = constructors[i];
				if (ctor.getParameterTypes().length == 0) {
					if (!ctor.isAccessible()) {
						ctor.setAccessible(true);
					}
					constructorCache.put(clz, ctor);
					return newInstanceUsingConstructor(ctor);
				}
			}
			if (Serializable.class.isAssignableFrom(clz)) {
				return newInstanceUsingJavaSerialization(clz);
			} else {
				throw new UnsupportedOperationException(String.format(
						"当前使用的JDK版本无法对%s这个类进行实例化操作，%s即没有默认构造函数也不支持JAVA的序列化。", clz.getName()));
			}
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	private Object newInstanceUsingJavaSerialization(Class<?> clz) {
		// TODO 如果该类是实现了java的序列化接口的话，可以尝试采用序列化的方式生成实例
		throw new UnsupportedOperationException("当前版本不支持通过JAVA的序列化方式生成实例对象。");
	}

	protected void setFieldWithoutNull(Object obj, Field field, Object fieldValue) {
		try {
			field.set(obj, fieldValue);
		} catch (Exception e) {
			throw new UnsupportedOperationException(
					String.format("无法为这个类%s的这个字段%s赋值。", obj.getClass(), field.getType()), e);

		}
	}

}

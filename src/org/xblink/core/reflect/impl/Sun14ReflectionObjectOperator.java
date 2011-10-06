package org.xblink.core.reflect.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.xblink.core.reflect.AbstractObjectOperator;

import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

/**
 * 采用sun.reflect包下提供的反射功能，进行类的实例化操作。
 * 
 * 支持对没有默认构造函数的类生成实例。
 * 
 * 这里参考了XStream的部分实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Sun14ReflectionObjectOperator extends AbstractObjectOperator {

	public static JavaReflectObjectOperator INSTANCE = new JavaReflectObjectOperator();

	private static ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();

	private static Unsafe unsafe;

	private static Map<Field, Long> fieldOffsetCache = new ConcurrentHashMap<Field, Long>();

	static {
		try {
			Class<?> clz = Class.forName("sun.misc.Unsafe");
			Field unsafeField = clz.getDeclaredField("theUnsafe");
			unsafeField.setAccessible(true);
			unsafe = (Unsafe) unsafeField.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Object newInstanceByImpl(Class<?> clz) {
		try {
			Constructor<?> constructor = getConstructor(clz);
			return newInstanceUsingConstructor(constructor);
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	private Constructor<?> getConstructor(Class<?> clz) throws Exception {
		Constructor<?> constructor = constructorCache.get(clz);
		if (null == constructor) {
			constructor = reflectionFactory.newConstructorForSerialization(clz,
					Object.class.getDeclaredConstructor(new Class[0]));
			constructorCache.put(clz, constructor);
		}
		return constructor;
	}

	protected void setFieldWithoutNull(Object obj, Field field, Object fieldValue) {
		try {
			long offset = getFieldOffset(field);
			Class<?> type = field.getType();
			if (type.isPrimitive()) {
				if (type.equals(Integer.TYPE)) {
					unsafe.putInt(obj, offset, ((Integer) fieldValue).intValue());
				} else if (type.equals(Long.TYPE)) {
					unsafe.putLong(obj, offset, ((Long) fieldValue).longValue());
				} else if (type.equals(Short.TYPE)) {
					unsafe.putShort(obj, offset, ((Short) fieldValue).shortValue());
				} else if (type.equals(Character.TYPE)) {
					unsafe.putChar(obj, offset, ((Character) fieldValue).charValue());
				} else if (type.equals(Byte.TYPE)) {
					unsafe.putByte(obj, offset, ((Byte) fieldValue).byteValue());
				} else if (type.equals(Float.TYPE)) {
					unsafe.putFloat(obj, offset, ((Float) fieldValue).floatValue());
				} else if (type.equals(Double.TYPE)) {
					unsafe.putDouble(obj, offset, ((Double) fieldValue).doubleValue());
				} else if (type.equals(Boolean.TYPE)) {
					unsafe.putBoolean(obj, offset, ((Boolean) fieldValue).booleanValue());
				}
			} else {
				unsafe.putObject(obj, offset, fieldValue);
			}
		} catch (IllegalArgumentException e) {
			throw new UnsupportedOperationException(
					String.format("无法为这个类%s的这个字段%s赋值。", obj.getClass(), field.getType()), e);
		}
	}

	private long getFieldOffset(Field f) {
		Long l = fieldOffsetCache.get(f);
		if (l == null) {
			l = new Long(unsafe.objectFieldOffset(f));
			fieldOffsetCache.put(f, l);
		}
		return l.longValue();
	}

}

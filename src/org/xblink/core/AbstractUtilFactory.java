package org.xblink.core;

import java.lang.reflect.Constructor;

import org.xblink.util.Lang;

/**
 * 工厂的几个方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class AbstractUtilFactory {

	/**
	 * Serializer的类名模板
	 */
	private final static String SERIALIZER_TEMPLATE = "org.xblink.core.impl.serializer.%SSerializer";

	/**
	 * Deserializer的类名模板
	 */
	private final static String DESERIALIZER_TEMPLATE = "org.xblink.core.impl.deserializer.%SDeserializer";

	protected static Serializer findSerializerByDocTypeName(Object obj, String docTypeName) {
		isBlankStr(docTypeName);
		String serializerClzName = String.format(SERIALIZER_TEMPLATE, docTypeName.toUpperCase());
		return (Serializer) getInstanceByObject(obj, serializerClzName, docTypeName);
	}

	protected static Deserializer findDeserializerByDocTypeName(Object obj, String docTypeName) {
		isBlankStr(docTypeName);
		String deserializerClzName = String
				.format(DESERIALIZER_TEMPLATE, docTypeName.toUpperCase());
		return (Deserializer) getInstanceByObject(obj, deserializerClzName, docTypeName);
	}

	private static Object getInstanceByObject(Object obj, String className, String docTypeName) {
		Object instance;
		try {
			Class<?> clz;
			Constructor<?> constructor;
			clz = Class.forName(className);
			// 实例化对象
			constructor = clz.getDeclaredConstructor(Object.class);
			instance = constructor.newInstance(obj);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类,无法执行后续操作。", docTypeName), e);
		} catch (Exception e) {
			throw new RuntimeException(String.format("%s格式的实现类实例化失败。", docTypeName), e);
		}
		return instance;
	}

	protected static void isNull(Object object) {
		if (null == object) {
			throw new RuntimeException("无法对一个空对象(null)进行序列化或反序列化操作。");
		}
	}

	private static void isBlankStr(String docTypeName) {
		// 格式名称
		if (Lang.isBlankStr(docTypeName)) {
			throw new RuntimeException("没有输入或指定转换文件的格式名称，无法执行序列化或反序列化操作。");
		}
	}

}

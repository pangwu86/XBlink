package org.xblink.core;

import java.lang.reflect.Constructor;

import org.xblink.core.impl.deserializer.JSONDeserializer;
import org.xblink.core.impl.deserializer.XMLDeserializer;
import org.xblink.core.impl.deserializer.YAMLDeserializer;
import org.xblink.core.impl.serializer.JSONSerializer;
import org.xblink.core.impl.serializer.XMLSerializer;
import org.xblink.core.impl.serializer.YAMLSerializer;
import org.xblink.util.Lang;

/**
 * 生产序列化器与反序列化器的工厂
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class SerialFactory {

	private SerialFactory() {
	}

	/**
	 * Serializer的类名模板
	 */
	private final static String SERIALIZER_TEMPLATE = "org.xblink.core.impl.serializer.%SSerializer";

	/**
	 * Deserializer的类名模板
	 */
	private final static String DESERIALIZER_TEMPLATE = "org.xblink.core.impl.deserializer.%SDeserializer";

	private static Serializer findSerializerByDocTypeName(Object obj, String docTypeName) {
		isBlankStr(docTypeName);
		String className = String.format(SERIALIZER_TEMPLATE, docTypeName.toUpperCase());
		Constructor<?> constructor = getConstructorForSerial(className);
		return createSerializer(obj, constructor, docTypeName);
	}

	private static Deserializer findDeserializerByDocTypeName(Object obj, Class<?> clz, String docTypeName) {
		isBlankStr(docTypeName);
		String className = String.format(DESERIALIZER_TEMPLATE, docTypeName.toUpperCase());
		Constructor<?> constructor = getConstructorForDeserial(className);
		return createDeserializer(obj, clz, constructor, docTypeName);
	}

	private static Constructor<?> getConstructorForSerial(String className) {
		Constructor<?> constructor = null;
		try {
			Class<?> clz = Class.forName(className);
			constructor = clz.getDeclaredConstructor(Object.class);
		} catch (Exception e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类，无法执行后续操作。", className), e);
		}
		return constructor;
	}

	private static Constructor<?> getConstructorForDeserial(String className) {
		Constructor<?> constructor = null;
		try {
			Class<?> clz = Class.forName(className);
			constructor = clz.getDeclaredConstructor(Object.class, Class.class);
		} catch (Exception e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类，无法执行后续操作。", className), e);
		}
		return constructor;
	}

	private static Serializer createSerializer(Object obj, Constructor<?> constructor, String docTypeName) {
		Serializer instance;
		try {
			instance = (Serializer) constructor.newInstance(obj);
		} catch (Exception e) {
			throw new RuntimeException(String.format("%s格式的实现类实例化失败。", docTypeName), e);
		}
		return instance;
	}

	private static Deserializer createDeserializer(Object obj, Class<?> clz, Constructor<?> constructor,
			String docTypeName) {
		Deserializer instance;
		try {
			instance = (Deserializer) constructor.newInstance(obj, clz);
		} catch (Exception e) {
			throw new RuntimeException(String.format("%s格式的实现类实例化失败。", docTypeName), e);
		}
		return instance;
	}

	public static void notSerializeNull(Object object) {
		if (null == object) {
			throw new RuntimeException("无法对一个空对象(null)进行序列化操作。");
		}
	}

	public static void notDeserializeNull(Object object, Class<?> clz) {
		if (null == object && null == clz) {
			throw new RuntimeException("没有参考对象或参考类，无法进行反序列化操作。");
		}
	}

	private static void isBlankStr(String docTypeName) {
		// 格式名称
		if (Lang.isBlankStr(docTypeName)) {
			throw new RuntimeException("没有输入或指定转换文件的格式名称，无法执行序列化或反序列化操作。");
		}
	}

	// ******************** 序列化器 ********************

	public static Serializer createXMLSerializer(Object object) {
		notSerializeNull(object);
		return new XMLSerializer(object);
	}

	public static Serializer createJSONSerializer(Object object) {
		notSerializeNull(object);
		return new JSONSerializer(object);
	}

	public static Serializer createYAMLSerializer(Object object) {
		notSerializeNull(object);
		return new YAMLSerializer(object);
	}

	public static Serializer createANYSerializer(Object object, String docTypeName) {
		notSerializeNull(object);
		return findSerializerByDocTypeName(object, docTypeName);
	}

	// ******************** 反序列化器 ********************

	public static Deserializer createXMLDeserializer(Object object, Class<?> clz) {
		notDeserializeNull(object, clz);
		return new XMLDeserializer(object, clz);
	}

	public static Deserializer createJSONDeserializer(Object object, Class<?> clz) {
		notDeserializeNull(object, clz);
		return new JSONDeserializer(object, clz);
	}

	public static Deserializer createYAMLDeserializer(Object object, Class<?> clz) {
		notDeserializeNull(object, clz);
		return new YAMLDeserializer(object, clz);
	}

	public static Deserializer createANYDeserializer(Object object, Class<?> clz, String docTypeName) {
		notDeserializeNull(object, clz);
		return findDeserializerByDocTypeName(object, clz, docTypeName);
	}

}

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

	public static Serializer findSerializerByDocTypeName(Object obj, String docTypeName) {
		isBlankStr(docTypeName);
		String serializerClzName = String.format(SERIALIZER_TEMPLATE, docTypeName.toUpperCase());
		return (Serializer) getInstanceByObject(obj, serializerClzName, docTypeName);
	}

	public static Deserializer findDeserializerByDocTypeName(Object obj, String docTypeName) {
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

	public static void isNull(Object object) {
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

	// ******************** 序列化器 ********************

	public static Serializer createXMLSerializer(Object object) {
		isNull(object);
		return new XMLSerializer(object);
	}

	public static Serializer createJSONSerializer(Object object) {
		isNull(object);
		return new JSONSerializer(object);
	}

	public static Serializer createYAMLSerializer(Object object) {
		isNull(object);
		return new YAMLSerializer(object);
	}

	public static Serializer createANYSerializer(Object object, String docTypeName) {
		isNull(object);
		return findSerializerByDocTypeName(object, docTypeName);
	}

	// ******************** 反序列化器 ********************

	public static Deserializer createXMLDeserializer(Object object) {
		isNull(object);
		return new XMLDeserializer(object);
	}

	public static Deserializer createJSONDeserializer(Object object) {
		isNull(object);
		return new JSONDeserializer(object);
	}

	public static Deserializer createYAMLDeserializer(Object object) {
		isNull(object);
		return new YAMLDeserializer(object);
	}

	public static Deserializer createANYDeserializer(Object object, String docTypeName) {
		isNull(object);
		return findDeserializerByDocTypeName(object, docTypeName);
	}

}

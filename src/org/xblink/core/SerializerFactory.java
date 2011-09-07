package org.xblink.core;

import org.xblink.core.impl.serializer.JSONSerializer;
import org.xblink.core.impl.serializer.XMLSerializer;
import org.xblink.core.impl.serializer.YAMLSerializer;

/**
 * 生产序列化器的工厂
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class SerializerFactory extends AbstractUtilFactory {

	private SerializerFactory() {
	}

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

}

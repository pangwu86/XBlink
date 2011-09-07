package org.xblink.core;

import org.xblink.core.impl.deserializer.JSONDeserializer;
import org.xblink.core.impl.deserializer.XMLDeserializer;
import org.xblink.core.impl.deserializer.YAMLDeserializer;

/**
 * 生产反序列化器的工厂
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class DeserializerFactory extends AbstractUtilFactory {

	private DeserializerFactory() {
	}

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

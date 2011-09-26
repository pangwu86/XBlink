package org.xblink.core.serial;

import org.xblink.core.serial.impl.DefaultDeserializer;
import org.xblink.core.serial.impl.DefaultSerializer;

/**
 * 生产序列化器与反序列化器的工厂
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class SerialFactory {

	private SerialFactory() {
	}

	private static void serializeNull(Object object) {
		if (null == object) {
			throw new RuntimeException("无法对一个空对象(null)进行序列化操作。");
		}
	}

	private static void deserializeNull(Object object, Class<?> clz) {
		if (null == object && null == clz) {
			throw new RuntimeException("没有参考对象或参考类，无法进行反序列化操作。");
		}
	}

	public static Serializer createSerializer(Object object) {
		serializeNull(object);
		return new DefaultSerializer(object);
	}

	public static Deserializer createDeserializer(Object object, Class<?> clz) {
		deserializeNull(object, clz);
		return new DefaultDeserializer(object, clz);
	}

}

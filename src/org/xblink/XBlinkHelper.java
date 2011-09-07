package org.xblink;

import java.io.IOException;
import java.io.OutputStream;

import org.xblink.core.Director;
import org.xblink.core.Serializer;
import org.xblink.core.SerializerFactory;

/**
 * XBlink功能实现类，算是一个辅助类吧，其实是为了XBlink这个类看起来更加简洁，把几个方法放在这个类中了。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
class XBlinkHelper {

	private XBlinkHelper() {
	}

	// ***************************************序列化****************************************

	// 序列化通用方法

	static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream, String docTypeName) {
		return serializing(SerializerFactory.createANYSerializer(obj, docTypeName), xbConfig,
				outputStream);
	}

	// 序列化默认方法

	static String toJSON(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serializing(SerializerFactory.createJSONSerializer(obj), xbConfig, outputStream);
	}

	static String toXML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serializing(SerializerFactory.createXMLSerializer(obj), xbConfig, outputStream);
	}

	static String toYAML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serializing(SerializerFactory.createYAMLSerializer(obj), xbConfig, outputStream);
	}

	private static String serializing(Serializer serializer, XBConfig xbConfig,
			OutputStream outputStream) {
		// 生成字符串
		String str = Director.serialize(serializer,
				null == xbConfig ? XBConfig.getDefaultXBConfig() : xbConfig);
		// 写入文件
		if (null != outputStream) {
			try {
				// TODO 需要改进一下，这么写应该回有问题，特别是大文件时
				outputStream.write(str.getBytes());
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				throw new RuntimeException("无法生成文件。", e);
			}
		}
		// 返回生成结果
		return str;
	}

	// ***************************************反序列化****************************************

}

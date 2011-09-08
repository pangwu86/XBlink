package org.xblink;

import org.xblink.core.DocWriter;
import org.xblink.core.SerialFactory;
import org.xblink.core.Serializer;

/**
 * XBlink小助手，算是一个辅助类吧，其实是为了XBlink这个类看起来更加简洁，只保留对外的API，而把几个不想公开的方法放在这个类中了。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
class XBlinkHelper {

	private XBlinkHelper() {
	}

	// ***************************************序列化****************************************

	// 序列化通用方法

	static String toAny(Object obj, XBConfig xbConfig, DocWriter docWriter, String docTypeName) {
		return serializing(SerialFactory.createANYSerializer(obj, docTypeName), xbConfig, docWriter);
	}

	// 序列化默认方法

	static String toXML(Object obj, XBConfig xbConfig, DocWriter docWriter) {
		return serializing(SerialFactory.createXMLSerializer(obj), xbConfig, docWriter);
	}

	/**
	 * 序列化。
	 * 
	 * @param serializer
	 *            序列化器
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param docWriter
	 *            文档输出器
	 * @return 字符串
	 */
	private static String serializing(Serializer serializer, XBConfig xbConfig, DocWriter docWriter) {
		// TODO

		return null;
	}

	// ***************************************反序列化****************************************

}

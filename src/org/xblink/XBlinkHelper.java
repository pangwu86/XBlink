package org.xblink;

import java.io.Reader;
import java.io.Writer;

import org.xblink.core.doc.DocReader;
import org.xblink.core.doc.DocWorkerFactory;
import org.xblink.core.doc.DocWriter;
import org.xblink.core.serial.Deserializer;
import org.xblink.core.serial.SerialFactory;
import org.xblink.core.serial.Serializer;
import org.xblink.util.StringUtil;

/**
 * XBlink小助手，算是一个辅助类吧，其实是为了XBlink这个类看起来更加简洁，只保留对外的API，而把几个不想公开的方法放在这个类中了。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
class XBlinkHelper {

	private XBlinkHelper() {
	}

	private static void missDocTypeName(String docTypeName) {
		// 格式名称
		if (StringUtil.isBlankStr(docTypeName)) {
			throw new RuntimeException("没有输入或指定转换文件的[格式名称]，无法执行对应格式的序列化或反序列化操作。");
		}
	}

	// ***************************************序列化****************************************

	protected static String toAny(Object obj, XBConfig xbConfig, Writer writer, String docTypeName) {
		missDocTypeName(docTypeName);
		return serializing(SerialFactory.createSerializer(obj), xbConfig,
				DocWorkerFactory.createAnyWriter(writer, docTypeName));
	}

	protected static String toXml(Object obj, XBConfig xbConfig, Writer writer) {
		return serializing(SerialFactory.createSerializer(obj), xbConfig, DocWorkerFactory.createXmlWriter(writer));
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

	@SuppressWarnings("unchecked")
	protected static <T> T fromAny(Class<T> clz, XBConfig xbConfig, Reader reader, String docTypeName) {
		missDocTypeName(docTypeName);
		return (T) deserializing(SerialFactory.createDeserializer(null, clz), xbConfig,
				DocWorkerFactory.createAnyReader(reader, docTypeName));
	}

	protected static Object fromAny(Object obj, XBConfig xbConfig, Reader reader, String docTypeName) {
		missDocTypeName(docTypeName);
		return deserializing(SerialFactory.createDeserializer(obj, null), xbConfig,
				DocWorkerFactory.createAnyReader(reader, docTypeName));
	}

	@SuppressWarnings("unchecked")
	protected static <T> T fromXml(Class<T> clz, XBConfig xbConfig, Reader reader) {
		return (T) deserializing(SerialFactory.createDeserializer(null, clz), xbConfig,
				DocWorkerFactory.createXmlReader(reader));
	}

	protected static Object fromXml(Object obj, XBConfig xbConfig, Reader reader) {
		return deserializing(SerialFactory.createDeserializer(obj, null), xbConfig,
				DocWorkerFactory.createXmlReader(reader));
	}

	/**
	 * 反序列化。
	 * 
	 * @param deserializer
	 *            反序列化器
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param DocReader
	 *            文档读取器
	 * @return 字符串
	 */
	private static Object deserializing(Deserializer deserializer, XBConfig xbConfig, DocReader docReader) {
		// TODO
		return null;
	}

}

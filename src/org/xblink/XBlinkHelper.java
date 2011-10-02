package org.xblink;

import java.io.Reader;
import java.io.Writer;

import org.xblink.core.TransferInfo;
import org.xblink.core.doc.DocReader;
import org.xblink.core.doc.DocWorkerFactory;
import org.xblink.core.doc.DocWriter;
import org.xblink.core.path.PathTracker;
import org.xblink.core.path.PathTrackingWriter;
import org.xblink.core.serial.Serializer;
import org.xblink.util.StringUtil;

/**
 * XBlink小助手，算是一个辅助类吧，其实是为了XBlink这个类看起来更加简洁，只保留对外的API，而把几个不想公开的方法放在这个类中了。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
class XBlinkHelper {

	private XBlinkHelper() {
	}

	private static void missDocTypeName(String docTypeName) {
		// 格式名称
		if (StringUtil.isBlankStr(docTypeName)) {
			throw new IllegalArgumentException("没有输入或指定转换文件的[格式名称]，无法执行对应格式的序列化或反序列化操作。");
		}
	}

	private static void serializeNull(Object object) {
		if (null == object) {
			throw new IllegalArgumentException("无法对一个空对象(null)进行序列化操作。");
		}
	}

	private static void deserializeNull(Object object, Class<?> clz) {
		if (null == object && null == clz) {
			throw new IllegalArgumentException("没有参考对象或参考类，无法进行反序列化操作。");
		}
	}

	// ***************************************序列化****************************************

	protected static String toAny(Object object, Writer writer, String docTypeName) {
		missDocTypeName(docTypeName);
		serializeNull(object);
		return serializing(object, DocWorkerFactory.createAnyWriter(writer, docTypeName));
	}

	protected static String toXml(Object object, Writer writer) {
		serializeNull(object);
		return serializing(object, DocWorkerFactory.createXmlWriter(writer));
	}

	/**
	 * 序列化。如果不生成文件，则直接返回生成的字符串。
	 * 
	 * @param object
	 *            被序列化的对象
	 * @param docWriter
	 *            文档输出器
	 * @return 字符串
	 */
	private static String serializing(Object object, DocWriter docWriter) {
		// 准备工作
		XBConfig xbConfig = XBConfigHelper.getXbConfig();
		PathTracker pathTracker = new PathTracker(xbConfig.isUseRelativePath());
		DocWriter realDocWriter = new PathTrackingWriter(docWriter, pathTracker);
		try {
			// 开始序列化
			realDocWriter.writeStartDocument();
			Serializer.writeUnknow(object, new TransferInfo(pathTracker, xbConfig, realDocWriter, null), null);
			realDocWriter.writeEndDocument();
		} catch (Exception e) {
			throw new RuntimeException("序列化失败。", e);
		} finally {
			if (null != realDocWriter) {
				try {
					realDocWriter.close();
				} catch (Exception e) {
					throw new RuntimeException("关闭输出流失败。", e);
				}
			}
		}
		return docWriter.getString();
	}

	// ***************************************反序列化****************************************

	@SuppressWarnings("unchecked")
	protected static <T> T fromAny(Class<T> clz, Reader reader, String docTypeName) {
		missDocTypeName(docTypeName);
		deserializeNull(null, clz);
		return (T) deserializing(null, clz, DocWorkerFactory.createAnyReader(reader, docTypeName));
	}

	protected static Object fromAny(Object object, Reader reader, String docTypeName) {
		missDocTypeName(docTypeName);
		deserializeNull(object, null);
		return deserializing(object, null, DocWorkerFactory.createAnyReader(reader, docTypeName));
	}

	@SuppressWarnings("unchecked")
	protected static <T> T fromXml(Class<T> clz, Reader reader) {
		deserializeNull(null, clz);
		return (T) deserializing(null, clz, DocWorkerFactory.createXmlReader(reader));
	}

	protected static Object fromXml(Object object, Reader reader) {
		deserializeNull(object, null);
		return deserializing(object, null, DocWorkerFactory.createXmlReader(reader));
	}

	/**
	 * 反序列化。
	 * 
	 * @param object
	 *            参考对象
	 * @param clz
	 *            参考类
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param DocReader
	 *            文档读取器
	 * @return 字符串
	 */
	private static Object deserializing(Object object, Class<?> clz, DocReader docReader) {
		// TODO
		return null;
	}

}

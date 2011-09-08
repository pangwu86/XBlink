package org.xblink;

import java.io.OutputStream;
import java.io.Writer;

import org.xblink.core.DocWorkerFactory;

/**
 * XBlink(吾爱跳刀)，通用序列化反序列化工具集。<BR>
 * 
 * 支持JAVA中的基本类型，对象类型，数组类型，List，Set，Map，枚举等。<BR>
 * 
 * 支持生成XML，JSON，YAML,及任意你喜欢的格式文件。（当然非默认格式的输出需要你加入对应的实现）<BR>
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBlink {

	private XBlink() {
	}

	/**
	 * 使用你设定的配置项，替换原有默认配置信息（全局唯一）。
	 */
	public static void setDefaultXBConfig(XBConfig xbConfig) {
		XBConfigHelper.setDefaultXBConfig(xbConfig);
	}

	/**
	 * 使用你设定的配置项，使用一次后马上失效。
	 * 
	 */
	public static void setTransientXBConfig(XBConfig xbConfig) {
		XBConfigHelper.setTransientXBConfig(xbConfig);
	}

	// ********************************* 通用序列化 *********************************

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。<BR>
	 * 
	 * 获得你需要的格式的字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 字符串
	 */
	public static String toAny(Object obj, String docTypeName) {
		return XBlinkHelper.toAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(), docTypeName);
	}

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。<BR>
	 * 
	 * 生成对应的文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param filePath
	 *            保存文件的路径(绝对路径)
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 */
	public static void toAny(Object obj, String filePath, String docTypeName) {
		XBlinkHelper.toAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(filePath), docTypeName);
	}

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。<BR>
	 * 
	 * 生成对应的文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 */
	public static void toAny(Object obj, OutputStream outputStream, String docTypeName) {
		XBlinkHelper.toAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(outputStream),
				docTypeName);
	}

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。<BR>
	 * 
	 * 生成对应的文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param writer
	 *            字符输出流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 */
	public static void toAny(Object obj, Writer writer, String docTypeName) {
		XBlinkHelper.toAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(writer), docTypeName);
	}

	// ****************************** 通用反序列化 ******************************

	// ****************************** XML序列化 *********************************

	/**
	 * 返回XML文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @return XML字符串
	 */
	public static String toXML(Object obj) {
		return XBlinkHelper.toXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter());
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param filePath
	 *            保存文件的路径(绝对路径)
	 */
	public static void toXML(Object obj, String filePath) {
		XBlinkHelper.toXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(filePath));
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 */
	public static void toXML(Object obj, OutputStream outputStream) {
		XBlinkHelper.toXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(outputStream));
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param writer
	 *            字节输出流
	 */
	public static void toXML(Object obj, Writer writer) {
		XBlinkHelper.toXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(writer));
	}

	// ****************************** XML反序列化 ********************************

	public static <T> T fromXML(CharSequence cs, Class<T> clz) {

		return null;
	}

	public static Object fromXML(CharSequence cs, Object obj) {

		return null;
	}

}

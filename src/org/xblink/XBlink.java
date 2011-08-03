package org.xblink;

import java.io.OutputStream;

import org.xblink.util.IOUtil;

/**
 * XBlink(吾爱跳刀)，通用序列化反序列化工具集。<BR>
 * 
 * 支持JAVA中的基本类型，对象类型，数组类型，List，Set，Map，枚举等。<BR>
 * 
 * 支持生成XML，JSON，YAML,及任意你喜欢的格式文件。（当然非默认格式的输出需要你加入对应的实现）<BR>
 * 
 * 
 * @author pangwu86@gmail.com
 * 
 */
public class XBlink {

	private XBlink() {
	}

	// ****** 通用序列化方法 ******

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, String docTypeName) {
		return XBlinkImpl.toAny(obj, xbConfig, null, docTypeName);
	}

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param filePath
	 *            保存文件的路径
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, String filePath, String docTypeName) {
		return XBlinkImpl.toAny(obj, xbConfig, IOUtil.getOutputStream(filePath), docTypeName);
	}

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param outputStream
	 *            输出流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream, String docTypeName) {
		return XBlinkImpl.toAny(obj, xbConfig, outputStream, docTypeName);
	}

	// ******　XML序列化方法　××××××

	/**
	 * 返回XML文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return XML字符串
	 */
	public static String toXML(Object obj, XBConfig xbConfig) {
		return XBlinkImpl.toXML(obj, xbConfig, null);
	}

	/**
	 * 返回XML文件字符串，生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param filePath
	 *            保存文件的路径
	 * @return XML字符串
	 */
	public static String toXML(Object obj, XBConfig xbConfig, String filePath) {
		return XBlinkImpl.toXML(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 返回XML文件字符串，生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param outputStream
	 *            输出流
	 * @return XML字符串
	 */
	public static String toXML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return XBlinkImpl.toXML(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// ******　JSON序列化方法　××××××

	/**
	 * 返回JSON文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, XBConfig xbConfig) {
		return XBlinkImpl.toJSON(obj, xbConfig, null);
	}

	/**
	 * 返回JSON文件字符串，生成JSON文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param filePath
	 *            保存文件的路径
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, XBConfig xbConfig, String filePath) {
		return XBlinkImpl.toJSON(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 返回JSON文件字符串，生成JSON文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param outputStream
	 *            输出流
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return XBlinkImpl.toJSON(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// ******　YAML序列化方法　××××××

	/**
	 * 返回YAML文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, XBConfig xbConfig) {
		return XBlinkImpl.toYAML(obj, xbConfig, null);
	}

	/**
	 * 返回YAML文件字符串，生成YAML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param filePath
	 *            保存文件的路径
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, XBConfig xbConfig, String filePath) {
		return XBlinkImpl.toYAML(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 返回YAML文件字符串，生成YAML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param outputStream
	 *            输出流
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return XBlinkImpl.toYAML(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// XXX 反序列化方法

}

package org.xblink;

import java.io.OutputStream;

import org.xblink.core.XBlinkUtil;
import org.xblink.util.IOUtil;

/**
 * XBlink(吾爱跳刀)，通用序列化反序列化工具集。<BR>
 * 
 * 支持JAVA中的基本类型，对象类型，数组类型，List，Set，Map等集合类型。<BR>
 * 
 * 支持生成XML，JSON，YAML,及任意你喜欢的格式文件。（当然非默认格式的输出需要你加入对应的实现）<BR>
 * 
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class XBlink {

	// ****** 通用序列化方法 ******

	/**
	 * toAny意味着You will get what you want（你会得到任何你想要的东西）<BR>
	 * 
	 * XBlink最NB的方法，生成任意你想要的格式字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param wanted
	 *            任意你想要的格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, String wanted) {
		return XBlinkUtil.toAny(obj, xbConfig, null, wanted);
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
	 * @param wanted
	 *            任意你想要的格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, String filePath, String wanted) {
		return toAny(obj, xbConfig, IOUtil.getOutputStream(filePath), wanted);
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
	 * @param wanted
	 *            任意你想要的格式名称，例如EXCEL或者XML
	 * @return
	 */
	public static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream,
			String wanted) {
		return XBlinkUtil.toAny(obj, xbConfig, outputStream, wanted);
	}

	// ******　XML序列化方法　××××××

	/**
	 * 生成XML字符串。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @return XML字符串
	 */
	public static String toXML(Object obj) {
		return XBlinkUtil.toXML(obj, null, null);
	}

	/**
	 * 生成XML字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return XML字符串
	 */
	public static String toXML(Object obj, XBConfig xbConfig) {
		return XBlinkUtil.toXML(obj, xbConfig, null);
	}

	/**
	 * 生成XML文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param filePath
	 *            保存文件的路径
	 * @return XML字符串
	 */
	public static String toXML(Object obj, String filePath) {
		return XBlinkUtil.toXML(obj, null, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成XML文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 * @return XML字符串
	 */
	public static String toXML(Object obj, OutputStream outputStream) {
		return XBlinkUtil.toXML(obj, null, IOUtil.getOutputStream(outputStream));
	}

	/**
	 * 生成XML文件。
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
		return XBlinkUtil.toXML(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成XML文件。
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
		return XBlinkUtil.toXML(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// ******　JSON序列化方法　××××××

	/**
	 * 生成JSON字符串。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj) {
		return XBlinkUtil.toJSON(obj, null, null);
	}

	/**
	 * 生成JSON字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, XBConfig xbConfig) {
		return XBlinkUtil.toJSON(obj, xbConfig, null);
	}

	/**
	 * 生成JSON文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param filePath
	 *            保存文件的路径
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, String filePath) {
		return XBlinkUtil.toJSON(obj, null, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成JSON文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 * @return JSON字符串
	 */
	public static String toJSON(Object obj, OutputStream outputStream) {
		return XBlinkUtil.toJSON(obj, null, IOUtil.getOutputStream(outputStream));
	}

	/**
	 * 生成JSON文件。
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
		return XBlinkUtil.toJSON(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成JSON文件。
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
		return XBlinkUtil.toJSON(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// ******　YAML序列化方法　××××××

	/**
	 * 生成YAML字符串。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj) {
		return XBlinkUtil.toYAML(obj, null, null);
	}

	/**
	 * 生成YAML字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param xbConfig
	 *            XBlink配置信息
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, XBConfig xbConfig) {
		return XBlinkUtil.toYAML(obj, xbConfig, null);
	}

	/**
	 * 生成YAML文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param filePath
	 *            保存文件的路径
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, String filePath) {
		return XBlinkUtil.toYAML(obj, null, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成YAML文件。（默认配置）
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 * @return YAML字符串
	 */
	public static String toYAML(Object obj, OutputStream outputStream) {
		return XBlinkUtil.toYAML(obj, null, IOUtil.getOutputStream(outputStream));
	}

	/**
	 * 生成YAML文件。
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
		return XBlinkUtil.toYAML(obj, xbConfig, IOUtil.getOutputStream(filePath));
	}

	/**
	 * 生成YAML文件。
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
		return XBlinkUtil.toYAML(obj, xbConfig, IOUtil.getOutputStream(outputStream));
	}

	// XXX 反序列化方法

}

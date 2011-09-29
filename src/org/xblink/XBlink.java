package org.xblink;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.util.IOUtil;

/**
 * XBlink(吾爱跳刀)，通用序列化反序列化工具集。
 * 
 * 支持JAVA中的基本类型，对象类型，数组类型，List，Set，Map，枚举等。
 * 
 * 支持生成XML，JSON，YAML,及任意你喜欢的格式文件。（当然非默认格式的输出需要你加入对应的实现）
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBlink {

	private XBlink() {
	}

	/**
	 * 打开缓存。(默认状态为打开)
	 */
	public static void openCache() {
		AliasCache.setUseClassAliasCache(true);
		AliasCache.setUseFieldAliasCache(true);
		AnalysisCache.setUseAnalysisCache(true);
	}

	/**
	 * 关闭缓存。
	 */
	public static void closeCache() {
		AliasCache.setUseClassAliasCache(false);
		AliasCache.setUseFieldAliasCache(false);
		AnalysisCache.setUseAnalysisCache(false);
	}

	/**
	 * 使用你设定的配置项，替换原有默认配置信息（全局唯一）。
	 */
	public static void setGlobalXBConfig(XBConfig xbConfig) {
		XBConfigHelper.setGlobalXBConfig(xbConfig);
	}

	/**
	 * 使用你设定的配置项，使用一次后马上失效。
	 */
	public static void setTransientXBConfig(XBConfig xbConfig) {
		XBConfigHelper.setTransientXBConfig(xbConfig);
	}

	/**
	 * 清空缓存。
	 */
	public static void cleanCache() {
		// TODO
	}

	// ********************************* 通用序列化 *********************************

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。
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
		return XBlinkHelper.toAny(obj, IOUtil.createWriter(), docTypeName);
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。
	 * 
	 * 生成对应的文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param file
	 *            要保存的文件
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 */
	public static void toAny(Object obj, File file, String docTypeName) {
		XBlinkHelper.toAny(obj, IOUtil.createWriter(file), docTypeName);
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。
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
		XBlinkHelper.toAny(obj, IOUtil.createWriter(outputStream), docTypeName);
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。
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
		XBlinkHelper.toAny(obj, IOUtil.createWriter(writer), docTypeName);
	}

	// ****************************** 通用反序列化 ******************************

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromAny(CharSequence cs, Class<T> clz, String docTypeName) {
		return XBlinkHelper.fromAny(clz, IOUtil.createReader(cs), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param obj
	 *            参考对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(CharSequence cs, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, IOUtil.createReader(cs), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param file
	 *            待解析的文件
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromAny(File file, Class<T> clz, String docTypeName) {
		return XBlinkHelper.fromAny(clz, IOUtil.createReader(file), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param file
	 *            待解析的文件
	 * @param obj
	 *            参考对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(File file, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, IOUtil.createReader(file), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param in
	 *            输入流
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromAny(InputStream in, Class<T> clz, String docTypeName) {
		return XBlinkHelper.fromAny(clz, IOUtil.createReader(in), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param in
	 *            输入流
	 * @param obj
	 *            参考对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(InputStream in, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, IOUtil.createReader(in), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromAny(Reader reader, Class<T> clz, String docTypeName) {
		return XBlinkHelper.fromAny(clz, IOUtil.createReader(reader), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param obj
	 *            参考对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(Reader reader, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, IOUtil.createReader(reader), docTypeName);
	}

	// ****************************** XML序列化 *********************************

	/**
	 * 返回XML文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @return XML字符串
	 */
	public static String toXml(Object obj) {
		return XBlinkHelper.toXml(obj, IOUtil.createWriter());
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param file
	 *            要保存的文件
	 */
	public static void toXml(Object obj, File file) {
		XBlinkHelper.toXml(obj, IOUtil.createWriter(file));
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param outputStream
	 *            输出流
	 */
	public static void toXml(Object obj, OutputStream outputStream) {
		XBlinkHelper.toXml(obj, IOUtil.createWriter(outputStream));
	}

	/**
	 * 生成XML文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param writer
	 *            字节输出流
	 */
	public static void toXml(Object obj, Writer writer) {
		XBlinkHelper.toXml(obj, IOUtil.createWriter(writer));
	}

	// ****************************** XML反序列化 ********************************

	/**
	 * 输入XML信息，根据参考类进行反序列化，返回对应对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param clz
	 *            參考类
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromXml(CharSequence cs, Class<T> clz) {
		return XBlinkHelper.fromXml(clz, IOUtil.createReader(cs));
	}

	/**
	 * 输入XML信息，根据参考对象进行反序列化，返回对应对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param obj
	 *            參考对象
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromXml(CharSequence cs, Object obj) {
		return XBlinkHelper.fromXml(obj, IOUtil.createReader(cs));
	}

	/**
	 * 输入XML信息，根据参考类进行反序列化，返回对应对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @param clz
	 *            參考类
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromXml(File file, Class<T> clz) {
		return XBlinkHelper.fromXml(clz, IOUtil.createReader(file));
	}

	/**
	 * 输入XML信息，根据参考对象进行反序列化，返回对应对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @param obj
	 *            參考对象
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromXml(File file, Object obj) {
		return XBlinkHelper.fromXml(obj, IOUtil.createReader(file));
	}

	/**
	 * 输入XML信息，根据参考类进行反序列化，返回对应对象。
	 * 
	 * @param in
	 *            输入流
	 * @param clz
	 *            參考类
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromXml(InputStream in, Class<T> clz) {
		return XBlinkHelper.fromXml(clz, IOUtil.createReader(in));
	}

	/**
	 * 输入XML信息，根据参考对象进行反序列化，返回对应对象。
	 * 
	 * @param in
	 *            输入流
	 * @param obj
	 *            參考对象
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromXml(InputStream in, Object obj) {
		return XBlinkHelper.fromXml(obj, IOUtil.createReader(in));
	}

	/**
	 * 输入XML信息，根据参考类进行反序列化，返回对应对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param clz
	 *            參考类
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromXml(Reader reader, Class<T> clz) {
		return XBlinkHelper.fromXml(clz, IOUtil.createReader(reader));
	}

	/**
	 * 输入XML信息，根据参考对象进行反序列化，返回对应对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param obj
	 *            參考对象
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromXml(Reader reader, Object obj) {
		return XBlinkHelper.fromXml(obj, IOUtil.createReader(reader));
	}

}

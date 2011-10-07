package org.xblink;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.cache.UsedClassCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterRegistry;
import org.xblink.core.convert.ConverterWarehouse;
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
 */
public class XBlink {

	static {
		// 初始化部分容器
		ConverterWarehouse.init();
	}

	private XBlink() {
	}

	/**
	 * 获得版本信息。
	 * 
	 * @return 版本信息
	 */
	public static String getVersion() {
		return "1.0.0";
	}

	// ********************** 各种设定项 ********************

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
	 * 清空缓存。
	 */
	public static void cleanCache() {
		// TODO
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
	 * 注册一批将要被序列化或反序列化的类。<br>
	 * 
	 * 注册之后，生成的序列化文件中，节点名称将更加简洁。
	 * 
	 * @param clzs
	 */
	public static void registerClassesToBeUsed(Class<?>[] clzs) {
		UsedClassCache.registerClassesToBeUsed(clzs);
	}

	/**
	 * 注册一个将要被序列化或反序列化的类。<br>
	 * 
	 * 注册之后，生成的序列化文件中，节点名称将更加简洁。
	 * 
	 * @param classes
	 */
	public static void registerClassToBeUsed(Class<?> clz) {
		UsedClassCache.registerClassToBeUsed(clz);
	}

	/**
	 * 注册一个转换器。
	 * 
	 * @param converterClz
	 */
	public static void registerConverter(Class<? extends Converter> converterClz) {
		ConverterRegistry.register(converterClz);
	}

	// ********************************* 通用序列化 *********************************

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件<br>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<br>
	 * <br>
	 * 
	 * 根据docTypeName，返回对应格式的文件字符串。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 字符串
	 */
	public static String toAny(Object obj, String docTypeName) {
		return XBlinkHelper.toAny(obj, docTypeName, IOUtil.createWriter());
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件<br>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<br>
	 * <br>
	 * 
	 * 根据docTypeName，生成文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param file
	 *            要保存的文件
	 */
	public static void toAny(Object obj, String docTypeName, File file) {
		XBlinkHelper.toAny(obj, docTypeName, IOUtil.createWriter(file));
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件<br>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<br>
	 * <br>
	 * 
	 * 根据docTypeName，生成文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param outputStream
	 *            输出流
	 */
	public static void toAny(Object obj, String docTypeName, OutputStream outputStream) {
		XBlinkHelper.toAny(obj, docTypeName, IOUtil.createWriter(outputStream));
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件<br>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<br>
	 * <br>
	 * 
	 * 根据docTypeName，生成文件。
	 * 
	 * @param obj
	 *            需要被序列化对象
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param writer
	 *            字符输出流
	 */
	public static void toAny(Object obj, String docTypeName, Writer writer) {
		XBlinkHelper.toAny(obj, docTypeName, IOUtil.createWriter(writer));
	}

	// ****************************** 通用反序列化 ******************************

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 对象
	 */
	public static Object fromAny(CharSequence cs, String docTypeName) {
		return XBlinkHelper.fromAny(IOUtil.createReader(cs), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param clz
	 *            參考类
	 * @param obj
	 *            参考对象
	 * @return 对象
	 */
	public static Object fromAny(CharSequence cs, String docTypeName, Class<?> clz, Object obj) {
		return XBlinkHelper.fromAny(IOUtil.createReader(cs), docTypeName, clz, obj);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 对象
	 */
	public static Object fromAny(File file, String docTypeName) {
		return XBlinkHelper.fromAny(IOUtil.createReader(file), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param clz
	 *            參考类
	 * @param obj
	 *            参考对象
	 * @return 对象
	 */
	public static Object fromAny(File file, String docTypeName, Class<?> clz, Object obj) {
		return XBlinkHelper.fromAny(IOUtil.createReader(file), docTypeName, clz, obj);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param in
	 *            输入流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 对象
	 */
	public static Object fromAny(InputStream in, String docTypeName) {
		return XBlinkHelper.fromAny(IOUtil.createReader(in), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param in
	 *            输入流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param clz
	 *            參考类
	 * @param obj
	 *            参考对象
	 * @return 对象
	 */
	public static Object fromAny(InputStream in, String docTypeName, Class<?> clz, Object obj) {
		return XBlinkHelper.fromAny(IOUtil.createReader(in), docTypeName, clz, obj);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 对象
	 */
	public static Object fromAny(Reader reader, String docTypeName) {
		return XBlinkHelper.fromAny(IOUtil.createReader(reader), docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象。<br>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<br>
	 * <br>
	 * 
	 * 根据docTypeName与其格式特点，进行反序列化生成对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @param clz
	 *            參考类
	 * @param obj
	 *            参考对象
	 * @return 对象
	 */
	public static Object fromAny(Reader reader, String docTypeName, Class<?> clz, Object obj) {
		return XBlinkHelper.fromAny(IOUtil.createReader(reader), docTypeName, clz, obj);
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
	 * 输入XML信息，返回对应对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @return 对象
	 */
	public static Object fromXml(CharSequence cs) {
		return XBlinkHelper.fromXml(IOUtil.createReader(cs));
	}

	/**
	 * 输入XML信息，根据参考类（参考对象）进行反序列化，返回对应对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param clz
	 *            參考类
	 * @param obj
	 *            參考对象
	 * @return 对象
	 */
	public static Object fromXml(CharSequence cs, Class<?> clz, Object obj) {
		return XBlinkHelper.fromXml(IOUtil.createReader(cs), clz, obj);
	}

	/**
	 * 输入XML信息，返回对应对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @return 对象
	 */
	public static Object fromXml(File file) {
		return XBlinkHelper.fromXml(IOUtil.createReader(file));
	}

	/**
	 * 输入XML信息，根据参考类（参考对象）进行反序列化，返回对应对象。
	 * 
	 * @param file
	 *            需要解析的文件
	 * @param clz
	 *            參考类
	 * @param obj
	 *            參考对象
	 * @return 对象
	 */
	public static Object fromXml(File file, Class<?> clz, Object obj) {
		return XBlinkHelper.fromXml(IOUtil.createReader(file), clz, obj);
	}

	/**
	 * 输入XML信息，返回对应对象。
	 * 
	 * @param in
	 *            输入流
	 * @return 对象
	 */
	public static Object fromXml(InputStream in) {
		return XBlinkHelper.fromXml(IOUtil.createReader(in));
	}

	/**
	 * 输入XML信息，根据参考类（参考对象）进行反序列化，返回对应对象。
	 * 
	 * @param in
	 *            输入流
	 * @param clz
	 *            參考类
	 * @param obj
	 *            參考对象
	 * @return 对象
	 */
	public static Object fromXml(InputStream in, Class<?> clz, Object obj) {
		return XBlinkHelper.fromXml(IOUtil.createReader(in), clz, obj);
	}

	/**
	 * 输入XML信息，返回对应对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @return 对象
	 */
	public static Object fromXml(Reader reader) {
		return XBlinkHelper.fromXml(IOUtil.createReader(reader));
	}

	/**
	 * 输入XML信息，根据参考类（参考对象）进行反序列化，返回对应对象。
	 * 
	 * @param reader
	 *            字符输入流
	 * @param clz
	 *            參考类
	 * @param obj
	 *            參考对象
	 * @return 对象
	 */
	public static Object fromXml(Reader reader, Class<?> clz, Object obj) {
		return XBlinkHelper.fromXml(IOUtil.createReader(reader), clz, obj);
	}

}

package org.xblink;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
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
	 * toAny意味着你可以通过对象获得你想要的格式的文件<BR>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<BR>
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
	 * toAny意味着你可以通过对象获得你想要的格式的文件<BR>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<BR>
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
		XBlinkHelper.toAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(file), docTypeName);
	}

	/**
	 * toAny意味着你可以通过对象获得你想要的格式的文件<BR>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<BR>
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
	 * toAny意味着你可以通过对象获得你想要的格式的文件<BR>
	 * 
	 * XBlink最NB的序列化方法，生成任意你想要的格式文件。<BR>
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

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
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
		return XBlinkHelper.fromAny(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(cs),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
	 * 
	 * @param cs
	 *            字符信息
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(CharSequence cs, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(cs),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
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
		return XBlinkHelper.fromAny(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(file),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
	 * 
	 * @param file
	 *            待解析的文件
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(File file, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(file),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
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
		return XBlinkHelper.fromAny(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(in),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
	 * 
	 * @param in
	 *            输入流
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(InputStream in, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(in),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
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
		return XBlinkHelper.fromAny(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(reader),
				docTypeName);
	}

	/**
	 * fromAny意味着可以从任意格式的文件中得到你想要的那个对象<BR>
	 * 
	 * XBlink最NB的反序列方法，生成你需要的对象。<BR>
	 * 
	 * @param reader
	 *            字符输入流
	 * @param clz
	 *            參考类
	 * @param docTypeName
	 *            任意你想要的文档格式名称，例如JSON或者XML
	 * @return 与参考对象类型相同的对象
	 */
	public static Object fromAny(Reader reader, Object obj, String docTypeName) {
		return XBlinkHelper.fromAny(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(reader),
				docTypeName);
	}

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
	 * @param file
	 *            要保存的文件
	 */
	public static void toXML(Object obj, File file) {
		XBlinkHelper.toXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocWriter(file));
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

	/**
	 * 输入XML信息，根据参考类进行反序列化，返回对应对象。
	 * 
	 * @param cs
	 *            字符信息
	 * @param clz
	 *            參考类
	 * @return 参考类的实例化对象
	 */
	public static <T> T fromXML(CharSequence cs, Class<T> clz) {
		return XBlinkHelper.fromXML(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(cs));
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
	public static Object fromXML(CharSequence cs, Object obj) {
		return XBlinkHelper.fromXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(cs));
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
	public static <T> T fromXML(File file, Class<T> clz) {
		return XBlinkHelper.fromXML(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(file));
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
	public static Object fromXML(File file, Object obj) {
		return XBlinkHelper.fromXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(file));
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
	public static <T> T fromXML(InputStream in, Class<T> clz) {
		return XBlinkHelper.fromXML(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(in));
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
	public static Object fromXML(InputStream in, Object obj) {
		return XBlinkHelper.fromXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(in));
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
	public static <T> T fromXML(Reader reader, Class<T> clz) {
		return XBlinkHelper.fromXML(clz, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(reader));
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
	public static Object fromXML(Reader reader, Object obj) {
		return XBlinkHelper.fromXML(obj, XBConfigHelper.getXbConfig(), DocWorkerFactory.createDocReader(reader));
	}

}

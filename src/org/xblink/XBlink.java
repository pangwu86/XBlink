package org.xblink;

import java.io.InputStream;
import java.io.OutputStream;

import org.xblink.domdrivers.DomDriver;
import org.xblink.reader.XMLReader;
import org.xblink.writer.XMLWriter;

/**
 * XBlink(吾爱跳刀),XML序列化反序列化工具集.<BR/>
 * 支持基本类型，对象类型，数组类型，List，Set，Map等集合类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 */
public class XBlink {

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param obj
	 *            实例对象
	 */
	public static void toXml(String filePath, Object obj) {
		new XMLWriter().writeXML(filePath, obj, null);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param obj
	 *            实例对象
	 * @param domDriver
	 *            DOM驱动
	 */
	public static void toXml(String filePath, Object obj, DomDriver domDriver) {
		new XMLWriter().writeXML(filePath, obj, domDriver);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param obj
	 *            实例对象
	 * @param formatXml
	 *            是否采用缩进格式
	 * @param encoding
	 *            文件编码
	 */
	public static void toXml(String filePath, Object obj, boolean formatXml, String encoding) {
		new XMLWriter().writeXML(filePath, obj, formatXml, encoding, null);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param obj
	 *            实例对象
	 * @param formatXml
	 *            是否采用缩进格式
	 * @param encoding
	 *            文件编码
	 * @param domDriver
	 *            DOM驱动
	 */
	public static void toXml(String filePath, Object obj, boolean formatXml, String encoding,
			DomDriver domDriver) {
		new XMLWriter().writeXML(filePath, obj, formatXml, encoding, domDriver);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param outputStream
	 *            包含文件信息的输出流
	 * @param obj
	 *            实例对象
	 */
	public static void toXml(OutputStream outputStream, Object obj) {
		new XMLWriter().writeXML(outputStream, obj, null);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param outputStream
	 *            包含文件信息的输出流
	 * @param obj
	 *            实例对象
	 * @param domDriver
	 *            DOM驱动
	 */
	public static void toXml(OutputStream outputStream, Object obj, DomDriver domDriver) {
		new XMLWriter().writeXML(outputStream, obj, domDriver);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param outputStream
	 *            包含文件信息的输出流
	 * @param obj
	 *            实例对象
	 * @param formatXml
	 *            是否采用缩进格式
	 * @param encoding
	 *            文件编码
	 */
	public static void toXml(OutputStream outputStream, Object obj, boolean formatXml,
			String encoding) {
		new XMLWriter().writeXML(outputStream, obj, formatXml, encoding, null);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param outputStream
	 *            包含文件信息的输出流
	 * @param obj
	 *            实例对象
	 * @param domDriver
	 *            DOM驱动
	 * @param formatXml
	 *            是否采用缩进格式
	 * @param encoding
	 *            文件编码
	 */
	public static void toXml(OutputStream outputStream, Object obj, boolean formatXml,
			String encoding, DomDriver domDriver) {
		new XMLWriter().writeXML(outputStream, obj, formatXml, encoding, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz) {
		return new XMLReader().readXML(filePath, clz, null, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, DomDriver domDriver) {
		return new XMLReader().readXML(filePath, clz, null, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses) {
		return new XMLReader().readXML(filePath, clz, implClasses, null, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses,
			DomDriver domDriver) {
		return new XMLReader().readXML(filePath, clz, implClasses, null, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz) {
		return new XMLReader().readXML(inputStream, clz, null, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, DomDriver domDriver) {
		return new XMLReader().readXML(inputStream, clz, null, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses) {
		return new XMLReader().readXML(inputStream, clz, implClasses, null, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			DomDriver domDriver) {
		return new XMLReader().readXML(inputStream, clz, implClasses, null, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, ClassLoader classLoader) {
		return new XMLReader().readXML(filePath, clz, classLoader, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param classLoader
	 *            用户的类加载器
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, ClassLoader classLoader,
			DomDriver domDriver) {
		return new XMLReader().readXML(filePath, clz, classLoader, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) {
		return new XMLReader().readXML(filePath, clz, implClasses, classLoader, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader, DomDriver domDriver) {
		return new XMLReader().readXML(filePath, clz, implClasses, classLoader, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, ClassLoader classLoader) {
		return new XMLReader().readXML(inputStream, clz, classLoader, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param classLoader
	 *            用户的类加载器
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, ClassLoader classLoader,
			DomDriver domDriver) {
		return new XMLReader().readXML(inputStream, clz, classLoader, domDriver);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) {
		return new XMLReader().readXML(inputStream, clz, implClasses, classLoader, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @param domDriver
	 *            DOM驱动
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader, DomDriver domDriver) {
		return new XMLReader().readXML(inputStream, clz, implClasses, classLoader, domDriver);
	}

	/**
	 * 清空当前环境中的缓存.
	 */
	public static void cleanCache() {
		XMLObject.cleanCache();
	}
}

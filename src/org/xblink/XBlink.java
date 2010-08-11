package org.xblink;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xblink.reader.XMLReader;
import org.xblink.writer.XMLWriter;

/**
 * XBlink(吾爱跳刀),XML序列化反序列化工具集.<BR/>
 * 支持基本类型，对象类型，数组类型，List，Set，Map三种集合类型.
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
	 * @throws FileNotFoundException
	 */
	static public void toXml(String filePath, Object obj) throws FileNotFoundException {
		new XMLWriter().writeXML(filePath, obj);
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
	 * @throws FileNotFoundException
	 */
	static public void toXml(String filePath, Object obj, boolean formatXml, String encoding)
			throws FileNotFoundException {
		new XMLWriter().writeXML(filePath, obj, formatXml, encoding);
	}

	/**
	 * 序列化一个对象，生成XML文件.
	 * 
	 * @param outputStream
	 *            包含文件信息的输出流
	 * @param obj
	 *            实例对象
	 * @throws FileNotFoundException
	 */
	static public void toXml(OutputStream outputStream, Object obj) throws FileNotFoundException {
		new XMLWriter().writeXML(outputStream, obj);
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
	 * @throws FileNotFoundException
	 */
	static public void toXml(OutputStream outputStream, Object obj, boolean formatXml,
			String encoding) throws FileNotFoundException {
		new XMLWriter().writeXML(outputStream, obj, formatXml, encoding);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clz
	 *            生成对象的Class
	 * @return 对象
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(String filePath, Class<?> clz) throws FileNotFoundException {
		return new XMLReader().readXML(filePath, clz, null);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses)
			throws FileNotFoundException {
		return new XMLReader().readXML(filePath, clz, implClasses, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            生成对象的Class
	 * @return 对象
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(InputStream inputStream, Class<?> clz)
			throws FileNotFoundException {
		return new XMLReader().readXML(inputStream, clz, null);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses)
			throws FileNotFoundException {
		return new XMLReader().readXML(inputStream, clz, implClasses, null);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(String filePath, Class<?> clz, ClassLoader classLoader)
			throws FileNotFoundException {
		return new XMLReader().readXML(filePath, clz, classLoader);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(String filePath, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) throws FileNotFoundException {
		return new XMLReader().readXML(filePath, clz, implClasses, classLoader);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(InputStream inputStream, Class<?> clz, ClassLoader classLoader)
			throws FileNotFoundException {
		return new XMLReader().readXML(inputStream, clz, classLoader);
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
	 * @throws FileNotFoundException
	 */
	static public Object fromXml(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) throws FileNotFoundException {
		return new XMLReader().readXML(inputStream, clz, implClasses, classLoader);
	}
}

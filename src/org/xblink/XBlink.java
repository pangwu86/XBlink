package org.xblink;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		new XMLWriter().writeXML(getOutputStream(filePath), obj);
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
		new XMLWriter().writeXML(outputStream, obj);
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
		return new XMLReader().readXML(getInputStream(filePath), clz, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?>[] clzs) {
		return new XMLReader().readXML(getInputStream(filePath), clzs, null);
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
		return new XMLReader().readXML(getInputStream(filePath), clz, implClasses, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	* @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?>[] clzs, Class<?>[] implClasses) {
		return new XMLReader().readXML(getInputStream(filePath), clzs, implClasses, null);
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
		return new XMLReader().readXML(inputStream, clz, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?>[] clzs) {
		return new XMLReader().readXML(inputStream, clzs, null);
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
		return new XMLReader().readXML(inputStream, clz, implClasses, null);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?>[] clzs, Class<?>[] implClasses) {
		return new XMLReader().readXML(inputStream, clzs, implClasses, null);
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
		return new XMLReader().readXML(getInputStream(filePath), clz, classLoader);
	}

	/**
	 * 
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?>[] clzs, ClassLoader classLoader) {
		return new XMLReader().readXML(getInputStream(filePath), clzs, classLoader);
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
		return new XMLReader().readXML(getInputStream(filePath), clz, implClasses, classLoader);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(String filePath, Class<?>[] clzs, Class<?>[] implClasses,
			ClassLoader classLoader) {
		return new XMLReader().readXML(getInputStream(filePath), clzs, implClasses, classLoader);
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
		return new XMLReader().readXML(inputStream, clz, classLoader);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	* @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?>[] clzs, ClassLoader classLoader) {
		return new XMLReader().readXML(inputStream, clzs, classLoader);
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
		return new XMLReader().readXML(inputStream, clz, implClasses, classLoader);
	}

	/**
	 * 反序列化一个对象，源自XML文件.
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clzs
	 *            生成对象的Class(Map集合使用，第一个参数是Key的类型，第二个参数是Value的类型)
	 * @param implClasses
	 *            生成对象中包含的所有接口实现类
	 * @param classLoader
	 *            用户的类加载器
	 * @return 对象
	 */
	public static Object fromXml(InputStream inputStream, Class<?>[] clzs, Class<?>[] implClasses,
			ClassLoader classLoader) {
		return new XMLReader().readXML(inputStream, clzs, implClasses, classLoader);
	}

	/**
	 * 清空当前环境中的缓存.
	 */
	public static void cleanCache() {
		XMLObject.cleanCache();
	}

	/**
	 * 获得输入流.
	 * 
	 * @param filePath
	 * @return
	 */
	private static InputStream getInputStream(String filePath) {
		try {
			return new BufferedInputStream(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("没有找到对应的文件", e);
		}
	}

	/**
	 * 获得输入流.
	 * 
	 * @param filePath
	 * @return
	 */
	private static OutputStream getOutputStream(String filePath) {
		try {
			return new BufferedOutputStream(new FileOutputStream(getFile(filePath)));
		} catch (Exception e) {
			throw new RuntimeException("没有找到对应的文件", e);
		}
	}

	/**
	 * 获得文件对象.
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static File getFile(String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
}

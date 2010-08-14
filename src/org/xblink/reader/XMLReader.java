package org.xblink.reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xblink.ClassLoaderSwitcher;
import org.xblink.Constants;
import org.xblink.ImplClasses;
import org.xblink.XMLObject;
import org.xblink.XRoot;

/**
 * XML反序列化工具类.
 */
public class XMLReader {

	/** 实现类集合 */
	private ImplClasses xmlImplClasses = new ImplClasses();

	/**
	 * 
	 * @param filePath
	 *            输出文件位置信息
	 * @param clz
	 *            Java类对象
	 * @throws FileNotFoundException
	 */

	public Object readXML(String filePath, Class<?> clz, ClassLoader classLoader)
			throws FileNotFoundException {
		return readXML(filePath, clz, new Class<?>[] {}, classLoader);
	}

	/**
	 * 
	 * @param filePath
	 *            输出文件位置信息
	 * @param clz
	 *            Java类对象
	 * @param implClasses
	 *            接口实现类
	 * @throws FileNotFoundException
	 */

	public Object readXML(String filePath, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) throws FileNotFoundException {
		try {
			return reading(new BufferedInputStream(new FileInputStream(new File(filePath))), clz,
					implClasses, classLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object();
	}

	/**
	 * 
	 * @param inputStream
	 *            包含文件信息的输 入流
	 * @param clz
	 *            Java类对象
	 * @throws FileNotFoundException
	 */

	public Object readXML(InputStream inputStream, Class<?> clz, ClassLoader classLoader) {
		return readXML(inputStream, clz, new Class<?>[] {}, classLoader);
	}

	/**
	 * 
	 @param inputStream
	 *            包含文件信息的输入流
	 * @param clz
	 *            Java类对象
	 * @param implClasses
	 *            接口实现类
	 */

	public Object readXML(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) {
		try {
			return reading(inputStream, clz, implClasses, classLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object();
	}

	/**
	 * 
	 * @param in
	 *            输入流 包含输出文件位置信息
	 * @param clz
	 *            Java类对象
	 * @param implClasses
	 *            接口实现类
	 * @throws Exception
	 */

	private Object reading(InputStream in, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) throws Exception {
		// 接口实现类集合初始化
		xmlImplClasses.setNewInstanceClass(clz);
		xmlImplClasses.setImplClasses(implClasses);
		xmlImplClasses.setImplClassesLength(implClasses.length);
		xmlImplClasses.setImplClassesMap(new HashMap<Class<?>, Class<?>>());
		// 类加载器切换器初始化
		ClassLoaderSwitcher classLoaderSwitcher = new ClassLoaderSwitcher();
		classLoaderSwitcher.setUserClassLoader(classLoader);
		// XML类对象反序列工具类初始化
		XMLObjectReader xmlObjectRead = new XMLObjectReader();
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
			Node baseNode = document.getFirstChild();
			String root = baseNode.getNodeName();
			if (root.equals(Constants.ROOT)) {
				Node rootCollectionNode = baseNode.getFirstChild().getNextSibling();
				String rootCollectionNodeName = rootCollectionNode.getNodeName();
				XRoot xmlRoot = new XRoot();
				// 判断是否是集合类型，是的话放入root对象中再进行序列化
				if (rootCollectionNodeName.endsWith(Constants.ARRAY)) {
					Object[] result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode,
							xmlImplClasses, classLoaderSwitcher)).getArray();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.LIST)) {
					List<?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, xmlImplClasses,
							classLoaderSwitcher)).getList();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.SET)) {
					Set<?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, xmlImplClasses,
							classLoaderSwitcher)).getSet();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.MAP)) {
					Map<?, ?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode,
							xmlImplClasses, classLoaderSwitcher)).getMap();
					return result;
				}
			}
			Object result = xmlObjectRead.read(getInstance(clz), baseNode, xmlImplClasses,
					classLoaderSwitcher);
			// 去掉XRoot的信息
			XMLObject.cleanXRoot();
			return result;
		} finally {
			if (null != in) {
				in.close();
			}
		}
	}

	/**
	 * 根据Class获得对象实例.
	 * 
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	protected Object getInstance(Class<?> clz) throws Exception {
		if (!clz.isInterface()) {
			return getSimpleInstance(clz);
		}
		Class<?> clzImpl = xmlImplClasses.getImplClassesMap().get(clz);
		if (null == clzImpl) {
			for (int i = 0; i < xmlImplClasses.getImplClassesLength(); i++) {
				Class<?>[] implClasses = xmlImplClasses.getImplClasses();
				if (clz.isAssignableFrom(implClasses[i])) {
					xmlImplClasses.getImplClassesMap().put(clz, implClasses[i]);
					clzImpl = implClasses[i];
					break;
				}
			}
		}
		if (null == clzImpl) {
			throw new Exception("没有找到 " + clz.getName() + " 的实现类，无法进行实例化.");
		}
		return getSimpleInstance(clzImpl);
	}

	/**
	 * 根据Class获得对象实例.
	 * 
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	protected Object getSimpleInstance(Class<?> clz) throws Exception {
		try {
			return clz.newInstance();
		} catch (Exception e) {
			throw new Exception(clz.getName() + " 没有不带参数的构造函数，无法进行实例化.");
		}
	}
}

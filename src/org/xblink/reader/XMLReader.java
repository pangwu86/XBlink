package org.xblink.reader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.Constants;
import org.xblink.XMLObject;
import org.xblink.XRoot;
import org.xblink.adapter.XMLAdapter;
import org.xblink.adapter.XMLAdapterFactory;
import org.xblink.domdrivers.impl.SystemDomDriver;
import org.xblink.transfer.ClassLoaderSwitcher;
import org.xblink.transfer.ImplClasses;
import org.xblink.transfer.ReferenceObject;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassUtil;
import org.xblink.xml.XMLDocument;
import org.xblink.xml.XMLNode;

/**
 * 
 * XML反序列化工具类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLReader {

	/**
	 * 
	 * @param inputStream
	 *            输入流
	 * @param clz
	 *            Java类对象
	 * @param classLoader
	 *            类加载器 return 对象
	 */
	public Object readXML(InputStream inputStream, Class<?> clz, ClassLoader classLoader) {
		return readXML(inputStream, clz, new Class<?>[] {}, classLoader);
	}

	/**
	 * 
	 * @param inputStream
	 *            输入流
	 * @param clz
	 *            Java类对象
	 * @param implClasses
	 *            接口实现类
	 * @param classLoader
	 *            类加载器
	 * 
	 * @return 对象
	 */
	public Object readXML(InputStream inputStream, Class<?> clz, Class<?>[] implClasses,
			ClassLoader classLoader) {
		try {
			return readStart(inputStream, new Class<?>[] { clz, null }, implClasses, classLoader);
		} catch (Exception e) {
			throw new RuntimeException(Constants.DESERIALIZE_FAIL, e);
		}
	}

	/**
	 * 
	 * @param inputStream
	 *            输入流
	 * @param clzs
	 *            Java类对象(两个)
	 * @param implClasses
	 *            接口实现类
	 * @param classLoader
	 *            类加载器
	 * @return 对象
	 */
	public Object readXML(InputStream inputStream, Class<?>[] clzs, ClassLoader classLoader) {
		return readXML(inputStream, clzs, new Class<?>[] {}, classLoader);
	}

	/**
	 * 
	 * @param inputStream
	 *            输入流
	 * @param clzs
	 *            Java类对象(两个)
	 * @param implClasses
	 *            接口实现类
	 * @param classLoader
	 *            类加载器
	 * @return 对象
	 */
	public Object readXML(InputStream inputStream, Class<?>[] clzs, Class<?>[] implClasses,
			ClassLoader classLoader) {
		try {
			return readStart(inputStream, clzs, implClasses, classLoader);
		} catch (Exception e) {
			throw new RuntimeException(Constants.DESERIALIZE_FAIL, e);
		}
	}

	/**
	 * 开始解析.
	 * 
	 * @param in
	 *            输入流 包含输出文件位置信息
	 * @param clzs
	 *            Java类对象(两个对象)
	 * @param implClasses
	 *            接口实现类
	 * @throws Exception
	 * 
	 * @return 对象
	 */
	private Object readStart(InputStream in, Class<?>[] clzs, Class<?>[] implClasses,
			ClassLoader classLoader) throws Exception {
		// 解析过的对象，方便其他对象引用
		Map<Integer, ReferenceObject> referenceObjects = new HashMap<Integer, ReferenceObject>();
		// 接口实现类集合初始化
		ImplClasses xmlImplClasses = new ImplClasses();
		xmlImplClasses.setRootInstanceClass1(clzs[0]);
		xmlImplClasses.setRootInstanceClass2(clzs[1]);
		xmlImplClasses.setImplClasses(implClasses);
		xmlImplClasses.setImplClassesNumber(implClasses.length);
		xmlImplClasses.setImplClassesMap(new HashMap<Class<?>, Class<?>>());
		// 类加载器切换器初始化
		ClassLoaderSwitcher classLoaderSwitcher = new ClassLoaderSwitcher();
		classLoaderSwitcher.setUserClassLoader(classLoader);
		// 根据DOM驱动，获得对应的适配器
		XMLAdapter xmlAdapter = XMLAdapterFactory.getAdapter(new SystemDomDriver());
		// 传递信息对象
		TransferInfo transferInfo = new TransferInfo();
		transferInfo.setReferenceObjects(referenceObjects);
		transferInfo.setXmlImplClasses(xmlImplClasses);
		transferInfo.setClassLoaderSwitcher(classLoaderSwitcher);
		transferInfo.setXmlAdapter(xmlAdapter);
		// XML类对象反序列工具类初始化
		XMLObjectReader xmlObjectRead = new XMLObjectReader();
		try {
			XMLDocument document = xmlAdapter.getDocument(in);
			XMLNode baseNode = document.getFirstChild(xmlAdapter);
			String root = baseNode.getNodeName(xmlAdapter);
			if (root.equals(Constants.ROOT)) {
				XMLNode rootCollectionNode = baseNode.getFirstChild(xmlAdapter).getNextSibling(
						xmlAdapter);
				String rootCollectionNodeName = rootCollectionNode.getNodeName(xmlAdapter);
				XRoot xmlRoot = new XRoot();
				// 判断是否是集合类型，是的话放入root对象中再进行序列化
				if (rootCollectionNodeName.endsWith(Constants.ARRAY)) {
					Object[] result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, transferInfo))
							.getArray();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.LIST)) {
					List<?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, transferInfo))
							.getList();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.SET)) {
					Set<?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, transferInfo))
							.getSet();
					return result;
				} else if (rootCollectionNodeName.endsWith(Constants.MAP)) {
					Map<?, ?> result = ((XRoot) xmlObjectRead.read(xmlRoot, baseNode, transferInfo))
							.getMap();
					return result;
				}
			}
			return xmlObjectRead.read(
					ClassUtil.getInstance(clzs[0], transferInfo.getXmlImplClasses()), baseNode,
					transferInfo);
		} finally {
			// 去掉XRoot的信息
			XMLObject.cleanXRoot();
			// 手动释放对象引用
			transferInfo = null;
			// 关闭相关流
			if (null != in) {
				in.close();
			}
		}
	}
}

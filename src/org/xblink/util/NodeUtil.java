package org.xblink.util;

import java.lang.reflect.Constructor;

import org.xblink.Constants;
import org.xblink.adapter.XMLAdapter;
import org.xblink.transfer.ClassLoaderSwitcher;
import org.xblink.xml.XMLNode;
import org.xblink.xml.XMLNodeList;

/**
 * 节点相关操作工具类.
 * 
 * 
 * @author geor.wupeiwen
 * 
 */
public class NodeUtil {

	/**
	 * 获得基本节点.
	 * 
	 * @param rootNode
	 * @param nodeName
	 * @return 节点
	 */
	public static XMLNode getTarNode(XMLNode rootNode, String nodeName, XMLAdapter xmlAdapter) {
		XMLNodeList nodeList = rootNode.getChildNodes(xmlAdapter);
		for (int j = 0; j < nodeList.getLength(xmlAdapter); j++) {
			if (2 * j + 1 >= nodeList.getLength(xmlAdapter)) {
				break;
			}
			XMLNode baseNode = nodeList.item(xmlAdapter, 2 * j + 1);
			if (baseNode.getNodeName(xmlAdapter).equals(nodeName)) {
				return baseNode;
			}
		}
		return null;
	}

	/**
	 * 根据节点信息获得对象.
	 * 
	 * @param baseNode
	 * @return 对象
	 * @throws Exception
	 */
	public static Object getObject(XMLNode baseNode, ClassLoaderSwitcher classLoaderSwitcher,
			XMLAdapter xmlAdapter) throws Exception {
		Object obj = null;
		Class<?> objClass = classLoaderSwitcher.forName(new String(baseNode.getNodeName(xmlAdapter)
				.replaceAll(Constants.CLASS_AND_PREFIX, Constants.EMPTY_STRING)));
		XMLNode att = baseNode.getAttributes(xmlAdapter).getNamedItem(xmlAdapter,
				Constants.OBJ_VALUE);
		String xPathValue = att == null ? null : att.getNodeValue(xmlAdapter);
		if (null == xPathValue || xPathValue.length() == 0) {
			obj = null;
		} else {
			if (xPathValue.startsWith(Constants.CLASS_PREFIX)) {
				obj = classLoaderSwitcher.forName(new String(xPathValue.replaceAll(
						Constants.CLASS_PREFIX, Constants.EMPTY_STRING)));
			} else {
				Constructor<?> constructor = objClass.getDeclaredConstructor(String.class);
				if (constructor != null) {
					obj = constructor.newInstance(xPathValue);
				}
			}
		}
		return obj;
	}

	/**
	 * 
	 * @param baseNode
	 *            Document中的某个节点
	 * @param xpath
	 *            基本字段的位置信息
	 * @return
	 */
	public static String getAttributeValue(XMLNode baseNode, String xpath, XMLAdapter xmlAdapter) {
		XMLNode att = baseNode.getAttributes(xmlAdapter).getNamedItem(xmlAdapter, xpath);
		return att.getNode() == null ? null : att.getNodeValue(xmlAdapter);
	}
}

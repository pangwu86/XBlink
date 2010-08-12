package org.xblink.util;

import java.lang.reflect.Constructor;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xblink.ClassLoaderSwitcher;
import org.xblink.Constants;

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
	public static Node getTarNode(Node rootNode, String nodeName) {
		NodeList nodeList = rootNode.getChildNodes();
		for (int j = 0; j < nodeList.getLength(); j++) {
			if (2 * j + 1 >= nodeList.getLength()) {
				break;
			}
			Node baseNode = nodeList.item(2 * j + 1);
			if (baseNode.getNodeName().equals(nodeName)) {
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
	public static Object getObject(Node baseNode, ClassLoaderSwitcher classLoaderSwitcher)
			throws Exception {
		Object obj = null;
		Class<?> objClass = classLoaderSwitcher.forName(new String(baseNode.getNodeName()
				.replaceAll(Constants.CLASS_AND_PREFIX, Constants.EMPTY_STRING)));
		Node att = baseNode.getAttributes().getNamedItem(Constants.OBJ_VALUE);
		String xPathValue = att == null ? null : att.getNodeValue();
		if (null == xPathValue || xPathValue.length() == 0) {
			obj = null;
		} else {
			if (xPathValue.startsWith(Constants.CLASS_PREFIX)) {
				obj = classLoaderSwitcher.forName(new String(xPathValue.replaceAll(Constants.CLASS_PREFIX,
				                                                                   Constants.EMPTY_STRING)));
			} else {
				Constructor<?> constructor = objClass.getDeclaredConstructor(String.class);
				if (constructor != null) {
					obj = constructor.newInstance(xPathValue);
				}
			}
		}
		return obj;
	}
}

package org.xblink.xml;

import org.xblink.adapter.XMLAdapter;

/**
 * NodeList对象.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLNodeList {

	/** 实际的节点列表 */
	private Object nodeList;

	public Object getNodeList() {
		return nodeList;
	}

	public void setNodeList(Object nodeList) {
		this.nodeList = nodeList;
	}

	public int getLength(XMLAdapter xmlAdapter) {
		return xmlAdapter.getXMLNodeListLength(nodeList);
	}

	public XMLNode item(XMLAdapter xmlAdapter, int i) {
		return xmlAdapter.getNodeListItem(nodeList, i);
	}

}

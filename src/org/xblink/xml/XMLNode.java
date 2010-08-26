package org.xblink.xml;

import org.xblink.adapter.XMLAdapter;

/**
 * XML节点.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLNode {

	/** 实际的节点对象 */
	private Object node;

	public Object getNode() {
		return node;
	}

	public void setNode(Object node) {
		this.node = node;
	}

	public XMLNodeList getChildNodes(XMLAdapter xmlAdapter) {
		return xmlAdapter.getChildNodes(node);
	}

	public String getNodeName(XMLAdapter xmlAdapter) {
		return xmlAdapter.getNodeName(node);
	}

	public XMLAttributeMap getAttributes(XMLAdapter xmlAdapter) {
		return xmlAdapter.getAttributes(node);
	}

	public String getElementValue(XMLAdapter xmlAdapter, String elementName) {
		return xmlAdapter.getElementValue(node, elementName);
	}

	public String getNodeValue(XMLAdapter xmlAdapter) {
		return xmlAdapter.getNodeValue(node);
	}

	public XMLNode getFirstChild(XMLAdapter xmlAdapter) {
		return xmlAdapter.getFirstChild(node);
	}

	public XMLNode getNextSibling(XMLAdapter xmlAdapter) {
		return xmlAdapter.getNextSibling(node);
	}

}

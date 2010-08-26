package org.xblink.xml;

import org.xblink.adapter.XMLAdapter;

/**
 * Document对象.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLDocument {

	/** 实际的Document对象 */
	private Object document;

	public Object getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = document;
	}

	/**
	 * 获得第一个孩子节点
	 * 
	 * @param xmlAdapter
	 * @return
	 */
	public XMLNode getFirstChild(XMLAdapter xmlAdapter) {
		return xmlAdapter.getFirstChild(document);
	}

}

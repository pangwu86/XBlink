package org.xblink.xml;

import org.xblink.adapter.XMLAdapter;

/**
 * AttributeMap对象.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLAttributeMap {

	/** 实际的attrbuteMap */
	private Object attributeMap;

	public Object getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Object attributeMap) {
		this.attributeMap = attributeMap;
	}

	public XMLNode getNamedItem(XMLAdapter xmlAdapter, String objValue) {
		return xmlAdapter.getNamedItem(attributeMap, objValue);
	}
}

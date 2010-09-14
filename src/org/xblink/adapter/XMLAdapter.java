package org.xblink.adapter;

import java.io.InputStream;

import org.xblink.xml.XMLAttributeMap;
import org.xblink.xml.XMLDocument;
import org.xblink.xml.XMLNode;
import org.xblink.xml.XMLNodeList;

/**
 * XML解析器接口.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public interface XMLAdapter {

	public XMLDocument getDocument(InputStream in);

	public XMLNode getFirstChild(Object document);

	public String getNodeName(Object node);

	public XMLNodeList getChildNodes(Object node);

	public String getNodeValue(Object node);

	public String getElementValue(Object node, String elementName);

	public XMLAttributeMap getAttributes(Object node);

	public XMLNode getNextSibling(Object node);

	public int getXMLNodeListLength(Object nodeList);

	public XMLNode getNodeListItem(Object nodeList, int i);

	public XMLNode getNamedItem(Object attributeMap, String objValue);

	public String getTextContent(Object node);

}

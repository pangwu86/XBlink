package org.xblink.adapter.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xblink.adapter.XMLAdapter;
import org.xblink.xml.XMLAttributeMap;
import org.xblink.xml.XMLDocument;
import org.xblink.xml.XMLNode;
import org.xblink.xml.XMLNodeList;
import org.xml.sax.SAXException;

/**
 * XML解析器-JDK实现.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class SystemXMLAdapter implements XMLAdapter {

	public XMLDocument getDocument(InputStream in) {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		XMLDocument xmlDocument = new XMLDocument();
		xmlDocument.setDocument(document);
		return xmlDocument;
	}

	public XMLNode getFirstChild(Object document) {
		Node node = (Node) document;
		Node firstChild = node.getFirstChild();
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNode(firstChild);
		return xmlNode;
	}

	public String getNodeName(Object node) {
		return ((Node) node).getNodeName();
	}

	public XMLNodeList getChildNodes(Object node) {
		Node inode = (Node) node;
		NodeList nodeList = inode.getChildNodes();
		XMLNodeList xmlNodeList = new XMLNodeList();
		xmlNodeList.setNodeList(nodeList);
		return xmlNodeList;
	}

	public String getNodeValue(Object node) {
		return ((Node) node).getNodeValue();
	}

	public String getElementValue(Object node, String elementName) {
		Node inode = null;
		try {
			inode = com.sun.org.apache.xpath.internal.XPathAPI.selectSingleNode((Node) node,
					elementName);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return inode == null ? null : inode.getTextContent();
	}

	public XMLAttributeMap getAttributes(Object node) {
		Node inode = (Node) node;
		NamedNodeMap namedNodeMap = inode.getAttributes();
		XMLAttributeMap xmlAttributeMap = new XMLAttributeMap();
		xmlAttributeMap.setAttributeMap(namedNodeMap);
		return xmlAttributeMap;
	}

	public XMLNode getNextSibling(Object node) {
		Node nextSibling = ((Node) node).getNextSibling();
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNode(nextSibling);
		return xmlNode;
	}

	public int getXMLNodeListLength(Object nodeList) {
		NodeList inodeList = (NodeList) nodeList;
		return inodeList.getLength();
	}

	public XMLNode getNodeListItem(Object nodeList, int i) {
		NodeList inodeList = (NodeList) nodeList;
		Node item = inodeList.item(i);
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNode(item);
		return xmlNode;
	}

	public XMLNode getNamedItem(Object attributeMap, String objValue) {
		NamedNodeMap namedNodeMap = (NamedNodeMap) attributeMap;
		Node item = namedNodeMap.getNamedItem(objValue);
		XMLNode xmlNode = new XMLNode();
		xmlNode.setNode(item);
		return xmlNode;
	}

}

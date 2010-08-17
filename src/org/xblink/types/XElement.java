package org.xblink.types;

import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Node;
import org.xblink.ReferenceObject;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAsElement;
import org.xblink.util.ClassUtil;
import org.xblink.writer.XMLWriterUtil;

/**
 * 字段类型元素.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XElement extends XType {

	public boolean getAnnotation(Field field) {
		XBlinkAsElement xTextElement = field.getAnnotation(XBlinkAsElement.class);
		if (null != xTextElement) {
			fieldTypes.add(field);
			return true;
		}
		return false;
	}

	public void writeItem(Object obj, XMLWriterUtil writer,
			Map<Integer, ReferenceObject> referenceObjects) throws Exception {
		for (Field field : fieldTypes) {
			if (isFieldEmpty(field, obj)) {
				continue;
			}
			writer.writeTextElement(ClassUtil.getFieldName(field).toString(), field.get(obj)
					.toString());
		}
	}

	public void readItem(Object obj, Node baseNode, Map<Integer, ReferenceObject> referenceObjects)
			throws Exception {
		for (Field field : fieldTypes) {
			String xPathValue = getTextElementValue(baseNode, ClassUtil.getFieldName(field)
					.toString());
			if (null == xPathValue || xPathValue.length() == 0) {
				field.set(obj, null);
			} else {
				ClassUtil.fieldSet(field, obj, xPathValue, getClassLoaderSwitcher());
			}
		}
	}

	/**
	 * 
	 * @param baseNode
	 *            Document中的某个节点
	 * @param xpath
	 *            基本字段的位置信息
	 * @return
	 */
	private String getTextElementValue(Node baseNode, String xpath) throws Exception {
		Node att = com.sun.org.apache.xpath.internal.XPathAPI.selectSingleNode(baseNode, xpath);
		return att == null ? null : att.getTextContent();
	}

}

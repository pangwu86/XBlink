package org.xblink.types;

import java.lang.reflect.Field;

import org.xblink.XType;
import org.xblink.annotations.XBlinkAsElement;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassUtil;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;

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

	public void writeItem(Object obj, XMLWriterHelper writer, TransferInfo transferInfo)
			throws Exception {
		for (Field field : fieldTypes) {
			if (isFieldEmpty(field, obj)) {
				continue;
			}
			writer.writeTextElement(ClassUtil.getFieldName(field).toString(), field.get(obj)
					.toString());
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			String xPathValue = baseNode.getElementValue(transferInfo.getXmlAdapter(), ClassUtil
					.getFieldName(field).toString());
			if (null == xPathValue || xPathValue.length() == 0) {
				field.set(obj, null);
			} else {
				ClassUtil.fieldSet(field, obj, xPathValue, transferInfo.getClassLoaderSwitcher());
			}
		}
	}
}

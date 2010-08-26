package org.xblink.types;

import java.lang.reflect.Field;

import org.xblink.XType;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;

/**
 * 字段类型属性.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XAttribute extends XType {

	public boolean getAnnotation(Field field) {
		XBlinkAsAttribute xAttribute = field.getAnnotation(XBlinkAsAttribute.class);
		if (null != xAttribute) {
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
			writer.writeAttribute(ClassUtil.getFieldName(field).toString(), field.get(obj)
					.toString());
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			String xPathValue = NodeUtil.getAttributeValue(baseNode, ClassUtil.getFieldName(field)
					.toString(), transferInfo.getXmlAdapter());
			if (null == xPathValue || xPathValue.length() == 0) {
				field.set(obj, null);
			} else {
				ClassUtil.fieldSet(field, obj, xPathValue, transferInfo.getClassLoaderSwitcher());
			}
		}
	}

}

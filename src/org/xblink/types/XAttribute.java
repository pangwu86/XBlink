package org.xblink.types;

import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Node;
import org.xblink.ReferenceObject;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLWriterUtil;

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

	public void writeItem(Object obj, XMLWriterUtil writer,
			Map<Integer, ReferenceObject> referenceObjects) throws Exception {
		for (Field field : fieldTypes) {
			if (isFieldEmpty(field, obj)) {
				continue;
			}
			writer.writeAttribute(ClassUtil.getFieldName(field).toString(), field.get(obj)
					.toString());
		}
	}

	public void readItem(Object obj, Node baseNode, Map<Integer, ReferenceObject> referenceObjects)
			throws Exception {
		for (Field field : fieldTypes) {
			String xPathValue = NodeUtil.getAttributeValue(baseNode, ClassUtil.getFieldName(field)
					.toString());
			if (null == xPathValue || xPathValue.length() == 0) {
				field.set(obj, null);
			} else {
				ClassUtil.fieldSet(field, obj, xPathValue, getClassLoaderSwitcher());
			}
		}
	}

}

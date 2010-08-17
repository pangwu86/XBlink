package org.xblink.types;

import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Node;
import org.xblink.ReferenceObject;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAsObject;
import org.xblink.reader.XMLObjectReader;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLObjectWriter;
import org.xblink.writer.XMLWriterUtil;

/**
 * 对象类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XObject extends XType {

	public boolean getAnnotation(Field field) {
		XBlinkAsObject xObject = field.getAnnotation(XBlinkAsObject.class);
		if (null != xObject) {
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
			new XMLObjectWriter().write(field.get(obj), writer, field.getName(), referenceObjects);
		}
	}

	public void readItem(Object obj, Node baseNode, Map<Integer, ReferenceObject> referenceObjects)
			throws Exception {
		for (Field field : fieldTypes) {
			Node tarNode = NodeUtil.getTarNode(baseNode, field.getName());
			if (null == tarNode) {
				continue;
			}
			Object value = new XMLObjectReader().read(
					ClassUtil.getInstance(field.getType(), getImplClass()), tarNode,
					getImplClass(), getClassLoaderSwitcher(), referenceObjects);
			field.set(obj, value);
		}
	}

}

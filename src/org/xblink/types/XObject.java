package org.xblink.types;

import java.lang.reflect.Field;

import org.xblink.XType;
import org.xblink.annotations.XBlinkAsObject;
import org.xblink.reader.XMLObjectReader;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLObjectWriter;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;

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

	public void writeItem(Object obj, XMLWriterHelper writer, TransferInfo transferInfo)
			throws Exception {
		for (Field field : fieldTypes) {
			if (isFieldEmpty(field, obj)) {
				continue;
			}
			new XMLObjectWriter().write(field.get(obj), writer, field.getName(), transferInfo);
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			XMLNode tarNode = NodeUtil.getTarNode(baseNode, field.getName(),
					transferInfo.getXmlAdapter());
			if (null == tarNode) {
				continue;
			}
			Object value = new XMLObjectReader().read(
					ClassUtil.getInstance(field.getType(), transferInfo.getXmlImplClasses()),
					tarNode, transferInfo);
			field.set(obj, value);
		}
	}

}

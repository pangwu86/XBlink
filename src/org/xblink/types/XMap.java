package org.xblink.types;

import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Node;

import org.xblink.ReferenceObject;
import org.xblink.XType;
import org.xblink.writer.XMLWriterUtil;

/**
 * Map集合类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMap extends XType {

	public boolean getAnnotation(Field field) {
		return false;
	}

	public void writeItem(Object obj, XMLWriterUtil writer,
			Map<Integer, ReferenceObject> referenceObjects) throws Exception {
		throw new Exception("未实现的功能.");
	}

	public void readItem(Object obj, Node baseNode, Map<Integer, ReferenceObject> referenceObjects)
			throws Exception {
		throw new Exception("未实现的功能.");
	}
}

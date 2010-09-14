package org.xblink.types;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xblink.Constants;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsMap;
import org.xblink.reader.XMLObjectReader;
import org.xblink.transfer.ReferenceObject;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassType;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLObjectWriter;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;
import org.xblink.xml.XMLNodeList;

/**
 * Map集合类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMap extends XType {

	private XMLNodeList nodeList;
	private int nodeListLength;

	public boolean getAnnotation(Field field) {
		XBlinkAsMap xMap = field.getAnnotation(XBlinkAsMap.class);
		if (null != xMap) {
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
			// 判断是Map吗
			if (!Map.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个Map.");
			}
			Map<Object, Object> objMap = (Map) field.get(obj);
			// 列表为空的话
			if (objMap.size() == 0) {
				continue;
			}
			// 判断是否需要起别名,添加前缀
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			// 前缀
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.MAP);
			}

			// 集合对象的判断
			Map<Integer, ReferenceObject> referenceObjects = transferInfo.getReferenceObjects();
			// 记录解析过的Object
			ReferenceObject ref = referenceObjects.get(objMap.hashCode());
			// 引用对象，特殊处理
			if (null != ref) {
				String startElement = fieldName.toString();
				writer.writeStartElement(startElement);
				// 调用toString
				writer.writeAttribute(Constants.OBJ_REFERENCE, String.valueOf(ref.getNo()));
				writer.writeEndElement();
				return;
			}
			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(objMap);
			referenceObjects.put(objMap.hashCode(), refObject);

			writer.writeStartElement(fieldName.toString());
			// Map内容
			Set<Object> keys = objMap.keySet();
			for (Object key : keys) {
				// entry start
				writer.writeStartElement(Constants.MAP_ENTRY);
				// key
				new XMLObjectWriter().write(key, writer, null, transferInfo);
				// value
				new XMLObjectWriter().write(objMap.get(key), writer, null, transferInfo);
				// entry end
				writer.writeEndElement();
			}
			// 后缀
			writer.writeEndElement();
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			// 判断是列表吗
			if (!Map.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个Map.");
			}
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.MAP);
			}
			XMLNode tarNode = NodeUtil.getTarNode(baseNode, fieldName.toString(),
					transferInfo.getXmlAdapter());
			if (null == tarNode) {
				continue;
			}
			Map<Integer, ReferenceObject> referenceObjects = transferInfo.getReferenceObjects();
			// 是否是引用对象
			String refNo = NodeUtil.getAttributeValue(tarNode, Constants.OBJ_REFERENCE,
					transferInfo.getXmlAdapter());
			if (null != refNo) {
				ReferenceObject refObject = referenceObjects.get(Integer.valueOf(refNo));
				Object ref = refObject.getRef();
				field.set(obj, ref);
				return;
			}

			// 获得泛型参数
			ClassType classType = ClassUtil.getClassTypes(field, transferInfo.getXmlImplClasses());
			// 获得Map对象
			Map result = traceXPathMap(tarNode, classType.getFieldClass(), transferInfo);
			field.set(obj, result);

			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(result);
			referenceObjects.put(refObject.getNo(), refObject);

			// Map对象塞入对应的值
			setValue(result, classType.getFieldClass(), classType.getFieldInnerClass1(),
					classType.getFieldInnerClassType1(), classType.getFieldInnerClass2(),
					classType.getFieldInnerClassType2(), transferInfo, nodeListLength, nodeList);
		}
	}

	private Map traceXPathMap(XMLNode baseNode, Class<?> fieldClass, TransferInfo transferInfo)
			throws Exception {
		boolean isInterface = fieldClass.isInterface() ? true : false;
		XMLNodeList nodeList = baseNode.getChildNodes(transferInfo.getXmlAdapter());
		int nodeListLength = nodeList.getLength(transferInfo.getXmlAdapter());
		if (nodeList == null || nodeListLength == 0) {
			if (isInterface) {
				return new HashMap(0);
			}
			return (Map) fieldClass.getConstructor(int.class).newInstance(0);
		}
		nodeListLength = (nodeListLength - 1) / 2;
		Map result;
		if (isInterface) {
			result = new HashMap();
		} else {
			result = (Map) fieldClass.newInstance();
		}
		// 记录两个参数
		this.nodeList = nodeList;
		this.nodeListLength = nodeListLength;
		return result;
	}

	private void setValue(Map result, Class<?> fieldClass, Class<?> fieldInnerClass1,
			Type fieldInnerClassType1, Class<?> fieldInnerClass2, Type fieldInnerClassType2,
			TransferInfo transferInfo, int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		// 塞入值
		for (int idx = 0; idx < nodeListLength; idx++) {
			Object key = null;
			Object value = null;
			XMLNode keyNode = nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1)
					.getFirstChild(transferInfo.getXmlAdapter())
					.getNextSibling(transferInfo.getXmlAdapter());
			XMLNode valueNode = keyNode.getNextSibling(transferInfo.getXmlAdapter())
					.getNextSibling(transferInfo.getXmlAdapter());
			// key的重构
			if (fieldInnerClass1 == null || fieldInnerClassType1 == null) {
				key = NodeUtil.getObject(keyNode, transferInfo.getClassLoaderSwitcher(),
						transferInfo.getXmlAdapter());
			} else {
				key = new XMLObjectReader().read(
						ClassUtil.getInstance(fieldInnerClass1, transferInfo.getXmlImplClasses()),
						keyNode, transferInfo);
			}
			// value的重构
			if (fieldInnerClass2 == null || fieldInnerClassType2 == null) {
				value = NodeUtil.getObject(valueNode, transferInfo.getClassLoaderSwitcher(),
						transferInfo.getXmlAdapter());
			} else {
				value = new XMLObjectReader().read(
						ClassUtil.getInstance(fieldInnerClass2, transferInfo.getXmlImplClasses()),
						valueNode, transferInfo);
			}
			result.put(key, value);
		}
	}
}

package org.xblink.types;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xblink.Constants;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsSet;
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
 * Set集合类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XSet extends XType {

	private XMLNodeList nodeList;
	private int nodeListLength;

	public boolean getAnnotation(Field field) {
		XBlinkAsSet xSet = field.getAnnotation(XBlinkAsSet.class);
		if (null != xSet) {
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
			// 判断是Set集合吗
			if (!Set.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个列表.");
			}
			Set<Object> objSet = (Set) field.get(obj);
			// 列表为空的话
			if (objSet.size() == 0) {
				continue;
			}
			// 判断是否需要起别名,添加前缀
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			// 前缀
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.SET);
			}

			// 集合对象的判断
			Map<Integer, ReferenceObject> referenceObjects = transferInfo.getReferenceObjects();
			// 记录解析过的Object
			ReferenceObject ref = referenceObjects.get(objSet.hashCode());
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
			refObject.setRef(objSet);
			referenceObjects.put(objSet.hashCode(), refObject);

			writer.writeStartElement(fieldName.toString());
			// Set集合内容
			for (Object object : objSet) {
				new XMLObjectWriter().write(object, writer, null, transferInfo);
			}
			// 后缀
			writer.writeEndElement();
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			// 判断是列表吗
			if (!Set.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个列表.");
			}
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.SET);
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
			ClassType classType = ClassUtil.getClassType(field, transferInfo.getXmlImplClasses());
			Set result = traceXPathSet(tarNode, classType.getFieldClass(), transferInfo);
			field.set(obj, result);

			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(result);
			referenceObjects.put(refObject.getNo(), refObject);

			// List对象塞入对应的值
			setValue(result, classType.getFieldClass(), classType.getFieldInnerClass1(),
					classType.getFieldInnerClassType1(), transferInfo, nodeListLength, nodeList);
		}
	}

	/**
	 * 
	 * @param baseNode
	 * @param fieldClass
	 * @param fieldInnerClass
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private Set traceXPathSet(XMLNode baseNode, Class fieldClass, TransferInfo transferInfo)
			throws Exception {
		boolean isInterface = fieldClass.isInterface() ? true : false;
		XMLNodeList nodeList = baseNode.getChildNodes(transferInfo.getXmlAdapter());
		int nodeListLength = nodeList.getLength(transferInfo.getXmlAdapter());
		if (nodeList == null || nodeListLength == 0) {
			if (isInterface) {
				return new HashSet();
			}
			return (Set) fieldClass.newInstance();
		}
		nodeListLength = (nodeListLength - 1) / 2;
		Set result;
		if (isInterface) {
			result = new HashSet();
		} else {
			result = (Set) fieldClass.newInstance();
		}
		// 记录两个参数
		this.nodeList = nodeList;
		this.nodeListLength = nodeListLength;

		return result;
	}

	private void setValue(Set result, Class<?> fieldClass, Class<?> fieldInnerClass,
			Type fieldInnerClassType, TransferInfo transferInfo, int nodeListLength,
			XMLNodeList nodeList) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			if (fieldInnerClass == null || fieldInnerClassType == null) {
				result.add(NodeUtil.getObject(
						nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1),
						transferInfo.getClassLoaderSwitcher(), transferInfo.getXmlAdapter()));
			} else {
				result.add(new XMLObjectReader().read(
						ClassUtil.getInstance(fieldInnerClass, transferInfo.getXmlImplClasses()),
						nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
			}
		}
	}
}

package org.xblink.types;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xblink.Constants;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsList;
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
 * 列表类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XList extends XType {

	private int nodeListLength;

	private XMLNodeList nodeList;

	public boolean getAnnotation(Field field) {
		XBlinkAsList xList = field.getAnnotation(XBlinkAsList.class);
		if (null != xList) {
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
			// 判断是列表吗
			if (!List.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个列表.");
			}
			List<Object> objList = (List) field.get(obj);
			// 列表为空的话
			if (objList.size() == 0) {
				continue;
			}
			// 判断是否需要起别名,添加前缀
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			// 前缀
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.LIST);
			}

			// 集合对象的判断
			Map<Integer, ReferenceObject> referenceObjects = transferInfo.getReferenceObjects();
			// 记录解析过的Object
			ReferenceObject ref = referenceObjects.get(objList.hashCode());
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
			refObject.setRef(objList);
			referenceObjects.put(objList.hashCode(), refObject);

			writer.writeStartElement(fieldName.toString());
			// 列表内容
			for (Object object : objList) {
				new XMLObjectWriter().write(object, writer, null, transferInfo);
			}
			// 后缀
			writer.writeEndElement();
		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			// 判断是列表吗
			if (!List.class.isAssignableFrom(field.getType())) {
				throw new Exception("字段：" + field.getName() + " 不是一个列表.");
			}
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.LIST);
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
			// 获得List对象
			List result = traceXPathList(tarNode, classType.getFieldClass(),
					classType.getFieldInnerClass(), classType.getFieldInnerClassType(),
					transferInfo);
			field.set(obj, result);

			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(result);
			referenceObjects.put(refObject.getNo(), refObject);

			// List对象塞入对应的值
			setValue(result, classType.getFieldClass(), classType.getFieldInnerClass(),
					classType.getFieldInnerClassType(), transferInfo, nodeListLength, nodeList);
		}
	}

	/**
	 * 
	 * @param baseNode
	 * @param fieldClass
	 * @param fieldInnerClass
	 * @param fieldInnerClassType
	 * @return
	 * @throws Exception
	 */
	private List traceXPathList(XMLNode baseNode, Class<?> fieldClass, Class<?> fieldInnerClass,
			Type fieldInnerClassType, TransferInfo transferInfo) throws Exception {
		boolean isInterface = fieldClass.isInterface() ? true : false;
		XMLNodeList nodeList = baseNode.getChildNodes(transferInfo.getXmlAdapter());
		int nodeListLength = nodeList.getLength(transferInfo.getXmlAdapter());
		if (nodeList == null || nodeListLength == 0) {
			if (isInterface) {
				return new ArrayList(0);
			}
			return (List) fieldClass.getConstructor(int.class).newInstance(0);
		}
		nodeListLength = (nodeListLength - 1) / 2;
		List result;
		if (isInterface) {
			result = new ArrayList();
		} else {
			result = (List) fieldClass.newInstance();
		}
		// 记录两个参数
		this.nodeList = nodeList;
		this.nodeListLength = nodeListLength;

		return result;
	}

	private void setValue(List result, Class<?> fieldClass, Class<?> fieldInnerClass,
			Type fieldInnerClassType, TransferInfo transferInfo, int nodeListLength,
			XMLNodeList nodeList) throws Exception {
		// 塞入值
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

package org.xblink.types;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;

import org.xblink.Constants;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsArray;
import org.xblink.reader.XMLObjectReader;
import org.xblink.transfer.ReferenceObject;
import org.xblink.transfer.TransferInfo;
import org.xblink.util.ClassUtil;
import org.xblink.util.NodeUtil;
import org.xblink.writer.XMLObjectWriter;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;
import org.xblink.xml.XMLNodeList;

/**
 * 数组类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XArray extends XType {

	private XMLNodeList nodeList;

	private int nodeListLength;

	public boolean getAnnotation(Field field) {
		XBlinkAsArray xArray = field.getAnnotation(XBlinkAsArray.class);
		if (null != xArray) {
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
			// 判断是数组吗
			if (!field.getType().isArray()) {
				throw new Exception("字段：" + field.getName() + " 不是一个数组.");
			}
			// TODO 8中基本类型
			Object[] objs = (Object[]) field.get(obj);
			// 列表为空的话
			if (objs.length == 0) {
				continue;
			}
			// 判断是否需要起别名,添加前缀
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			// 前缀
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.ARRAY);
			}

			// 集合对象的判断
			Map<Integer, ReferenceObject> referenceObjects = transferInfo.getReferenceObjects();
			// 记录解析过的Object
			ReferenceObject ref = referenceObjects.get(objs.hashCode());
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
			refObject.setRef(objs);
			referenceObjects.put(objs.hashCode(), refObject);

			writer.writeStartElement(fieldName.toString());
			// 数组内容
			for (Object object : objs) {
				new XMLObjectWriter().write(object, writer, null, transferInfo);
			}
			// 后缀
			writer.writeEndElement();

		}
	}

	public void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo) throws Exception {
		for (Field field : fieldTypes) {
			// 判断是数组吗
			if (!field.getType().isArray()) {
				throw new Exception("字段：" + field.getName() + " 不是一个数组.");
			}
			StringBuffer fieldName = ClassUtil.getFieldName(field);
			boolean addSuffix = field.getAnnotation(XBlinkAlias.class) == null ? true : false;
			if (addSuffix) {
				fieldName.append(Constants.ARRAY);
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

			Class<?> fieldClass;
			// 特殊情况root的array
			if (tarNode.getNodeName(transferInfo.getXmlAdapter()).equals(
					Constants.ROOT + Constants.ARRAY)) {
				fieldClass = transferInfo.getXmlImplClasses().getNewInstanceClass();
			} else {
				fieldClass = field.getType().getComponentType();
			}
			// 获得数组对象
			Object[] result = traceXPathArray(tarNode, fieldClass, transferInfo);
			field.set(obj, result);

			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(result);
			referenceObjects.put(refObject.getNo(), refObject);

			// List对象塞入对应的值
			setValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
		}
	}

	/**
	 * 
	 * @param baseNode
	 * @param fieldClass
	 * @param transferInfo
	 * @return
	 * @throws Exception
	 */
	private Object[] traceXPathArray(XMLNode baseNode, Class<?> fieldClass,
			TransferInfo transferInfo) throws Exception {
		XMLNodeList nodeList = baseNode.getChildNodes(transferInfo.getXmlAdapter());
		int nodeListLength = nodeList.getLength(transferInfo.getXmlAdapter());
		if (nodeList == null || nodeListLength == 0) {
			return (Object[]) Array.newInstance(fieldClass, 0);
		}
		nodeListLength = (nodeListLength - 1) / 2;
		Object[] result = (Object[]) Array.newInstance(fieldClass, nodeListLength);
		
		// 记录两个参数
		this.nodeList = nodeList;
		this.nodeListLength = nodeListLength;
		
		return result;
	}

	private void setValue(Object[] result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength, XMLNodeList nodeList) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			result[idx] = new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo);
		}
	}
}

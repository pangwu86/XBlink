package org.xblink.types;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.xblink.Constants;
import org.xblink.XType;
import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsList;
import org.xblink.reader.XMLObjectReader;
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
			// 获得泛型参数
			ClassType classType = ClassUtil.getClassType(field, transferInfo.getXmlImplClasses());
			field.set(
					obj,
					traceXPathList(tarNode, classType.getFieldClass(),
							classType.getFieldInnerClass(), classType.getFieldInnerClassType(),
							transferInfo));
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
		return result;
	}
}

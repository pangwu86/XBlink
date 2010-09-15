package org.xblink.types;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;

import org.xblink.Constants;
import org.xblink.EightBasicTypes;
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
			Object[] objs = null;
			Object array = field.get(obj);
			// 8中基本类型的判断
			Class<?> elememtType = ClassUtil.getArrayElementType(array);
			EightBasicTypes et = ClassUtil.getEightType(elememtType);
			if (null == et.getType()) {
				objs = (Object[]) array;
			} else {
				// TODO 优化这里的实现方式
				// 8中基本类型之一
				objs = getEightBasicArray(et, array);
			}
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
				fieldClass = transferInfo.getXmlImplClasses().getRootInstanceClass1();
			} else {
				fieldClass = field.getType().getComponentType();
			}
			// 获得数组对象
			// 判断8中基本类型的数组
			EightBasicTypes et = ClassUtil.getEightType(fieldClass);
			if (null == et.getType()) {
				Object[] result = traceXPathArray(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (int.class == et.getType()) {
				// FIXME 优化代码
				int[] result = (int[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setIntValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (byte.class == et.getType()) {
				// FIXME 优化代码
				byte[] result = (byte[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setByteValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (short.class == et.getType()) {
				// FIXME 优化代码
				short[] result = (short[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setShortValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (long.class == et.getType()) {
				// FIXME 优化代码
				long[] result = (long[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setLongValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (float.class == et.getType()) {
				// FIXME 优化代码
				float[] result = (float[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setFloatValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (double.class == et.getType()) {
				// FIXME 优化代码
				double[] result = (double[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setDoubleValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (boolean.class == et.getType()) {
				// FIXME 优化代码
				boolean[] result = (boolean[]) traceXPathArrayBasic(tarNode, fieldClass,
						transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setBooleanValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			} else if (char.class == et.getType()) {
				// FIXME 优化代码
				char[] result = (char[]) traceXPathArrayBasic(tarNode, fieldClass, transferInfo);
				field.set(obj, result);

				// 记录该对象，保持对其引用
				ReferenceObject refObject = new ReferenceObject();
				refObject.setNo(referenceObjects.size() + 1);
				refObject.setRef(result);
				referenceObjects.put(refObject.getNo(), refObject);

				// Array对象塞入对应的值
				setCharValue(result, fieldClass, transferInfo, nodeListLength, nodeList);
			}
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
	private Object traceXPathArrayBasic(XMLNode baseNode, Class<?> fieldClass,
			TransferInfo transferInfo) throws Exception {
		XMLNodeList nodeList = baseNode.getChildNodes(transferInfo.getXmlAdapter());
		int nodeListLength = nodeList.getLength(transferInfo.getXmlAdapter());
		if (nodeList == null || nodeListLength == 0) {
			return Array.newInstance(fieldClass, 0);
		}
		nodeListLength = (nodeListLength - 1) / 2;
		Object result = Array.newInstance(fieldClass, nodeListLength);
		// 记录两个参数
		this.nodeList = nodeList;
		this.nodeListLength = nodeListLength;
		return result;
	}

	// 8种基本类型

	private void setIntValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setInt(result, idx, (Integer) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setShortValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setShort(result, idx, (Short) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setLongValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setLong(result, idx, (Long) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setFloatValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setFloat(result, idx, (Float) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setDoubleValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setDouble(result, idx, (Double) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setByteValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setByte(result, idx, (Byte) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setCharValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setChar(result, idx, (Character) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
		}
	}

	private void setBooleanValue(Object result, Class<?> fieldClass, TransferInfo transferInfo,
			int nodeListLength2, XMLNodeList nodeList2) throws Exception {
		for (int idx = 0; idx < nodeListLength; idx++) {
			Array.setBoolean(result, idx, (Boolean) new XMLObjectReader().read(
					ClassUtil.getInstance(fieldClass, transferInfo.getXmlImplClasses()),
					nodeList.item(transferInfo.getXmlAdapter(), idx * 2 + 1), transferInfo));
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

	private Object[] getEightBasicArray(EightBasicTypes et, Object array) {
		if (int.class == et.getType()) {
			int[] basicArray = (int[]) array;
			Integer[] objectArray = new Integer[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (double.class == et.getType()) {
			double[] basicArray = (double[]) array;
			Double[] objectArray = new Double[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (float.class == et.getType()) {
			float[] basicArray = (float[]) array;
			Float[] objectArray = new Float[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (short.class == et.getType()) {
			short[] basicArray = (short[]) array;
			Short[] objectArray = new Short[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (long.class == et.getType()) {
			long[] basicArray = (long[]) array;
			Long[] objectArray = new Long[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (byte.class == et.getType()) {
			byte[] basicArray = (byte[]) array;
			Byte[] objectArray = new Byte[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (char.class == et.getType()) {
			char[] basicArray = (char[]) array;
			Character[] objectArray = new Character[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else if (boolean.class == et.getType()) {
			boolean[] basicArray = (boolean[]) array;
			Boolean[] objectArray = new Boolean[basicArray.length];
			for (int i = 0; i < basicArray.length; i++) {
				objectArray[i] = basicArray[i];
			}
			return objectArray;
		} else {
			return null;
		}
	}

	// private <T1, T2> Object[] getArrays(T1 type1, T2 type2, Class<?>
	// type2Class, Object array) {
	// T1[] basicArray = (T1[]) array;
	// T2[] objectArray = (T2[]) Array.newInstance(type2Class,
	// basicArray.length);
	// for (int i = 0; i < basicArray.length; i++) {
	// objectArray[i] = (T2) basicArray[i];
	// }
	// return objectArray;
	// }
}

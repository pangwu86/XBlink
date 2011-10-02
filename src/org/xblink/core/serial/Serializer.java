package org.xblink.core.serial;

import org.xblink.core.AnalysisObject;
import org.xblink.core.Constant;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.serial.xtype.impl.XAttribute;
import org.xblink.core.serial.xtype.impl.XCollection;
import org.xblink.core.serial.xtype.impl.XElement;
import org.xblink.core.serial.xtype.impl.XMap;
import org.xblink.core.serial.xtype.impl.XObject;
import org.xblink.util.StringUtil;
import org.xblink.util.TypeUtil;

/**
 * 序列化一个对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Serializer {

	private Serializer() {
	}

	/**
	 * 对一个不确定类型的对象，进行序列化。
	 * 
	 * @param obj
	 * @param transferInfo
	 * @param objName
	 * @throws Exception
	 */
	public static void writeUnknow(Object obj, TransferInfo transferInfo, String objName) throws Exception {
		Class<?> objClz = obj.getClass();
		String tagName = StringUtil.isBlankStr(objName) ? AliasCache.getClassName(objClz) : objName;
		// 下面根据传入对象的类型，采用不同的策略
		if (TypeUtil.isSingleValueType(objClz)) {
			// 单值类型
			writeSingleValue(objClz, obj, transferInfo, tagName);
		} else {
			// 其他类型都可以算作引用类型
			if (isReferenceObject(obj, transferInfo)) {
				// 引用类型
				writeReference(objClz, transferInfo.getRefMap().get(obj), transferInfo, tagName);
			} else {
				if (TypeUtil.isCollectionType(objClz)) {
					// 集合类型
					writeCollection(objClz, obj, transferInfo, tagName);
				} else if (TypeUtil.isMapType(objClz)) {
					// Map类型
					writeMap(objClz, obj, transferInfo, tagName);
				} else {
					// 对象类型
					writeObject(objClz, obj, transferInfo, tagName);
				}
			}
		}
	}

	// ********** 根据类型的不同，采用不同的序列化策略 ****************

	/**
	 * 单值类型对象序列化。
	 * 
	 * @param objClz
	 * @param obj
	 * @param transferInfo
	 * @param tagName
	 * @throws Exception
	 */
	public static void writeSingleValue(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeElementText(tagName, ConverterWarehouse.getTextByData(objClz, obj));
	}

	/**
	 * 集合类型对象序列化。
	 * 
	 * @param objClz
	 * @param obj
	 * @param transferInfo
	 * @param tagName
	 * @throws Exception
	 */
	public static void writeCollection(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		XCollection.INSTANCE.writeOneItem(objClz, obj, transferInfo, tagName);
	}

	/**
	 * Map类型对象序列化。
	 * 
	 * @param objClz
	 * @param obj
	 * @param transferInfo
	 * @param tagName
	 * @throws Exception
	 */
	public static void writeMap(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		XMap.INSTANCE.writeOneItem(objClz, obj, transferInfo, tagName);
	}

	/**
	 * 引用类型序列化。
	 * 
	 * @param objClz
	 * @param refObj
	 * @param transferInfo
	 * @param tagName
	 * @throws Exception
	 */
	public static void writeReference(Class<?> objClz, ReferenceObject refObj, TransferInfo transferInfo, String tagName)
			throws Exception {
		// 以Attribute的形式记录引用
		// 例如：<a ref='../../b/c/a' />
		transferInfo.getDocWriter().writeStartTag(tagName);
		transferInfo.getDocWriter().writeAttribute(Constant.ATTRIBUTE_REFERENCE,
				transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
		transferInfo.getDocWriter().writeEndTagNotWithNewLine(tagName);
	}

	/**
	 * 对象类型序列化。
	 * 
	 * @param objClz
	 * @param obj
	 * @param transferInfo
	 * @param tagName
	 * @throws Exception
	 */
	public static void writeObject(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(tagName);
		// 分析对象，根据分析结果，逐个类型进行序列化
		AnalysisObject analysisObject = AnalysisCache.getAnalysisObject(objClz);
		// attribute类型
		if (!analysisObject.attributeIsEmpty()) {
			XAttribute.INSTANCE.writeItem(obj, analysisObject, transferInfo);
		}
		// element类型
		if (!analysisObject.elementIsEmpty()) {
			XElement.INSTANCE.writeItem(obj, analysisObject, transferInfo);
		}
		// obj类型
		if (!analysisObject.objIsEmpty()) {
			XObject.INSTANCE.writeItem(obj, analysisObject, transferInfo);
		}
		// collection类型
		if (!analysisObject.collectionIsEmpty()) {
			XCollection.INSTANCE.writeItem(obj, analysisObject, transferInfo);
		}
		// map类型
		if (!analysisObject.mapIsEmpty()) {
			XMap.INSTANCE.writeItem(obj, analysisObject, transferInfo);
		}
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

	// ************** 辅助类 ************

	/**
	 * 判断对象是否属于引用对象。
	 * 
	 * @param obj
	 * @param transferInfo
	 * @return
	 * @throws Exception
	 */
	public static boolean isReferenceObject(Object obj, TransferInfo transferInfo) throws Exception {
		ReferenceObject refObj = transferInfo.getRefMap().get(obj);
		// 判断对象是否已经序列化过，查看其是否是个引用对象
		if (null != refObj) {
			return true;
		}
		// 记录当前对象
		String[] currentPath = transferInfo.getPathTracker().getCurrentPath();
		refObj = new ReferenceObject(obj, currentPath);
		transferInfo.getRefMap().put(obj, refObj);
		return false;
	}

}

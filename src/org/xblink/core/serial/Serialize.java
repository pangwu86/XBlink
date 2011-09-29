package org.xblink.core.serial;

import org.xblink.core.AnalysisObject;
import org.xblink.core.Constant;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.type.XAttribute;
import org.xblink.core.type.XElement;
import org.xblink.core.type.XObject;
import org.xblink.util.StringUtil;
import org.xblink.util.TypeUtil;

/**
 * 序列化一个对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Serialize {

	private Serialize() {
	}

	public static void doIt(Object obj, TransferInfo transferInfo, String objName) throws Exception {
		Class<?> objClz = obj.getClass();
		String tagName = StringUtil.isBlankStr(objName) ? getTagName(objClz) : objName;
		// 下面根据传入对象的类型，采用不同的策略
		if (TypeUtil.isBasicType(objClz)) {
			// 基本类型
			writeBasic(objClz, objClz, transferInfo, tagName);
		} else {
			// 需要先写Node，再计算引用，否则无法记录其路径。
			transferInfo.getDocWriter().writeStartTag(tagName);
			// 其他类型都可以算作引用类型
			if (isReferenceObject(obj, transferInfo)) {
				writeReference(objClz, transferInfo.getRefMap().get(obj), transferInfo, tagName);
			} else {
				if (TypeUtil.isCollectionType(objClz)) {
					// 集合
					// TODO
				} else if (TypeUtil.isMapType(objClz)) {
					// Map类型
					// TODO
				} else {
					// 对象
					writeObject(objClz, obj, transferInfo, tagName);
				}
			}
		}
	}

	// ********** 根据传入值的不同，采用不同的序列化策略 ****************

	// 基本类型
	private static void writeBasic(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {

		transferInfo.getDocWriter().writeElementText(tagName, convert(objClz, obj));
	}

	// 对象
	public static void writeObject(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		// 分析对象
		AnalysisObject analysisObject = getAnalysisClass(objClz);
		// 根据分析结果，逐个类型进行序列化

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

		}
		// map类型
		if (!analysisObject.mapIsEmpty()) {

		}
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

	// 引用
	private static void writeReference(Class<?> objClz, ReferenceObject refObj, TransferInfo transferInfo,
			String tagName) throws Exception {
		// 以Attribute的形式记录引用
		// 例如：<a ref='../../b/c/a' />
		transferInfo.getDocWriter().writeAttribute(Constant.ATTRIBUTE_REFERENCE,
				transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
		transferInfo.getDocWriter().writeEndTagNotWithNewLine(tagName);
	}

	// ********************* 根据对象类型的不同，采用不同方式写入文件 *****************************

	// ************** 几个辅助类 ************

	private static String convert(Class<?> objClz, Object obj) throws Exception {
		return ConverterWarehouse.getTextByData(objClz, obj);
	}

	private static boolean isReferenceObject(Object obj, TransferInfo transferInfo) throws Exception {
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

	private static String getTagName(Class<?> objClz) {
		return AliasCache.getClassName(objClz);
	}

	private static AnalysisObject getAnalysisClass(Class<?> objClz) {
		return AnalysisCache.getAnalysisObject(objClz);
	}
}

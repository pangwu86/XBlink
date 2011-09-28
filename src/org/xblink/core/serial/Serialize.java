package org.xblink.core.serial;

import org.xblink.core.AnalysisObject;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.type.XAttribute;
import org.xblink.core.type.XElement;
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
		// 基本类型还是对象
		if (TypeUtil.isBasicType(objClz)) {

		} else {

		}

		// 分析对象
		AnalysisObject analysisObject = getAnalysisClass(objClz);
		// 根据分析结果，逐个类型进行序列化
		String tagName = StringUtil.isBlankStr(objName) ? getTagName(objClz) : objName;
		transferInfo.getDocWriter().writeStartTag(tagName);
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

		}
		// collection类型
		if (!analysisObject.collectionIsEmpty()) {

		}
		// map类型
		if (!analysisObject.mapIsEmpty()) {

		}
		transferInfo.getDocWriter().writeStartTag(tagName);
	}

	// ********************* 根据对象类型的不同，采用不同方式写入文件 *****************************

	private static void writeObjectType(Class<?> objClz, Object obj, TransferInfo transferInfo, String objName)
			throws Exception {

		// // 写节点
		// String tagName = StringUtil.isBlankStr(objName) ? getTagName(objClz)
		// : objName;
		// transferInfo.getDocWriter().writeStartTag(tagName);
		// // 记录引用信息
		// ReferenceObject refObj = transferInfo.getRefMap().get(obj);
		// if (isReferenceObject(obj, refObj, transferInfo)) {
		// // 以Attribute的形式记录引用
		// // 例如：<a ref='../../b/c/a' />
		// transferInfo.getDocWriter().writeAttribute(Constant.ATTRIBUTE_REFERENCE,
		// transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
		// transferInfo.getDocWriter().writeEndTagNotWithNewLine(tagName);
		// } else {
		// // 调用转换器
		// convert(objClz, obj, tagName, transferInfo);
		// transferInfo.getDocWriter().writeEndTag(tagName);
		// }
	}

	// ************** 几个辅助类 ************

	private static boolean isReferenceObject(Object obj, ReferenceObject refObj, TransferInfo transferInfo)
			throws Exception {
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

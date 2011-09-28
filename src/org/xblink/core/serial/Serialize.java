package org.xblink.core.serial;

import org.xblink.core.Constant;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;
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
		// 是否是基本类型
		if (TypeUtil.isBasicType(objClz)) {
			writeBasicType(objClz, obj, transferInfo, objName);
		} else if (TypeUtil.isCollectionType(objClz)) {
			writeCollectionType(objClz, obj, transferInfo, objName);
		} else {
			writeObjectType(objClz, obj, transferInfo, objName);
		}
	}

	// ********************* 根据对象类型的不同，采用不同方式写入文件 *****************************

	private static void writeBasicType(Class<?> objClz, Object obj, TransferInfo transferInfo, String objName)
			throws Exception {
		String tagName = StringUtil.isBlankStr(objName) ? getTagName(objClz) : objName;
		transferInfo.getDocWriter().writeStartTag(tagName);
		// 调用转换器
		convert(objClz, obj, transferInfo);
		transferInfo.getDocWriter().writeEndTagNotWithNewLine(tagName);
	}

	private static void writeCollectionType(Class<?> objClz, Object obj, TransferInfo transferInfo, String objName)
			throws Exception {
		// TODO
	}

	private static void writeObjectType(Class<?> objClz, Object obj, TransferInfo transferInfo, String objName)
			throws Exception {
		// 分析对象
		analysisClass(objClz);
		// 写节点
		String tagName = StringUtil.isBlankStr(objName) ? getTagName(objClz) : objName;
		transferInfo.getDocWriter().writeStartTag(tagName);
		// 记录引用信息
		ReferenceObject refObj = transferInfo.getRefMap().get(obj);
		if (isReferenceObject(obj, refObj, transferInfo)) {
			// 以Attribute的形式记录引用
			// 例如：<a ref='../../b/c/a' />
			transferInfo.getDocWriter().writeAttribute(Constant.ATTRIBUTE_REFERENCE,
					transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
			transferInfo.getDocWriter().writeEndTagNotWithNewLine(tagName);
		} else {
			// 调用转换器
			convert(objClz, obj, transferInfo);
			transferInfo.getDocWriter().writeEndTag(tagName);
		}
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

	private static void convert(Class<?> objClz, Object obj, TransferInfo transferInfo) throws Exception {
		// 根据对象类型，寻找对应的转换器，完成转换操作
		Converter converter = ConverterWarehouse.searchConverterForType(objClz);
		converter.obj2Text(obj, transferInfo);
	}

	private static void analysisClass(Class<?> objClz) {
		// TODO 分析class，生成分析结果，然后缓存
	}

	private static String getTagName(Class<?> clz) {
		return AliasCache.getClassName(clz);
	}
}

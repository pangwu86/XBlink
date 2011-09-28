package org.xblink.core.serial;

import org.xblink.core.Constant;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;

/**
 * 序列化一个对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Serialize {

	private Serialize() {
	}

	public static void doIt(Object obj, TransferInfo transferInfo) throws Exception {
		Class<?> objClz = obj.getClass();
		// 分析对象
		analysisClass(objClz);
		// 写节点
		String tagName = getTagName(objClz);
		transferInfo.getDocWriter().writeStartTag(tagName);
		writeContent(obj, objClz, transferInfo);
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

	private static void analysisClass(Class<?> objClz) {
		// TODO 分析class，生成分析结果，然后缓存
	}

	private static String getTagName(Class<?> clz) {
		return AliasCache.getClassName(clz);
	}

	private static void writeContent(Object obj, Class<?> objClz, TransferInfo transferInfo) throws Exception {
		// 判断对象是否已经序列化过，查看其是否是个引用对象
		ReferenceObject refObj = transferInfo.getRefMap().get(obj);
		if (null != refObj) {
			// 以Attribute的形式记录引用
			transferInfo.getDocWriter().writeAttribute(Constant.ATTRIBUTE_REFERENCE,
					transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
			return;
		}

		// 记录当前对象
		String[] currentPath = transferInfo.getPathTracker().getCurrentPath();
		refObj = new ReferenceObject(obj, currentPath);
		transferInfo.getRefMap().put(obj, refObj);

		// 根据对象类型，寻找对应的转换器，完成转换操作
		Converter converter = ConverterWarehouse.searchConverterForType(objClz);
		converter.obj2Text(obj, transferInfo);
	}
}

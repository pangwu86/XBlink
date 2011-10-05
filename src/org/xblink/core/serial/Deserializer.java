package org.xblink.core.serial;

import org.xblink.core.AnalysisObject;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.convert.ConverterRegistry;
import org.xblink.core.serial.xtype.impl.XAttribute;
import org.xblink.core.serial.xtype.impl.XCollection;
import org.xblink.core.serial.xtype.impl.XCustomized;
import org.xblink.core.serial.xtype.impl.XElement;
import org.xblink.core.serial.xtype.impl.XEnum;
import org.xblink.core.serial.xtype.impl.XMap;
import org.xblink.core.serial.xtype.impl.XObject;
import org.xblink.util.StringUtil;
import org.xblink.util.TypeUtil;

/**
 * 反序列化一个对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Deserializer {

	private Deserializer() {
	}

	/**
	 * 对一个不确定类型的对象，进行序列化。
	 * 
	 * @param obj
	 * @param transferInfo
	 * @param objName
	 * @throws Exception
	 */
	public static void readUnknow(Object obj, TransferInfo transferInfo, String objName) throws Exception {
		Class<?> objClz = obj.getClass();
		String tagName = StringUtil.isBlankStr(objName) ? AliasCache.getClassName(objClz) : objName;
		// 下面根据传入对象的类型，采用不同的策略
		if (TypeUtil.isSingleValueType(objClz)) {
			// 单值类型
			readSingleValue(objClz, obj, transferInfo, tagName);
		} else if (TypeUtil.isEnum(objClz)) {
			// 枚举类型
		} else {
			// 这里只要是可能被引用的类型，都要进行先写节点后马上缓存引用
			// 其他类型都可以算作引用类型
			if (isReferenceObject(transferInfo)) {
				// 引用类型
			} else {
				if (TypeUtil.isCollectionType(objClz)) {
					// 集合类型
				} else if (TypeUtil.isMapType(objClz)) {
					// Map类型
				} else {
					// 当做对象处理前，需要查看该类是否有自定义的转换器
					if (ConverterRegistry.hasCustomizedConverterAndRegister(objClz)) {
						// 单值类型
					} else {
						// 对象类型
						readObject(objClz, obj, transferInfo, tagName);
					}
				}
			}
		}
	}

	private static void readObject(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocReader().moveDown();
		// FIXME
		recordReferenceObject(obj, transferInfo);
		// 分析对象，根据分析结果，逐个类型进行序列化
		AnalysisObject analysisObject = AnalysisCache.getAnalysisObject(objClz);
		// attribute类型
		if (!analysisObject.attributeIsEmpty()) {
			XAttribute.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// element类型
		if (!analysisObject.elementIsEmpty()) {
			XElement.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// enum类型
		if (!analysisObject.enumIsEmpty()) {
			XEnum.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// customized类型
		if (!analysisObject.customizedIsEmpty()) {
			XCustomized.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// obj类型
		if (!analysisObject.objIsEmpty()) {
			XObject.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// collection类型
		if (!analysisObject.collectionIsEmpty()) {
			XCollection.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		// map类型
		if (!analysisObject.mapIsEmpty()) {
			XMap.INSTANCE.readItem(obj, analysisObject, transferInfo);
		}
		transferInfo.getDocReader().moveUp();
	}

	public static void readSingleValue(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName) {

	}

	private static boolean isReferenceObject(TransferInfo transferInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void recordReferenceObject(Object obj, TransferInfo transferInfo) {
		// 记录当前对象
		String[] currentPath = transferInfo.getPathTracker().getCurrentPath();
		transferInfo.getRefMap().put(obj, new ReferenceObject(obj, currentPath));
	}

}

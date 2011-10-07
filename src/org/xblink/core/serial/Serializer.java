package org.xblink.core.serial;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.xblink.core.AnalysisObject;
import org.xblink.core.Constant;
import org.xblink.core.ReferenceObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;
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
		} else if (TypeUtil.isEnum(objClz)) {
			// 枚举类型
			writeEnum(objClz, obj, transferInfo, tagName);
		} else {
			// 这里只要是可能被引用的类型，都要进行先写节点后马上缓存引用
			// 其他类型都可以算作引用类型
			if (SerialHelper.isReferenceObjectByObj(obj, transferInfo)) {
				// 引用类型
				writeReference(objClz, obj, transferInfo, tagName);
			} else {
				if (TypeUtil.isCollectionType(objClz)) {
					// 集合类型
					writeCollection(objClz, obj, transferInfo, tagName);
				} else if (TypeUtil.isEntryType(objClz)) {
					// Map.Entry类型
					writeEntry(objClz, obj, transferInfo, tagName);
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

	private static void writeSingleValue(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		writeSingleValue(obj, transferInfo, tagName, ConverterWarehouse.searchConverterForType(objClz, transferInfo));
	}

	private static void writeEnum(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		writeSingleValue(obj, transferInfo, tagName, SerialHelper.getEnumConverter());
	}

	private static void writeSingleValue(Object obj, TransferInfo transferInfo, String tagName, Converter converter)
			throws Exception {
		transferInfo.getDocWriter().writeElementText(tagName, converter.obj2Text(obj));
	}

	private static void writeCollection(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(tagName);
		SerialHelper.recordReferenceObjectByObj(obj, transferInfo);
		if (objClz.isArray()) {
			int length = Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				Object item = Array.get(obj, i);
				writeUnknow(item, transferInfo, null);
			}
		} else {
			Collection<?> collection = (Collection<?>) obj;
			for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
				Object item = iterator.next();
				writeUnknow(item, transferInfo, null);
			}
		}
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

	private static void writeMap(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(tagName);
		SerialHelper.recordReferenceObjectByObj(obj, transferInfo);
		Map<?, ?> map = (Map<?, ?>) obj;
		for (Iterator<?> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iterator.next();
			transferInfo.getDocWriter().writeStartTag(Constant.MAP_ENTRY);
			writeUnknow(entry.getKey(), transferInfo, null);
			writeUnknow(entry.getValue(), transferInfo, null);
			transferInfo.getDocWriter().writeEndTag(Constant.MAP_ENTRY);
		}
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

	private static void writeEntry(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(tagName);
		SerialHelper.recordReferenceObjectByObj(obj, transferInfo);
		Map.Entry<?, ?> entry = (Entry<?, ?>) obj;
		writeUnknow(entry.getKey(), transferInfo, null);
		writeUnknow(entry.getValue(), transferInfo, null);
		transferInfo.getDocWriter().writeStartTag(tagName);
	}

	private static void writeReference(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		ReferenceObject refObj = transferInfo.getRefMap().get(obj);
		transferInfo.getDocWriter().writeStartTag(tagName);
		transferInfo.getDocWriter().writeReference(tagName, Constant.ATTRIBUTE_REFERENCE,
				transferInfo.getPathTracker().getReferencePathAsString(refObj.getPath()));
	}

	private static void writeObject(Class<?> objClz, Object obj, TransferInfo transferInfo, String tagName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(tagName);
		// 记录引用的对象
		SerialHelper.recordReferenceObjectByObj(obj, transferInfo);
		// 分析对象，根据分析结果，逐个类型进行序列化
		boolean ignoreTransient = transferInfo.getXbConfig().isIgnoreTransient();
		AnalysisObject analysisObject = AnalysisCache.getAnalysisObject(objClz, ignoreTransient);
		boolean ignoreNull = transferInfo.getXbConfig().isIgnoreNull();
		// attribute类型
		if (!analysisObject.attributeIsEmpty()) {
			for (Field field : analysisObject.getAttributeFieldTypes()) {
				// 判断空的话是否还要进行序列化
				Object fieldValue = field.get(obj);
				boolean useNullConverter = false;
				if (null == fieldValue) {
					if (ignoreNull) {
						continue;
					} else {
						useNullConverter = true;
					}
				}
				String fieldName = AliasCache.getFieldName(obj.getClass(), field);
				Converter converter = null;
				if (analysisObject.isFieldHasConverter(field) || useNullConverter) {
					// Null转换器或者使用自定义转换器
					converter = useNullConverter ? SerialHelper.getNullConverter() : analysisObject
							.getFieldConverter(field);
				} else {
					converter = ConverterWarehouse.searchConverterForType(field.getType(), transferInfo);
				}
				transferInfo.getDocWriter().writeAttribute(fieldName, converter.obj2Text(fieldValue));
			}
		}
		// 其他类型
		if (!analysisObject.otherIsEmpty()) {
			for (Field field : analysisObject.getOtherFieldTypes()) {
				// 判断空的话是否还要进行序列化
				Object fieldValue = field.get(obj);
				boolean useNullConverter = false;
				if (null == fieldValue) {
					if (ignoreNull) {
						continue;
					} else {
						useNullConverter = true;
					}
				}
				String fieldName = AliasCache.getFieldName(obj.getClass(), field);
				if (analysisObject.isFieldHasConverter(field) || useNullConverter) {
					// Null转换器或者使用自定义转换器
					Converter converter = useNullConverter ? SerialHelper.getNullConverter() : analysisObject
							.getFieldConverter(field);
					writeSingleValue(fieldValue, transferInfo, fieldName, converter);
				} else {
					// 其他情况
					writeUnknow(fieldValue, transferInfo, fieldName);
				}
			}
		}
		transferInfo.getDocWriter().writeEndTag(tagName);
	}

}

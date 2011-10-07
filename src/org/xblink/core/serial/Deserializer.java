package org.xblink.core.serial;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.core.AnalysisObject;
import org.xblink.core.Constant;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.cache.AnalysisCache;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.doc.DocReader;
import org.xblink.util.TypeUtil;

/**
 * 反序列化一个对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Deserializer {

	private Deserializer() {
	}

	public static Object readUnknow(Class<?> objClz, Object obj, Field field, TransferInfo transferInfo)
			throws Exception {
		Object result = null;
		// objClz必须有
		if (null == objClz) {
			if (null != obj) {
				objClz = obj.getClass();
			}
		}
		if (null == objClz) {
			// TODO 最麻烦的情况了，需要根据名称进行猜测，找到对应的类进行处理
			// 这里怎么处理来，主要是集合类型与Map类型，没有使用泛型的情况下，如何去做
			result = readAnyType(transferInfo);
		}
		// 下面根据传入对象的类型，采用不同的策略
		if (TypeUtil.isSingleValueType(objClz)) {
			// 单值类型
			result = readSingleValue(objClz, transferInfo);
		} else if (TypeUtil.isEnum(objClz)) {
			// 枚举类型
			result = readEnum(objClz, transferInfo);
		} else {
			// 其他类型都可以算作引用类型
			if (SerialHelper.isReferenceObjectByNode(transferInfo)) {
				// 引用类型
				result = readReference(obj, field, transferInfo);
			} else {
				if (TypeUtil.isCollectionType(objClz)) {
					// 集合类型
					result = readCollection(objClz, obj, field, transferInfo);
				} else if (TypeUtil.isMapType(objClz)) {
					// Map类型
					result = readMap(objClz, obj, field, transferInfo);
				} else {
					// 对象类型
					result = readObject(objClz, transferInfo);
				}
			}
		}
		return result;
	}

	private static Object readAnyType(TransferInfo transferInfo) throws Exception {
		// 只能根据名称去猜测类型
		String nodeName = transferInfo.getDocReader().getNodeName();
		Class<?> classType = tryFindTypeByNodeName(nodeName);
		return readUnknow(classType, null, null, transferInfo);
	}

	private static Class<?> tryFindTypeByNodeName(String nodeName) {
		Class<?> type = AliasCache.getClassByAliasName(nodeName);
		if (null == type) {
			// 尝试根据名称去加载
			try {
				type = Class.forName(nodeName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(String.format("无法根据节点名称[%s]找个对应的类。", nodeName), e);
			}
		}
		return type;
	}

	private static Object readSingleValue(Class<?> objClz, TransferInfo transferInfo) throws Exception {
		return ConverterWarehouse.searchConverterForType(objClz).text2Obj(transferInfo.getDocReader().getTextValue());
	}

	private static Object readEnum(Class<?> objClz, TransferInfo transferInfo) throws Exception {
		return SerialHelper.getEnumConverter().text2Obj(transferInfo.getDocReader().getTextValue(), objClz);
	}

	private static Object readReference(Object obj, Field field, TransferInfo transferInfo) throws Exception {
		String relativePath = transferInfo.getDocReader().getAttribute(Constant.ATTRIBUTE_REFERENCE);
		String objPath = null;
		if (relativePath.startsWith(Constant.PATH_SEPARATER)) {
			// 如果是采用了绝对路径，则不需要再计算
			objPath = relativePath;
		} else {
			objPath = transferInfo.getPathTracker().getTargetNodeAbsolutePathAsString(relativePath);
		}
		Object result = transferInfo.getPathRefMap().get(objPath);
		if (null == result) {
			// TODO 记录当前状态等全部完成后再次放入
			// obj field objPath 这三者
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object readCollection(Class<?> objClz, Object obj, Field field, TransferInfo transferInfo)
			throws Exception {
		DocReader docReader = transferInfo.getDocReader();
		Object result = null;
		if (objClz.isArray()) {
			Class<?> arrayItemClz = objClz.getComponentType();
			List<Object> items = new ArrayList<Object>();
			while (docReader.hasMoreChildren()) {
				docReader.moveDown();
				Object item = readUnknow(arrayItemClz, null, null, transferInfo);
				items.add(item);
				docReader.moveUp();
			}
			// 放入数组中
			Object array = Array.newInstance(arrayItemClz, items.size());
			int i = 0;
			for (Iterator<Object> iterator = items.iterator(); iterator.hasNext();) {
				Array.set(array, i++, iterator.next());
			}
			result = array;
		} else {
			Collection collection = null;
			if (null != obj) {
				// 集合的话可以利用传入的对象，但是需要清空该对象
				collection = (Collection) obj;
				collection.clear();
			} else {
				if (List.class.isAssignableFrom(objClz)) {
					collection = (Collection) transferInfo.getObjectOperator().newInstance(List.class);
				} else if (Set.class.isAssignableFrom(objClz)) {
					collection = (Collection) transferInfo.getObjectOperator().newInstance(Set.class);
				} else {
					throw new RuntimeException(String.format("无法根据%s生成Collection对象。", objClz.getName()));
				}
			}
			// 尝试获得泛型
			Class<?> itemClz = null;
			if (null != field) {
				itemClz = transferInfo.getObjectOperator().getCollectionGenericType(field.getGenericType());
			}
			while (docReader.hasMoreChildren()) {
				docReader.moveDown();
				Object item = readUnknow(itemClz, null, null, transferInfo);
				collection.add(item);
				docReader.moveUp();
			}
			result = collection;
		}
		// 记录引用的对象
		SerialHelper.recordReferenceObjectByPath(result, transferInfo);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object readMap(Class<?> objClz, Object obj, Field field, TransferInfo transferInfo) throws Exception {
		DocReader docReader = transferInfo.getDocReader();
		Map map = null;
		if (null != obj) {
			// 集合的话可以利用传入的对象，但是需要清空该对象
			map = (Map) obj;
			map.clear();
		} else {
			if (Map.class.isAssignableFrom(objClz)) {
				map = (Map) transferInfo.getObjectOperator().newInstance(Map.class);
			} else {
				throw new RuntimeException(String.format("无法根据%s生成Map对象。", objClz.getName()));
			}
		}
		// 尝试获得泛型
		Class<?> keyClz = null;
		Class<?> valueClz = null;
		if (null != field) {
			keyClz = transferInfo.getObjectOperator().getMapKeyGenericType(field.getGenericType());
			valueClz = transferInfo.getObjectOperator().getMapValueGenericType(field.getGenericType());
		}
		while (docReader.hasMoreChildren()) {
			docReader.moveDown(); // 进入entry

			docReader.moveDown();// 进入key
			Object key = readUnknow(keyClz, null, null, transferInfo);
			docReader.moveUp();// 退出key

			docReader.moveDown();// 进入value
			Object value = readUnknow(valueClz, null, null, transferInfo);
			docReader.moveUp();// 退出value

			docReader.moveUp();// 退出entry
			map.put(key, value);
		}
		// 记录引用的对象
		SerialHelper.recordReferenceObjectByPath(map, transferInfo);
		return map;
	}

	private static Object readObject(Class<?> objClz, TransferInfo transferInfo) throws Exception {
		Object result = transferInfo.getObjectOperator().newInstance(objClz);
		DocReader docReader = transferInfo.getDocReader();
		// 记录引用的对象
		SerialHelper.recordReferenceObjectByPath(result, transferInfo);
		// 分析对象，根据分析结果，逐个类型进行序列化
		boolean ignoreTransient = transferInfo.getXbConfig().isIgnoreTransient();
		AnalysisObject analysisObject = AnalysisCache.getAnalysisObject(objClz, ignoreTransient);
		boolean ignoreNull = transferInfo.getXbConfig().isIgnoreNull();
		// attribute类型
		if (!analysisObject.attributeIsEmpty() && docReader.getAttributeCount() > 0) {
			Iterator<String> iter = docReader.getAttributeNames();
			while (iter.hasNext()) {
				String attName = iter.next();
				if (analysisObject.getAttributeFieldMap().containsKey(attName)) {
					Field field = analysisObject.getAttributeFieldMap().get(attName);
					String fieldValueStr = docReader.getAttribute(attName);
					Object fieldValue = null;
					if (!ignoreNull && SerialHelper.getNullConverter().canConvert(fieldValueStr)) {
						fieldValue = null;
					} else if (analysisObject.isFieldHasConverter(field)) {
						fieldValue = analysisObject.getFieldConverter(field).text2Obj(fieldValueStr);
					} else {
						fieldValue = ConverterWarehouse.searchConverterForType(field.getType()).text2Obj(fieldValueStr);
					}
					transferInfo.getObjectOperator().setField(result, field, fieldValue);
				}
			}
		}
		// 其他类型
		if (!analysisObject.otherIsEmpty() && docReader.hasMoreChildren()) {
			docReader.moveDown();
			String nodeName = docReader.getNodeName();
			Field field = analysisObject.getOtherFieldMap().get(nodeName);
			Class<?> fieldClz = field.getType();
			Object fieldValue = null;
			boolean useNullConverter = false;
			boolean useGetTextValue = false;
			String fieldValueStr = null;
			if (!ignoreNull) {
				// 尝试获得text，如果是Null，则调用NullConverter
				fieldValueStr = docReader.getTextValue();
				useGetTextValue = true;
				if (null != fieldValueStr && SerialHelper.getNullConverter().canConvert(fieldValueStr)) {
					useNullConverter = true;
				}
			}
			if (analysisObject.isFieldHasConverter(field) || useNullConverter) {
				if (!useGetTextValue) {
					fieldValueStr = docReader.getTextValue();
				}
				fieldValue = useNullConverter ? null : analysisObject.getFieldConverter(field).text2Obj(fieldValueStr);
			} else {
				fieldValue = readUnknow(fieldClz, result, field, transferInfo);
			}
			transferInfo.getObjectOperator().setField(result, field, fieldValue);
			docReader.moveUp();
		}
		return result;
	}
}

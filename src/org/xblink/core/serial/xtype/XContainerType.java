package org.xblink.core.serial.xtype;

import java.lang.reflect.Field;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;

/**
 * XCollection与XMap格式的基类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class XContainerType implements XType {

	public void writeItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		for (Field field : getFields(analysisObject)) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			// 空的话不进行序列化
			Object fieldValue = field.get(obj);
			if (null == fieldValue) {
				continue;
			}
			// 外层名称
			String collectionName = AliasCache.getFieldName(obj.getClass(), field);
			writeOneItem(field.getType(), fieldValue, transferInfo, collectionName);
		}
	}

	public abstract void writeOneItem(Class<?> objClz, Object obj, TransferInfo transferInfo, String collectionName)
			throws Exception;
}

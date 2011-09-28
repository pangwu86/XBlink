package org.xblink.core.type;

import java.lang.reflect.Field;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;

/**
 * Attribute与Element格式的共通方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class XBasicType extends AbstractXType {

	public void writeItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		for (Field field : analysisObject.getAttributeFieldTypes()) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			// 空的话不进行序列化
			Object fieldValue = field.get(obj);
			if (null == fieldValue) {
				continue;
			}
			Class<?> objClz = obj.getClass();
			Class<?> fieldClz = field.getClass();
			String fieldValueStr = getText(fieldValue, fieldClz);
			// 按照基本格式写入
			writeBasicType(AliasCache.getFieldName(objClz, field), fieldValueStr, transferInfo);
		}
	}

	public abstract void writeBasicType(String tagName, String value, TransferInfo transferInfo) throws Exception;
}

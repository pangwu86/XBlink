package org.xblink.core.serial.xtype;

import java.lang.reflect.Field;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;

/**
 * XAttribute，XElement与XObject格式的基类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class XBasicType implements XType {

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
			// 按照基本格式写入
			writeBasicType(obj, analysisObject, transferInfo, fieldValue,
					AliasCache.getFieldName(obj.getClass(), field), field);
		}
	}

	public abstract void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo,
			Object fieldValue, String tagName, Field field) throws Exception;

}

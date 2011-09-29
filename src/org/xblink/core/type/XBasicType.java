package org.xblink.core.type;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;

/**
 * Attribute，Element与Object格式的基类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class XBasicType extends AbstractXType {

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
					AliasCache.getFieldName(obj.getClass(), field));
		}
	}

	public abstract List<Field> getFields(AnalysisObject analysisObject);

	public abstract void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo,
			Object fieldValue, String tagName) throws Exception;

}

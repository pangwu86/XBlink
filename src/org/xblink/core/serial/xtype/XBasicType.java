package org.xblink.core.serial.xtype;

import java.lang.reflect.Field;

import org.xblink.annotation.XBlinkConverter;
import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.convert.Converter;

/**
 * XAttribute，XElement，XEnum，XCustomized，XObject格式的基类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class XBasicType implements XType {

	protected String getFieldName(Object obj, Field field) {
		return AliasCache.getFieldName(obj.getClass(), field);
	}

	protected Converter getFieldConveter(Field field) throws Exception {
		XBlinkConverter xBlinkConverter = field.getAnnotation(XBlinkConverter.class);
		Class<? extends Converter> converterClz = xBlinkConverter.value();
		return converterClz.newInstance();
	}

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
			String fieldName = getFieldName(obj, field);
			// 按照基本格式写入
			writeField(obj, analysisObject, transferInfo, field, fieldName, fieldValue);
		}
	}

	public abstract void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception;

}

package org.xblink.core.serial.xtype;

import java.lang.reflect.Field;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;

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
					AliasCache.getFieldName(obj.getClass(), field));
		}
	}

	public String getText(Object obj, Class<?> objClz) throws Exception {
		// 根据对象类型，寻找对应的转换器，完成转换操作
		Converter converter = ConverterWarehouse.searchConverterForType(objClz);
		return converter.obj2Text(obj);
	}

	public abstract void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo,
			Object fieldValue, String tagName) throws Exception;

}

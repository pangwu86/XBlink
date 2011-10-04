package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * 处理枚举类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XEnum extends XBasicType {

	public static final XEnum INSTANCE = new XEnum();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getEnumFieldTypes();
	}

	public void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Object fieldValue,
			String tagName, Field field) throws Exception {
		String fieldValueStr = ConverterWarehouse.getTextValueByData(Enum.class, fieldValue);
		transferInfo.getDocWriter().writeElementText(tagName, fieldValueStr);
	}

}

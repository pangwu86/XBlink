package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.convert.Converter;
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

	// FIXME 枚举类型处理需要再修正

	public void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception {
		String fieldValueStr = ConverterWarehouse.getTextValueByData(Enum.class, fieldValue);
		transferInfo.getDocWriter().writeElementText(fieldName, fieldValueStr);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void readField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName) throws Exception {
		Converter converter = ConverterWarehouse.searchConverterForType(Enum.class);
		// Object fieldValue = ((EnumConverter) converter)
		// .text2Obj(fieldValueStr, (Class<? extends Enum>) field.getType());
		// transferInfo.getObjectOperator().setField(obj, field, fieldValue);
	}

	public void readItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}

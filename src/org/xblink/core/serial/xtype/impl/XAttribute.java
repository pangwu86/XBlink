package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.serial.xtype.XBasicType;
import org.xblink.util.TypeUtil;

/**
 * Attribute类型。
 * 
 * XML特有的格式。其他文本格式，会以Element类型来执行。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XAttribute extends XBasicType {

	public static final XAttribute INSTANCE = new XAttribute();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getAttributeFieldTypes();
	}

	public void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception {
		// 字段上可能有自定义转换器
		String fieldValueStr;
		if (TypeUtil.isCustomizedField(field)) {
			fieldValueStr = getFieldConveter(field).obj2Text(fieldValue);
		} else {
			fieldValueStr = ConverterWarehouse.getTextValueByData(fieldValue.getClass(), fieldValue);
		}
		transferInfo.getDocWriter().writeAttribute(fieldName, fieldValueStr);
	}

	public void readItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// 除了Attribute其他都需要move
		for (Field field : getFields(analysisObject)) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			String fieldName = getFieldName(obj, field);
			String fieldValueStr = transferInfo.getDocReader().getAttribute(fieldName);
			// 空的话不进行反序列化
			if (null == fieldValueStr) {
				return;
			}
			// 按照基本格式读入
			readField(obj, analysisObject, transferInfo, field, fieldName, fieldValueStr);
		}
	}

	private void readField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, String fieldValueStr) throws Exception {
		// 字段上可能有自定义转换器
		Object fieldValue;
		if (TypeUtil.isCustomizedField(field)) {
			fieldValue = getFieldConveter(field).text2Obj(fieldValueStr);
		} else {
			fieldValue = ConverterWarehouse.getTextValueByData(field.getType(), fieldValueStr);
		}
		transferInfo.getObjectOperator().setField(obj, field, fieldValue);
	}

}

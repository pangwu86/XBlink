package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.convert.ConverterWarehouse;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * Element类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XElement extends XBasicType {

	public static final XElement INSTANCE = new XElement();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getElementFieldTypes();
	}

	public void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception {
		String fieldValueStr = ConverterWarehouse.getTextValueByData(fieldValue.getClass(), fieldValue);
		transferInfo.getDocWriter().writeElementText(fieldName, fieldValueStr);
	}

	public void readField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName) throws Exception {
		String fieldValueStr = transferInfo.getDocReader().getTextValue();
		// 空的话不进行反序列化
		if (null == fieldValueStr) {
			return;
		}
		Object fieldValue = ConverterWarehouse.getTextValueByData(field.getType(), fieldValueStr);
		transferInfo.getObjectOperator().setField(obj, field, fieldValue);
	}

	public void readItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}

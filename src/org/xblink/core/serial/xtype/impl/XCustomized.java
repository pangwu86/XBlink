package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * 使用了自定义转换器的成员。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XCustomized extends XBasicType {

	public static final XCustomized INSTANCE = new XCustomized();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getCustomizedFieldTypes();
	}

	public void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception {
		String fieldValueStr = getFieldConveter(field).obj2Text(fieldValue);
		transferInfo.getDocWriter().writeElementText(fieldName, fieldValueStr);
	}

	public void readField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, String fieldValueStr) throws Exception {
		Object fieldValue = getFieldConveter(field).text2Obj(fieldValueStr);
		transferInfo.getObjectOperator().setField(obj, field, fieldValue);
	}

	public void readItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}

package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.Deserializer;
import org.xblink.core.serial.Serializer;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * 对象类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XObject extends XBasicType {

	public static final XObject INSTANCE = new XObject();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getObjFieldTypes();
	}

	public void writeField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, Object fieldValue) throws Exception {
		Serializer.writeUnknow(fieldValue, transferInfo, fieldName);
	}

	public void readField(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Field field,
			String fieldName, String fieldValueStr) throws Exception {
		Object rootObj = transferInfo.getObjectOperator().newInstance(field.getType());
		Deserializer.readUnknow(rootObj, transferInfo, fieldName);
	}

	public void readItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}

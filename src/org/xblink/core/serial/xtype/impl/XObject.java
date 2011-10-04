package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.Serializer;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * 对象类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XObject extends XBasicType {

	public static final XObject INSTANCE = new XObject();

	public void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Object fieldValue,
			String tagName, Field field) throws Exception {
		Serializer.writeUnknow(fieldValue, transferInfo, tagName);
	}

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getObjFieldTypes();
	}

}

package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.xtype.XBasicType;

/**
 * Attribute类型。
 * 
 * XML特有的格式。其他文本格式，会以Element类型来执行。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XAttribute extends XBasicType {

	public static final XAttribute INSTANCE = new XAttribute();

	public void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Object fieldValue,
			String tagName) throws Exception {
		String fieldValueStr = getText(fieldValue, fieldValue.getClass());
		transferInfo.getDocWriter().writeAttribute(tagName, fieldValueStr);
	}

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getAttributeFieldTypes();
	}

}

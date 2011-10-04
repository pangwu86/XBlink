package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.annotation.XBlinkConverter;
import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.convert.Converter;
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

	public void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Object fieldValue,
			String tagName, Field field) throws Exception {
		String fieldValueStr = getFieldConveter(field).obj2Text(fieldValue);
		transferInfo.getDocWriter().writeElementText(tagName, fieldValueStr);
	}

	private Converter getFieldConveter(Field field) throws Exception {
		XBlinkConverter xBlinkConverter = field.getAnnotation(XBlinkConverter.class);
		Class<? extends Converter> converterClz = xBlinkConverter.value();
		return converterClz.newInstance();
	}

}

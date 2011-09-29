package org.xblink.core.type;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;

/**
 * Element类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XElement extends XBasicType {

	public static XElement INSTANCE = new XElement();

	public void writeBasicType(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo, Object fieldValue,
			String tagName) throws Exception {
		String fieldValueStr = getText(fieldValue, fieldValue.getClass());
		transferInfo.getDocWriter().writeElementText(tagName, fieldValueStr);
	}

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getElementFieldTypes();
	}
}

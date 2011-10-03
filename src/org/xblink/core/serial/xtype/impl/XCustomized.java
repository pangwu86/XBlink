package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.xtype.XType;

/**
 * 使用了自定义转换器的成员。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XCustomized implements XType {

	public List<Field> getFields(AnalysisObject analysisObject) {
		// TODO
		return null;
	}

	public void writeItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}

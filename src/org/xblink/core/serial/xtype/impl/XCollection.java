package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.Serializer;
import org.xblink.core.serial.xtype.XContainerType;

/**
 * 处理数组，List，Set类型数据。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XCollection extends XContainerType {

	public static final XCollection INSTANCE = new XCollection();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getCollectionFieldTypes();
	}

	@SuppressWarnings("rawtypes")
	public void writeOneItem(Class<?> objClz, Object obj, TransferInfo transferInfo, String collectionName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(collectionName);
		// FIXME
		Serializer.recordReferenceObject(obj, transferInfo);
		if (objClz.isArray()) {
			int length = Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				Object item = Array.get(obj, i);
				Serializer.writeUnknow(item, transferInfo, null);
			}
		} else {
			Collection collection = (Collection) obj;
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Object item = iterator.next();
				Serializer.writeUnknow(item, transferInfo, null);
			}
		}
		transferInfo.getDocWriter().writeEndTag(collectionName);
	}

}

package org.xblink.core.serial.xtype.impl;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xblink.core.AnalysisObject;
import org.xblink.core.Constant;
import org.xblink.core.TransferInfo;
import org.xblink.core.serial.Serializer;
import org.xblink.core.serial.xtype.XContainerType;

/**
 * 处理Map类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XMap extends XContainerType {

	public static final XMap INSTANCE = new XMap();

	public List<Field> getFields(AnalysisObject analysisObject) {
		return analysisObject.getMapFieldTypes();
	}

	@SuppressWarnings("rawtypes")
	public void writeOneItem(Class<?> objClz, Object obj, TransferInfo transferInfo, String collectionName)
			throws Exception {
		transferInfo.getDocWriter().writeStartTag(collectionName);
		Map map = (Map) obj;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			transferInfo.getDocWriter().writeStartTag(Constant.MAP_ENTRY);
			Serializer.writeUnknow(entry.getKey(), transferInfo, null);
			Serializer.writeUnknow(entry.getValue(), transferInfo, null);
			transferInfo.getDocWriter().writeEndTag(Constant.MAP_ENTRY);
		}
		transferInfo.getDocWriter().writeEndTag(collectionName);
	}

}

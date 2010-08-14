package org.xblink.writer;

import java.lang.reflect.Field;

import org.xblink.AnalysisedObject;
import org.xblink.Constants;
import org.xblink.XMLObject;
import org.xblink.XType;
import org.xblink.annotations.XBlinkNotSerialize;
import org.xblink.util.ClassUtil;

/**
 * XML类对象序列化操作类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLObjectWriter extends XMLObject {

	/**
	 * 对象为单位写入.
	 * 
	 * @param obj
	 * @param writer
	 * @param objectName
	 * @throws Exception
	 */
	public void write(Object obj, XMLWriterUtil writer, String objectName) throws Exception {
		// 记录解析过的对象
		AnalysisedObject analysisedObject;
		String className = obj.getClass().getName();
		analysisedObject = xmlWriteObjects.get(className);
		if (null == analysisedObject) {
			analysisedObject = new AnalysisedObject();
			analysisedObject.setXmlObject(this);
			xmlWriteObjects.put(className, analysisedObject);
			Field[] fields = obj.getClass().getDeclaredFields();
			// 将所有变量进行分类
			boolean isXBlinkClass = false;
			for (Field field : fields) {
				field.setAccessible(true);
				// 没有数据情况下不进行序列化处理
				if (null == field.get(obj)) {
					continue;
				}
				// 是否需要序列化
				XBlinkNotSerialize xNotSerialize = field.getAnnotation(XBlinkNotSerialize.class);
				if (null != xNotSerialize) {
					continue;
				}
				// 各种类型数据是否需要序列化
				init();
				for (XType xtype : xTypes) {
					if (xtype.getAnnotation(field)) {
						isXBlinkClass = true;
						break;
					}
				}
			}
			analysisedObject.setXBlinkClass(isXBlinkClass);
		}
		// XBlink序列化对象
		if (analysisedObject.isXBlinkClass()) {
			String startElement = null;
			if (objectName != null) {
				startElement = objectName;
			} else {
				startElement = ClassUtil.getClassName(obj.getClass()).toString();
			}
			writer.writeStartElement(startElement);
			// 开始进行分类处理
			for (XType xtype : analysisedObject.getXmlObject().getXTypes()) {
				if (xtype.isFieldsEmpty()) {
					continue;
				}
				xtype.writeItem(obj, writer);
			}
		} else {
			writer.writeStartElement(obj.getClass().toString()
					.replaceAll(Constants.SPACE, Constants.UNDERLINE));
			// 调用toString
			writer.writeAttribute(Constants.OBJ_VALUE, obj.toString());
		}
		writer.writeEndElement();
	}
}

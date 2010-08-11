package org.xblink.writer;

import java.lang.reflect.Field;

import org.xblink.XMLObject;
import org.xblink.XType;
import org.xblink.annotations.XBlinkNotSerializeAndUnserialize;
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
	 * @throws Exception
	 */
	public void write(Object obj, XMLWriterUtil writer) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		boolean isXBlinkClass = false;
		// 将所有变量进行分类
		for (Field field : fields) {
			field.setAccessible(true);
			// 没有数据情况下不进行序列化处理
			if (null == field.get(obj)) {
				continue;
			}
			// 是否需要序列化
			XBlinkNotSerializeAndUnserialize xNotSerialize = field
					.getAnnotation(XBlinkNotSerializeAndUnserialize.class);
			if (null != xNotSerialize) {
				continue;
			}
			// 各种类型数据是否需要序列化
			for (XType xtype : xTypes) {
				if (xtype.getAnnotation(field)) {
					isXBlinkClass = true;
					break;
				}
			}
		}
		// XBlink序列化对象
		if (isXBlinkClass) {
			writer.writeStartElement(ClassUtil.getClassName(obj).toString());
			// 开始进行分类处理
			for (XType xtype : xTypes) {
				if (xtype.isFieldsEmpty()) {
					continue;
				}
				xtype.writeItem(obj, writer);
			}
		} else {
			writer.writeStartElement(obj.getClass().toString().replaceAll(SPACE, UNDERLINE));
			// 调用toString
			writer.writeAttribute(OBJ_VALUE, obj.toString());
		}
		writer.writeEndElement();
	}
}

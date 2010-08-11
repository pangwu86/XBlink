package org.xblink.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.w3c.dom.Node;

import org.xblink.ClassLoaderSwitcher;
import org.xblink.XType;
import org.xblink.ImplClasses;
import org.xblink.XMLObject;
import org.xblink.annotations.XBlinkNotSerializeAndUnserialize;

/**
 * XML类对象反序列化操作类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLObjectReader extends XMLObject {

	/** 类加载器切换器 */
	private ClassLoaderSwitcher classLoaderSwitcher;

	/**
	 * 对象为单位读取.
	 * 
	 * @param obj
	 * @param baseNode
	 * @throws Exception
	 */
	public Object read(Object obj, Node baseNode, ImplClasses xmlImplClasses,
			ClassLoaderSwitcher clzLoaderSwitcher) throws Exception {
		this.classLoaderSwitcher = clzLoaderSwitcher;
		Field[] fields = obj.getClass().getDeclaredFields();
		// 将所有变量进行分类
		boolean isXBlinkClass = false;
		for (Field field : fields) {
			field.setAccessible(true);
			// 是否进行过序列化
			XBlinkNotSerializeAndUnserialize xNotSerialize = field
					.getAnnotation(XBlinkNotSerializeAndUnserialize.class);
			if (null != xNotSerialize) {
				continue;
			}
			for (XType xtype : xTypes) {
				if (xtype.getAnnotation(field)) {
					xtype.setClassLoaderSwitcher(classLoaderSwitcher);
					isXBlinkClass = true;
					break;
				}
			}
		}
		if (isXBlinkClass) {
			// 开始进行分类处理
			for (XType xtype : xTypes) {
				if (xtype.isFieldsEmpty()) {
					continue;
				}
				xtype.setImplClass(xmlImplClasses);
				xtype.readItem(obj, baseNode);
			}
		} else {
			Node att = baseNode.getAttributes().getNamedItem(OBJ_VALUE);
			String xPathValue = att == null ? null : att.getNodeValue();
			if (null == xPathValue || xPathValue.length() == 0) {
				obj = null;
			} else {
				if (xPathValue.startsWith(CLASS_PREFIX)) {
					obj = classLoaderSwitcher.forName(new String(xPathValue.replaceAll(
							CLASS_PREFIX, EMPTY_STRING)));
				} else {
					Constructor<?> constructor = obj.getClass()
							.getDeclaredConstructor(String.class);
					if (constructor != null) {
						obj = constructor.newInstance(xPathValue);
					}
				}
			}
		}
		return obj;
	}

}

package org.xblink.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Node;

import org.xblink.AnalysisedClass;
import org.xblink.ClassLoaderSwitcher;
import org.xblink.Constants;
import org.xblink.ReferenceObject;
import org.xblink.XType;
import org.xblink.ImplClasses;
import org.xblink.XMLObject;
import org.xblink.annotations.XBlinkNotSerialize;
import org.xblink.util.NodeUtil;

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
			ClassLoaderSwitcher clzLoaderSwitcher, Map<Integer, ReferenceObject> referenceObjects)
			throws Exception {
		// 是否是引用对象
		String refNo = NodeUtil.getAttributeValue(baseNode, Constants.OBJ_REFERENCE);
		if (null != refNo) {
			ReferenceObject refObject = referenceObjects.get(Integer.valueOf(refNo));
			obj = refObject.getRef();
			return obj;
		}
		// 记录解析过的对象
		AnalysisedClass analysisedObject;
		String className = obj.getClass().getName();
		analysisedObject = xmlReadClasses.get(className);
		if (null == analysisedObject) {
			analysisedObject = new AnalysisedClass();
			analysisedObject.setXmlObject(this);
			xmlReadClasses.put(className, analysisedObject);
			this.classLoaderSwitcher = clzLoaderSwitcher;
			Field[] fields = obj.getClass().getDeclaredFields();
			// 将所有变量进行分类
			boolean isXBlinkClass = false;
			for (Field field : fields) {
				field.setAccessible(true);
				// 是否进行过序列化
				XBlinkNotSerialize xNotSerialize = field.getAnnotation(XBlinkNotSerialize.class);
				if (null != xNotSerialize) {
					continue;
				}
				init();
				for (XType xtype : xTypes) {
					if (xtype.getAnnotation(field)) {
						xtype.setClassLoaderSwitcher(classLoaderSwitcher);
						isXBlinkClass = true;
						break;
					}
				}
			}
			analysisedObject.setXBlinkClass(isXBlinkClass);
		}
		if (analysisedObject.isXBlinkClass()) {
			// 记录该对象，保持对其引用
			ReferenceObject refObject = new ReferenceObject();
			refObject.setNo(referenceObjects.size() + 1);
			refObject.setRef(obj);
			referenceObjects.put(refObject.getNo(), refObject);
			// 开始进行分类处理
			for (XType xtype : analysisedObject.getXmlObject().getXTypes()) {
				if (xtype.isFieldsEmpty()) {
					continue;
				}
				xtype.setImplClass(xmlImplClasses);
				xtype.readItem(obj, baseNode, referenceObjects);
			}
		} else {
			Node att = baseNode.getAttributes().getNamedItem(Constants.OBJ_VALUE);
			String xPathValue = att == null ? null : att.getNodeValue();
			if (null == xPathValue || xPathValue.length() == 0) {
				obj = null;
			} else {
				if (xPathValue.startsWith(Constants.CLASS_PREFIX)) {
					obj = classLoaderSwitcher.forName(new String(xPathValue.replaceAll(
							Constants.CLASS_PREFIX, Constants.EMPTY_STRING)));
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

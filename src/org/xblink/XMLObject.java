package org.xblink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xblink.types.XArray;
import org.xblink.types.XAttribute;
import org.xblink.types.XElement;
import org.xblink.types.XList;
import org.xblink.types.XMap;
import org.xblink.types.XObject;
import org.xblink.types.XSet;

/**
 * XML类型对象集合對象.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public abstract class XMLObject {

	/** 记录所有解析过的对象 **/
	static protected HashMap<String, AnalysisedObject> xmlWriteObjects = new HashMap<String, AnalysisedObject>();

	/** 记录所有解析过的对象 **/
	static protected HashMap<String, AnalysisedObject> xmlReadObjects = new HashMap<String, AnalysisedObject>();

	/** 字段各种类型列表 */
	protected List<XType> xTypes = new ArrayList<XType>();

	/** 是否初始化过 */
	private boolean isInitialized = false;;

	/**
	 * 各种类型的对象操作类初始化.
	 */
	public void init() {
		// 防止重复初始化
		if (isInitialized) {
			return;
		}
		// 顺序为其序列化顺序
		xTypes.add(new XAttribute());
		xTypes.add(new XElement());
		xTypes.add(new XObject());
		xTypes.add(new XList());
		xTypes.add(new XSet());
		xTypes.add(new XMap());
		xTypes.add(new XArray());
		isInitialized = true;
	}

	public List<XType> getXTypes() {
		return xTypes;
	}

	public static void cleanXRoot() {
		xmlWriteObjects.remove(XRoot.class.getName());
		xmlReadObjects.remove(XRoot.class.getName());
	}
}

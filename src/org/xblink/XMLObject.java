package org.xblink;

import java.util.ArrayList;
import java.util.List;

import org.xblink.Constants;

import org.xblink.types.XArray;
import org.xblink.types.XAttribute;
import org.xblink.types.XList;
import org.xblink.types.XMap;
import org.xblink.types.XObject;
import org.xblink.types.XSet;
import org.xblink.types.XElement;

/**
 * XML类型对象集合對象.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public abstract class XMLObject {

	/** 字段各种类型列表 */
	protected List<XType> xTypes = new ArrayList<XType>();

	/**
	 * 构造函数.
	 */
	public XMLObject() {
		init();
	}

	/**
	 * 各种类型的对象操作类初始化.
	 */
	private void init() {
		// 顺序为其序列化顺序
		xTypes.add(new XAttribute());
		xTypes.add(new XElement());
		xTypes.add(new XObject());
		xTypes.add(new XList());
		xTypes.add(new XSet());
		xTypes.add(new XMap());
		xTypes.add(new XArray());
	}
}

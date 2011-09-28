package org.xblink.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.xblink.annotation.XBlinkAsAttribute;
import org.xblink.util.ReflectUtil;
import org.xblink.util.TypeUtil;

/**
 * 分析结果对象，记录的当前Class的各种序列化类型的分类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AnalysisObject {

	/** 字段集合 */
	private List<Field> attributeFieldTypes;
	private List<Field> elementFieldTypes;
	private List<Field> objFieldTypes;
	private List<Field> collectionFieldTypes;
	private List<Field> mapFieldTypes;

	public AnalysisObject(Class<?> clz) {
		analysing(clz);
	}

	public void analysing(Class<?> clz) {
		// 遍历所有字段，分门别类的存放
		for (Field field : ReflectUtil.getField(clz)) {
			Class<?> fieldClz = field.getClass();
			if (TypeUtil.isBasicType(fieldClz)) {
				// 基本类型可以以Attribute的方式展现（目前仅限XML格式）
				boolean isAttributeNode = null != field.getAnnotation(XBlinkAsAttribute.class);
				if (isAttributeNode) {
					add2Attribute(field);
				} else {
					add2Element(field);
				}
			} else if (TypeUtil.isCollectionType(fieldClz)) {
				add2Collection(field);
			} else if (TypeUtil.isMapType(fieldClz)) {
				add2Map(field);
			} else {
				add2Obj(field);
			}
		}
	}

	// ********* add **********

	private void add2Obj(Field field) {
		add2Container(field, objFieldTypes);
	}

	private void add2Map(Field field) {
		add2Container(field, mapFieldTypes);
	}

	private void add2Collection(Field field) {
		add2Container(field, collectionFieldTypes);
	}

	private void add2Element(Field field) {
		add2Container(field, elementFieldTypes);
	}

	private void add2Attribute(Field field) {
		add2Container(field, attributeFieldTypes);
	}

	private void add2Container(Field field, List<Field> container) {
		if (null == container) {
			container = new ArrayList<Field>();
		}
		container.add(field);
	}

	// *********** 提供给外面的信息 *************

	// ************ isEmpty ***************

	public boolean attributeIsEmpty() {
		return containerIsEmpty(attributeFieldTypes);
	}

	public boolean elementIsEmpty() {
		return containerIsEmpty(elementFieldTypes);
	}

	public boolean objIsEmpty() {
		return containerIsEmpty(objFieldTypes);
	}

	public boolean collectionIsEmpty() {
		return containerIsEmpty(collectionFieldTypes);
	}

	public boolean mapIsEmpty() {
		return containerIsEmpty(mapFieldTypes);
	}

	private boolean containerIsEmpty(List<Field> container) {
		return null == container || container.isEmpty();
	}

	// *************** get ********************
	public List<Field> getAttributeFieldTypes() {
		return attributeFieldTypes;
	}

	public List<Field> getElementFieldTypes() {
		return elementFieldTypes;
	}

	public List<Field> getObjFieldTypes() {
		return objFieldTypes;
	}

	public List<Field> getCollectionFieldTypes() {
		return collectionFieldTypes;
	}

}

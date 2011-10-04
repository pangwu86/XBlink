package org.xblink.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.xblink.annotation.XBlinkAsAttribute;
import org.xblink.annotation.XBlinkOmitField;
import org.xblink.util.ReflectUtil;
import org.xblink.util.TypeUtil;

/**
 * 分析结果对象，记录的当前Class的各种序列化类型的分类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AnalysisObject {

	// TODO 简化字段

	/** 字段集合 */
	private List<Field> attributeFieldTypes;
	private List<Field> elementFieldTypes;
	private List<Field> enumFieldTypes;
	private List<Field> customizedFieldTypes;
	private List<Field> objFieldTypes;
	private List<Field> collectionFieldTypes;
	private List<Field> mapFieldTypes;

	public AnalysisObject(Class<?> clz) {
		analysing(clz);
	}

	public void analysing(Class<?> clz) {
		// 遍历所有字段，分门别类的存放
		for (Field field : ReflectUtil.getField(clz)) {
			// 先判断该字段是否要忽略
			if (null != field.getAnnotation(XBlinkOmitField.class)) {
				continue;
			}
			Class<?> fieldClz = field.getType();
			if (TypeUtil.isCustomizedTypeField(field)) {
				// 自定义转换器优先
				add2Customized(field);
			} else if (TypeUtil.isSingleValueType(fieldClz)) {
				// 基本类型可以以Attribute的方式展现（目前仅限XML格式）
				boolean isAttributeNode = null != field.getAnnotation(XBlinkAsAttribute.class);
				if (isAttributeNode) {
					add2Attribute(field);
				} else {
					add2Element(field);
				}
			} else if (TypeUtil.isEnum(fieldClz)) {
				add2Enum(field);
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
		if (null == objFieldTypes) {
			objFieldTypes = new ArrayList<Field>();
		}
		objFieldTypes.add(field);
	}

	private void add2Map(Field field) {
		if (null == mapFieldTypes) {
			mapFieldTypes = new ArrayList<Field>();
		}
		mapFieldTypes.add(field);
	}

	private void add2Collection(Field field) {
		if (null == collectionFieldTypes) {
			collectionFieldTypes = new ArrayList<Field>();
		}
		collectionFieldTypes.add(field);
	}

	private void add2Element(Field field) {
		if (null == elementFieldTypes) {
			elementFieldTypes = new ArrayList<Field>();
		}
		elementFieldTypes.add(field);
	}

	private void add2Enum(Field field) {
		if (null == enumFieldTypes) {
			enumFieldTypes = new ArrayList<Field>();
		}
		enumFieldTypes.add(field);
	}

	private void add2Customized(Field field) {
		if (null == customizedFieldTypes) {
			customizedFieldTypes = new ArrayList<Field>();
		}
		customizedFieldTypes.add(field);
	}

	private void add2Attribute(Field field) {
		if (null == attributeFieldTypes) {
			attributeFieldTypes = new ArrayList<Field>();
		}
		attributeFieldTypes.add(field);

	}

	// *********** 提供给外面的信息 *************

	// ************ isEmpty ***************

	public boolean attributeIsEmpty() {
		return containerIsEmpty(attributeFieldTypes);
	}

	public boolean elementIsEmpty() {
		return containerIsEmpty(elementFieldTypes);
	}

	public boolean enumIsEmpty() {
		return containerIsEmpty(enumFieldTypes);
	}

	public boolean customizedIsEmpty() {
		return containerIsEmpty(customizedFieldTypes);
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

	public List<Field> getEnumFieldTypes() {
		return enumFieldTypes;
	}

	public List<Field> getCustomizedFieldTypes() {
		return customizedFieldTypes;
	}

	public List<Field> getObjFieldTypes() {
		return objFieldTypes;
	}

	public List<Field> getCollectionFieldTypes() {
		return collectionFieldTypes;
	}

	public List<Field> getMapFieldTypes() {
		return mapFieldTypes;
	}

}

package org.xblink.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.xblink.annotation.XBlinkConverter;
import org.xblink.annotation.XBlinkOmitField;
import org.xblink.core.convert.Converter;
import org.xblink.core.reflect.ObjectOperator;
import org.xblink.core.reflect.ObjectOperatorFactory;
import org.xblink.util.TypeUtil;

/**
 * 分析结果对象，记录的当前Class的各种序列化类型的分类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class AnalysisObject {

	private static ObjectOperator objectOperator = ObjectOperatorFactory.createObjectOperator();
	private static Map<Field, Converter> customizedFieldTypes = new ConcurrentHashMap<Field, Converter>();

	/** 字段集合 */
	private List<Field> attributeFieldTypes;
	private List<Field> otherFieldTypes;

	public AnalysisObject(Class<?> clz, boolean ignoreTransient) {
		analysing(clz, ignoreTransient);
	}

	private void analysing(Class<?> clz, boolean ignoreTransient) {
		// 遍历所有字段，分门别类的存放
		for (Field field : clz.getDeclaredFields()) {
			// 先判断该字段是否要忽略
			if (null != field.getAnnotation(XBlinkOmitField.class)) {
				continue;
			}
			// 判断transient类型的是否需要序列化
			if (Modifier.isTransient(field.getModifiers()) && ignoreTransient) {
				continue;
			}
			// 设置可访问行
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			Class<?> fieldClz = field.getType();
			if (TypeUtil.isCustomizedField(field)) {
				// 自定义转换器优先
				if (TypeUtil.isAttributeField(field)) {
					// 比较特殊的类型，同时添加了两个注解，这就需要以Attribute类型进行处理了
					add2Attribute(field);
				} else {
					record2Customized(field);
					add2Other(field);
				}
			} else if (TypeUtil.isSingleValueType(fieldClz)) {
				// 基本类型可以以Attribute的方式展现（目前仅限XML格式）
				if (TypeUtil.isAttributeField(field)) {
					add2Attribute(field);
				} else {
					add2Other(field);
				}
			} else {
				add2Other(field);
			}
		}
	}

	private Converter createFieldConverter(Field field) {
		return (Converter) objectOperator.newInstance(field.getAnnotation(XBlinkConverter.class).value());
	}

	private void record2Customized(Field field) {
		if (null == customizedFieldTypes) {
			customizedFieldTypes = new HashMap<Field, Converter>();
		}
		customizedFieldTypes.put(field, createFieldConverter(field));
	}

	// ********* add **********

	private void add2Attribute(Field field) {
		if (null == attributeFieldTypes) {
			attributeFieldTypes = new ArrayList<Field>();
		}
		attributeFieldTypes.add(field);
	}

	private void add2Other(Field field) {
		if (null == otherFieldTypes) {
			otherFieldTypes = new ArrayList<Field>();
		}
		otherFieldTypes.add(field);
	}

	// *********** 提供给外面的信息 *************

	// ************ isEmpty ***************

	public boolean attributeIsEmpty() {
		return null == attributeFieldTypes || attributeFieldTypes.isEmpty();
	}

	public boolean otherIsEmpty() {
		return null == otherFieldTypes || otherFieldTypes.isEmpty();
	}

	// *************** get ********************

	public List<Field> getAttributeFieldTypes() {
		return attributeFieldTypes;
	}

	public List<Field> getOtherFieldTypes() {
		return otherFieldTypes;
	}

	/**
	 * 根据Field获取对应的自定义转换器
	 * 
	 * @param field
	 * @return
	 */
	public Converter getFieldConverter(Field field) {
		return customizedFieldTypes.get(field);
	}

	/**
	 * 判断Field是否有对应的自定义转换器
	 * 
	 * @param field
	 * @return
	 */
	public boolean isFieldHasConverter(Field field) {
		return customizedFieldTypes.containsKey(field);
	}

}

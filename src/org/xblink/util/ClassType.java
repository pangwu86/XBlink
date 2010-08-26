package org.xblink.util;

import java.lang.reflect.Type;

/**
 * Field的内部类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ClassType {

	Type fieldInnerClassType;

	Class<?> fieldInnerClass;

	Class<?> fieldClass;

	public Type getFieldInnerClassType() {
		return fieldInnerClassType;
	}

	public void setFieldInnerClassType(Type fieldInnerClassType) {
		this.fieldInnerClassType = fieldInnerClassType;
	}

	public Class<?> getFieldInnerClass() {
		return fieldInnerClass;
	}

	public void setFieldInnerClass(Class<?> fieldInnerClass) {
		this.fieldInnerClass = fieldInnerClass;
	}

	public Class<?> getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}
}

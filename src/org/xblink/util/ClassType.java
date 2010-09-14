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

	Type fieldInnerClassType1;

	Type fieldInnerClassType2;

	Class<?> fieldInnerClass1;

	Class<?> fieldInnerClass2;

	Class<?> fieldClass;

	public Type getFieldInnerClassType1() {
		return fieldInnerClassType1;
	}

	public void setFieldInnerClassType1(Type fieldInnerClassType) {
		this.fieldInnerClassType1 = fieldInnerClassType;
	}

	public Class<?> getFieldInnerClass1() {
		return fieldInnerClass1;
	}

	public void setFieldInnerClass1(Class<?> fieldInnerClass) {
		this.fieldInnerClass1 = fieldInnerClass;
	}

	public Type getFieldInnerClassType2() {
		return fieldInnerClassType2;
	}

	public void setFieldInnerClassType2(Type fieldInnerClassType2) {
		this.fieldInnerClassType2 = fieldInnerClassType2;
	}

	public Class<?> getFieldInnerClass2() {
		return fieldInnerClass2;
	}

	public void setFieldInnerClass2(Class<?> fieldInnerClass2) {
		this.fieldInnerClass2 = fieldInnerClass2;
	}

	public Class<?> getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}
}

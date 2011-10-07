package org.xblink.core;

import java.lang.reflect.Field;

/**
 * 记录反序列化时未完成的赋值操作。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class UnfinishedSetField {

	private Object obj;

	private Field field;

	private String objPath;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getObjPath() {
		return objPath;
	}

	public void setObjPath(String objPath) {
		this.objPath = objPath;
	}

}

package org.xblink;

import java.util.HashMap;

/**
 * 提供各种接口的实现类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ImplClasses {

	/** 与传入的参数保存同步更新 */
	private Class<?> newInstanceClass;
	private Class<?>[] implClasses;
	private int implClassesLength;
	private HashMap<Class<?>, Class<?>> implClassesMap = new HashMap<Class<?>, Class<?>>();

	public Class<?> getNewInstanceClass() {
		return newInstanceClass;
	}

	public void setNewInstanceClass(Class<?> newInstanceClass) {
		this.newInstanceClass = newInstanceClass;
	}

	public Class<?>[] getImplClasses() {
		return implClasses;
	}

	public void setImplClasses(Class<?>[] implClasses) {
		this.implClasses = implClasses;
	}

	public int getImplClassesLength() {
		return implClassesLength;
	}

	public void setImplClassesLength(int implClassesLength) {
		this.implClassesLength = implClassesLength;
	}

	public HashMap<Class<?>, Class<?>> getImplClassesMap() {
		return implClassesMap;
	}

	public void setImplClassesMap(HashMap<Class<?>, Class<?>> implClassesMap) {
		this.implClassesMap = implClassesMap;
	}

}

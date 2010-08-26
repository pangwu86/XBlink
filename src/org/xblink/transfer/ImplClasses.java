package org.xblink.transfer;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供各种接口的实现类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ImplClasses {

	/** 最外层实例化对象的类型 */
	private Class<?> newInstanceClass;

	/** 接口实现类集合 */
	private Class<?>[] implClasses;

	/** 实现类数量 */
	private int implClassesNumber;

	/** 记录已经匹配的接口和实现类 */
	private Map<Class<?>, Class<?>> implClassesMap = new HashMap<Class<?>, Class<?>>();

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

	public int getImplClassesNumber() {
		return implClassesNumber;
	}

	public void setImplClassesNumber(int implClassesNumber) {
		this.implClassesNumber = implClassesNumber;
	}

	public Map<Class<?>, Class<?>> getImplClassesMap() {
		return implClassesMap;
	}

	public void setImplClassesMap(Map<Class<?>, Class<?>> implClassesMap) {
		this.implClassesMap = implClassesMap;
	}

}

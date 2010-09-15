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

	/** 是否是最外层实例化对象的类型1 */
	private boolean isRootInstanceClass1 = true;

	/** 最外层实例化对象的类型1 */
	private Class<?> rootInstanceClass1;

	/** 最外层实例化对象的类型2 (Map使用) */
	private Class<?> rootInstanceClass2;

	/** 接口实现类集合 */
	private Class<?>[] implClasses;

	/** 实现类数量 */
	private int implClassesNumber;

	/** 记录已经匹配的接口和实现类 */
	private Map<Class<?>, Class<?>> implClassesMap = new HashMap<Class<?>, Class<?>>();

	public Class<?> getRootInstanceClass1() {
		return rootInstanceClass1;
	}

	public void setRootInstanceClass1(Class<?> newInstanceClass) {
		this.rootInstanceClass1 = newInstanceClass;
	}

	public Class<?> getRootInstanceClass2() {
		return rootInstanceClass2;
	}

	public void setRootInstanceClass2(Class<?> newInstanceClass) {
		this.rootInstanceClass2 = newInstanceClass;
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

	/**
	 * 
	 */
	public Class<?> getRootInstanceClass() {
		if (isRootInstanceClass1) {
			isRootInstanceClass1 = false;
			return rootInstanceClass1;
		} else {
			isRootInstanceClass1 = true;
			return rootInstanceClass2;
		}
	}

}

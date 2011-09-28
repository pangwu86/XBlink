package org.xblink.core;

/**
 * 记录对象的相对路径及其本身。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ReferenceObject {

	/** 引用对象 */
	private Object object;

	/** 字符数组形式的绝对路径 */
	private String[] path;

	public ReferenceObject(Object object, String[] path) {
		this.object = object;
		this.path = path;
	}

	public Object getObject() {
		return object;
	}

	public String[] getPath() {
		return path;
	}

}

package org.xblink.core;

/**
 * 反序列化器，也是对象的构造器。<BR>
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class Deserializer {

	private Object obj = null;

	private Class<?> clz = null;

	/**
	 * 构造器，放入需要分析的参考对象或参考类。
	 * 
	 * @param obj
	 */
	public Deserializer(Object obj, Class<?> clz) {
		this.obj = obj;
		this.clz = clz;
	}

}

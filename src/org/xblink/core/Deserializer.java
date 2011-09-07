package org.xblink.core;

/**
 * 反序列化器，也是对象的构造器。<BR>
 * 
 * 功能就是解析文本，根据其属性生成对应的变量。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class Deserializer {

	private Object obj = null;

	/**
	 * 构造器，放入需要分析的对象。
	 * 
	 * @param obj
	 */
	public Deserializer(Object obj) {
		this.obj = obj;
	}

}

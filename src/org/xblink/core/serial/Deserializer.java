package org.xblink.core.serial;

/**
 * 分析参考类或参考对象，根据分析结果来解析文本，组成Java对象。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class Deserializer {

	private Object obj = null;

	private Class<?> clz = null;

	/**
	 * 构造器，放入需要分析的参考对象或参考类。
	 * 
	 * @param obj
	 *            参考对象
	 * @param clz
	 *            参考类
	 */
	public Deserializer(Object obj, Class<?> clz) {
		this.obj = obj;
		this.clz = clz;
	}

}

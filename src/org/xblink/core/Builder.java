package org.xblink.core;

/**
 * 构建者。<BR>
 * 
 * 解析对象，生成文件。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class Builder {

	private Object obj = null;

	/**
	 * 构造器，放入需要分析的对象。
	 * 
	 * @param obj
	 */
	public Builder(Object obj) {
		this.obj = obj;
	}

	/**
	 * 获得当前对象的Class。
	 * 
	 * @return
	 */
	public Class<?> getObjClass() {
		return obj.getClass();
	}

	/**
	 * 分析Class，根据其中的XBlink主键，生成分析结果，并缓存。
	 * 
	 * @param clz
	 */
	public void analysis() {
		// XXX 分析Class对象
	}

	/**
	 * 基础类型序列化结果。<BR>
	 * 
	 * 八种基础类型，外加各种JAVA中常见类型，例如String，date等。
	 * 
	 * @return
	 */
	public abstract String getPrimitiveTypeStr();

	/**
	 * 对象类型序列化结果。<BR>
	 * 
	 * 自定义对象，对象也是有各种基本类型，对象类型的字段组成。
	 * 
	 * @return
	 */
	public abstract String getObjectTypeStr();

	/**
	 * 数组类型序列化结果。<BR>
	 * 
	 * 数组类型，基本类型数组与对象数组。
	 * 
	 * @return
	 */
	public abstract String getArrayTypeStr();

	/**
	 * 集合类型序列化结果。<BR>
	 * 
	 * List，Set三种类型。
	 * 
	 * @return
	 */
	public abstract String getCollectionTypeStr();

	/**
	 * Map类型序列化结果。<BR>
	 * 
	 * Map<K,V>形式比较复杂，需要单独处理。
	 * 
	 * @return
	 */
	public abstract String getMapTypeStr();

	/**
	 * 枚举类型序列化结果。<BR>
	 * 
	 * Enum类型。
	 * 
	 * @return
	 */
	public abstract String getEnumTypeStr();

}

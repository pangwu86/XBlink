package org.xblink.core.reflect;

import java.lang.reflect.Field;

/**
 * 根据提供的类，生成对应的实例。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface ObjectOperator {

	/**
	 * 获得一个指定Class的实例对象。
	 * 
	 * @param clz
	 *            指定的Class
	 * @return 对象
	 */
	public Object newInstance(Class<?> clz);

	/**
	 * 将对应的值放入对象中对应的字段中。
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @param fieldValue
	 *            字段值
	 */
	public void setField(Object obj, Field field, Object fieldValue);

}

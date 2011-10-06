package org.xblink.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 一个类(对象)操作器，提供各种基于反射的方法。
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
	 * 将对应的值放入对象中对应的字段中。(值为Null的话，不会赋值)
	 * 
	 * @param obj
	 *            对象
	 * @param field
	 *            字段
	 * @param fieldValue
	 *            字段值
	 */
	public void setField(Object obj, Field field, Object fieldValue);

	/**
	 * 添加一个接口与其默认实现。
	 * 
	 * @param type
	 *            接口类
	 * @param defaultImpl
	 *            默认实现类
	 */
	public void addImpl(Class<?> type, Class<?> defaultImpl);

	/**
	 * 通过一个接口获得其实现。
	 * 
	 * @param type
	 *            接口类
	 * @return 接口的实现类
	 */
	public Class<?> getImpl(Class<?> type);

	/**
	 * 获得Collection类的泛型类型。
	 * 
	 * @param gtype
	 *            集合类
	 * @return 泛型类型
	 */
	public Class<?> getCollectionGenericType(Type gtype);

	/**
	 * 获得Map类Key的泛型类型。
	 * 
	 * @param gtype
	 *            map类
	 * @return 泛型类型
	 */
	public Class<?> getMapKeyGenericType(Type gtype);

	/**
	 * 获得Map类Value的泛型类型。
	 * 
	 * @param mapClz
	 *            map类
	 * @return 泛型类型
	 */
	public Class<?> getMapValueGenericType(Type gtype);

}

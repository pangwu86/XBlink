package org.xblink;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.xblink.transfer.TransferInfo;
import org.xblink.writer.XMLWriterHelper;
import org.xblink.xml.XMLNode;

/**
 * 
 * 属性类型抽象类.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public abstract class XType {

	/** 字段集合 */
	protected List<Field> fieldTypes = new ArrayList<Field>();

	/**
	 * 获得该变量的注解类型.
	 * 
	 * @param field
	 * @return 是否成功
	 */
	public abstract boolean getAnnotation(Field field);

	/**
	 * 写项目.
	 * 
	 * @param obj
	 * @param writer
	 * @param transferInfo
	 * @throws Exception
	 */
	public abstract void writeItem(Object obj, XMLWriterHelper writer, TransferInfo transferInfo)
			throws Exception;

	/**
	 * 读项目.
	 * 
	 * @param obj
	 * @param baseNode
	 * @param transferInfo
	 * @throws Exception
	 */
	public abstract void readItem(Object obj, XMLNode baseNode, TransferInfo transferInfo)
			throws Exception;

	/**
	 * 判断字段集合是否为空.
	 * 
	 * @return 是否是空
	 */
	public boolean isFieldsEmpty() {
		if (fieldTypes.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 该字段是否为空.
	 * 
	 * @param field
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public boolean isFieldEmpty(Field field, Object obj) throws IllegalArgumentException,
			IllegalAccessException {
		// 没有数据情况下不进行序列化处理
		if (null == field.get(obj)) {
			return true;
		}
		return false;
	}

}

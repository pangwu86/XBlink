package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * Null类型转换器。这个比较特殊，不属于常规使用范围。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class NullConverter extends SingleValueTypeConverter {

	private static String NULL_STRING = "Null";

	public boolean canConvert(String text) {
		return NULL_STRING.equals(text);
	}

	public Class<?>[] getTypes() {
		return new Class<?>[] {};
	}

	public boolean canConvert(Class<?> type) {
		return true;
	}

	public String obj2Text(Object obj) throws Exception {
		return NULL_STRING;
	}

	public Object text2Obj(String text) throws Exception {
		return null;
	}

}

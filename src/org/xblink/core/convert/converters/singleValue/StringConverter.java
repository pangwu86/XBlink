package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * JAVA中的String类型，一个简单的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class StringConverter extends SingleValueTypeConverter {

	public boolean canConvert(Class<?> type) {
		return type == String.class;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Class<?>[] getTypes() {
		return new Class<?>[] { String.class };
	}

	public Object text2Obj(String text) throws Exception {
		return text;
	}
}

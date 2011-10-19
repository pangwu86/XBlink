package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * int与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class IntegerConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Integer.class, int.class };
	}

	public boolean canConvert(Class<?> type) {
		return int.class == type || Integer.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Integer.valueOf(text);
	}

}

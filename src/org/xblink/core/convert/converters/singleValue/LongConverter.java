package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * long与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class LongConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Long.class, long.class };
	}

	public boolean canConvert(Class<?> type) {
		return long.class == type || Long.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Long.valueOf(text);
	}

}

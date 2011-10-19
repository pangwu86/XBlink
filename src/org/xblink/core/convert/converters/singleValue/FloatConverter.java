package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * float与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class FloatConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { float.class, Float.class };
	}

	public boolean canConvert(Class<?> type) {
		return float.class == type || Float.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Float.valueOf(text);
	}

}

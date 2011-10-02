package org.xblink.core.convert.converters;

import org.xblink.core.convert.Converter;

/**
 * boolean与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class BooleanConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Boolean.class, boolean.class };
	}

	public boolean canConvert(Class<?> type) {
		return boolean.class == type || Boolean.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Boolean.valueOf(text);
	}

}

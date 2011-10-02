package org.xblink.core.convert.converters;

import org.xblink.core.convert.Converter;

/**
 * StringBuilder的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class StringBuilderConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { StringBuilder.class };
	}

	public boolean canConvert(Class<?> type) {
		return StringBuilder.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return new StringBuilder(text);
	}

}

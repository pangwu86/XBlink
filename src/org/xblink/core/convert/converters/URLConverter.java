package org.xblink.core.convert.converters;

import java.net.URL;

import org.xblink.core.convert.Converter;

/**
 * URL转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class URLConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { URL.class };
	}

	public boolean canConvert(Class<?> type) {
		return URL.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return new URL(text);
	}

}
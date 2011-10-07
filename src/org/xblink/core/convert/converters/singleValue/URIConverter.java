package org.xblink.core.convert.converters.singleValue;

import java.net.URI;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * URI转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class URIConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { URI.class };
	}

	public boolean canConvert(Class<?> type) {
		return URI.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return new URI(text);
	}

}
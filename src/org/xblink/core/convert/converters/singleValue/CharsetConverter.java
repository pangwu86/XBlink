package org.xblink.core.convert.converters.singleValue;

import java.nio.charset.Charset;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * Charset转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class CharsetConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Charset.class };
	}

	public boolean canConvert(Class<?> type) {
		return Charset.class.isAssignableFrom(type);
	}

	public String obj2Text(Object obj) throws Exception {
		return obj == null ? null : ((Charset) obj).name();
	}

	public Object text2Obj(String text) throws Exception {
		return Charset.forName(text);
	}

}

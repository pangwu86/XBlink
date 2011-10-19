package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * StringBuffer的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class StringBufferConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { StringBuffer.class };
	}

	public boolean canConvert(Class<?> type) {
		return StringBuffer.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return new StringBuffer(text);
	}

}

package org.xblink.core.convert.converters;

import org.xblink.core.convert.Converter;

/**
 * byte与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ByteConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Byte.class, byte.class };
	}

	public boolean canConvert(Class<?> type) {
		return byte.class == type || Byte.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Byte.valueOf(text);
	}

}
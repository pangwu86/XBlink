package org.xblink.core.convert.converters;

import java.util.UUID;

import org.xblink.core.convert.Converter;

/**
 * UUID类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class UUIDConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { UUID.class };
	}

	public boolean canConvert(Class<?> type) {
		return UUID.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return UUID.fromString(text);
	}

}

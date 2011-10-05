package org.xblink.core.convert.converters;

import org.xblink.core.convert.Converter;

import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 * Null类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class NullConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Null.class };
	}

	public boolean canConvert(Class<?> type) {
		return type == Null.class;
	}

	public String obj2Text(Object obj) throws Exception {
		return "Null";
	}

	public Object text2Obj(String text) throws Exception {
		return null;
	}

}

package org.xblink.core.convert.converters;

import java.lang.reflect.Type;

import org.xblink.core.convert.Converter;

/**
 * Class,Type类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class ClassConverter implements Converter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Class.class, Type.class };
	}

	public boolean canConvert(Class<?> type) {
		return Class.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return ((Class<?>) obj).getName();
	}

	public Object text2Obj(String text) throws Exception {
		return Class.forName(text);
	}

}

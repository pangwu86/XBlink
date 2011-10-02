package org.xblink.core.convert.converters;

import org.xblink.core.convert.Converter;
import org.xblink.util.TypeUtil;

/**
 * 普通的枚举类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class EnumConverter implements Converter {

	public boolean canConvert(Class<?> type) {
		return TypeUtil.isEnum(type);
	}

	@SuppressWarnings("rawtypes")
	public String obj2Text(Object obj) throws Exception {
		Enum en = (Enum) obj;
		return en.name();
	}

	public Class<?>[] getTypes() {
		return new Class<?>[] { Enum.class };
	}

	public Object text2Obj(String text) throws Exception {
		// FIXME 这里的Class怎么得到来？
		// return Enum.valueOf(enumType, text);
		throw new UnsupportedOperationException();
	}
}

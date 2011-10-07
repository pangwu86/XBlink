package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;
import org.xblink.util.TypeUtil;

/**
 * 普通的枚举类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class EnumConverter extends SingleValueTypeConverter {

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
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object text2Obj(String text, Class<?> enumType) throws Exception {
		return Enum.valueOf((Class<? extends Enum>) enumType, text);
	}
}

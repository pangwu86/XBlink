package org.xblink.core.convert.converters.singleValue;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * char与其包装类转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class CharacterConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { Character.class, char.class };
	}

	public boolean canConvert(Class<?> type) {
		return char.class == type || Character.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return Character.valueOf(text.charAt(0));
	}

}

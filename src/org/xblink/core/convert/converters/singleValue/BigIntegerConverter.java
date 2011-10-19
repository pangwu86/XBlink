package org.xblink.core.convert.converters.singleValue;

import java.math.BigInteger;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * BigInteger转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class BigIntegerConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { BigInteger.class };
	}

	public boolean canConvert(Class<?> type) {
		return BigInteger.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return obj.toString();
	}

	public Object text2Obj(String text) throws Exception {
		return new BigInteger(text);
	}

}

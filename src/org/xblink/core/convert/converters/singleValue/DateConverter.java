package org.xblink.core.convert.converters.singleValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * java.util.Date类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class DateConverter extends SingleValueTypeConverter {

	private static final String DATA_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

	public Class<?>[] getTypes() {
		return new Class<?>[] { Date.class };
	}

	public boolean canConvert(Class<?> type) {
		return Date.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_TEMPLATE);
		return dateFormat.format(obj);
	}

	public Object text2Obj(String text) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_TEMPLATE);
		return dateFormat.parseObject(text);
	}

}

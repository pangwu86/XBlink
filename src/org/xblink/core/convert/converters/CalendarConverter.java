package org.xblink.core.convert.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xblink.core.convert.Converter;

/**
 * GregorianCalendar与Calendar的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class CalendarConverter implements Converter {

	private static DateConverter dateConverter = new DateConverter();

	public Class<?>[] getTypes() {
		return new Class<?>[] { GregorianCalendar.class, Calendar.class };
	}

	public boolean canConvert(Class<?> type) {
		return false;
	}

	public String obj2Text(Object obj) throws Exception {
		Calendar calendar = (Calendar) obj;
		return dateConverter.obj2Text(calendar.getTime());
	}

	public Object text2Obj(String text) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) dateConverter.text2Obj(text));
		return calendar;
	}

}

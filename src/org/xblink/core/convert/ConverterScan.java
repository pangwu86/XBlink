package org.xblink.core.convert;

import java.util.ArrayList;
import java.util.List;

import org.xblink.core.convert.converters.singleValue.BigDecimalConverter;
import org.xblink.core.convert.converters.singleValue.BigIntegerConverter;
import org.xblink.core.convert.converters.singleValue.BooleanConverter;
import org.xblink.core.convert.converters.singleValue.ByteConverter;
import org.xblink.core.convert.converters.singleValue.CalendarConverter;
import org.xblink.core.convert.converters.singleValue.CharacterConverter;
import org.xblink.core.convert.converters.singleValue.CharsetConverter;
import org.xblink.core.convert.converters.singleValue.ClassConverter;
import org.xblink.core.convert.converters.singleValue.DateConverter;
import org.xblink.core.convert.converters.singleValue.DoubleConverter;
import org.xblink.core.convert.converters.singleValue.EnumConverter;
import org.xblink.core.convert.converters.singleValue.FileConverter;
import org.xblink.core.convert.converters.singleValue.FloatConverter;
import org.xblink.core.convert.converters.singleValue.IntegerConverter;
import org.xblink.core.convert.converters.singleValue.LongConverter;
import org.xblink.core.convert.converters.singleValue.NullConverter;
import org.xblink.core.convert.converters.singleValue.ShortConverter;
import org.xblink.core.convert.converters.singleValue.StringBufferConverter;
import org.xblink.core.convert.converters.singleValue.StringBuilderConverter;
import org.xblink.core.convert.converters.singleValue.StringConverter;
import org.xblink.core.convert.converters.singleValue.URIConverter;
import org.xblink.core.convert.converters.singleValue.URLConverter;
import org.xblink.core.convert.converters.singleValue.UUIDConverter;

/**
 * 自动扫描converters包下的所有转换器。
 * 
 * TODO 这里等下一个版本再进行改进吧
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ConverterScan {

	@SuppressWarnings("unused")
	private static final String CONVERT_PACKAGE = "org.xblink.core.convert.converters";

	private static final List<Class<?>> converterList;

	static {
		converterList = new ArrayList<Class<?>>();
		// TODO 当前版本扫描未实现
		// Scans.scanPackage(CONVERT_PACKAGE);
		// 八种基本类型
		converterList.add(IntegerConverter.class);
		converterList.add(ShortConverter.class);
		converterList.add(ByteConverter.class);
		converterList.add(LongConverter.class);
		converterList.add(FloatConverter.class);
		converterList.add(DoubleConverter.class);
		converterList.add(BooleanConverter.class);
		converterList.add(CharacterConverter.class);
		// JAVA中常见类型
		converterList.add(ClassConverter.class);
		converterList.add(NullConverter.class);
		converterList.add(CharsetConverter.class);
		converterList.add(EnumConverter.class);
		converterList.add(FileConverter.class);
		converterList.add(StringConverter.class);
		converterList.add(StringBuilderConverter.class);
		converterList.add(StringBufferConverter.class);
		converterList.add(DateConverter.class);
		converterList.add(CalendarConverter.class);
		converterList.add(BigDecimalConverter.class);
		converterList.add(BigIntegerConverter.class);
		converterList.add(URLConverter.class);
		converterList.add(URIConverter.class);
		converterList.add(UUIDConverter.class);
	}

	/**
	 * 扫描转换器。(默认包下的所有转换器类)
	 * 
	 * @return
	 */
	public static List<Class<?>> scanConverter() {
		return converterList;
	}

}

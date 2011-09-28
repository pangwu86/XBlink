package org.xblink.core.convert;

import java.util.HashMap;
import java.util.Map;

import org.xblink.core.convert.converters.ReflectConverter;
import org.xblink.core.convert.converters.StringConverter;

/**
 * 转换器仓库，根据Class类型，返回对应的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ConverterWarehouse {

	// TODO 这里有两个问题
	// 一是有线程安全的问题，有可能多个线程拿到同一个Converter并进行调用
	// 二是如果不添加缓存，生成Converter的开销会有多大
	private static Map<Class<?>, Converter> conMap = new HashMap<Class<?>, Converter>();

	/** 是否用在多线程环境中 */
	private static boolean useInMultiThreaded = false;

	private ConverterWarehouse() {
	}

	/**
	 * 一个全局性开关，是否在多线程环境下运行。
	 * 
	 * @param useInMultiThreaded
	 *            开关
	 */
	public static void useInMultiThreaded(boolean useInMultiThreaded) {
		ConverterWarehouse.useInMultiThreaded = useInMultiThreaded;
	}

	/**
	 * 寻获对应转换器。
	 * 
	 * @param clz
	 *            类
	 * @return 转换器
	 */
	public static Converter searchConverterForType(Class<?> clz) {
		Converter converter = conMap.get(clz);
		if (null == converter) {
			converter = lookForConverterForType(clz);
			conMap.put(clz, converter);
		}
		return converter;
	}

	private static Converter lookForConverterForType(Class<?> clz) {
		if (clz == String.class) {
			return StringConverter.INSTANCE;
		}
		return ReflectConverter.INSTANCE;
	}

}

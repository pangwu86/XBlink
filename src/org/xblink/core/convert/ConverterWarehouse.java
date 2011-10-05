package org.xblink.core.convert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.util.TypeUtil;

/**
 * 转换器仓库，根据Class类型，返回对应的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ConverterWarehouse {

	private ConverterWarehouse() {
	}

	// TODO 这里有两个问题
	// 一是有线程安全的问题，有可能多个线程拿到同一个Converter并进行调用
	// 二是如果不添加缓存，生成Converter的开销会有多大
	private static Map<Class<?>, Converter> conMap = new HashMap<Class<?>, Converter>();

	private static Set<Class<?>> conClzSet = new HashSet<Class<?>>();

	/** 是否用在多线程环境中 */
	@SuppressWarnings("unused")
	private static boolean useInMultiThreaded = false;

	private static final String CONVERT_PACKAGE = "org.xblink.core.convert.converters";

	private static final String CONVERT_IMPL = "org.xblink.core.convert.converters.%sConverter";

	/**
	 * 转换器仓库初始化，加载提供的转换器到缓存中。
	 */
	public static void init() {
		// 扫描Classpath路径的转换器类，加载到缓存中
		List<Class<?>> convertersClzs = ConverterScan.scanPackage(CONVERT_PACKAGE);
		for (Iterator<Class<?>> iterator = convertersClzs.iterator(); iterator.hasNext();) {
			Class<?> converterClz = iterator.next();
			setConverter(converterClz);
		}
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
	 * 通过Class类型与对象，拿到其文字值。
	 * 
	 * @param clz
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getTextValueByData(Class<?> clz, Object obj) throws Exception {
		return searchConverterForType(clz).obj2Text(obj);
	}

	/**
	 * 缓存中是否已经存在该转换器。
	 * 
	 * @param converterClz
	 * @return
	 */
	public static boolean hasThisConverter(Class<?> converterClz) {
		return conClzSet.contains(converterClz);
	}

	/**
	 * 寻获对应转换器。
	 * 
	 * @param clz
	 *            类
	 * @return 转换器
	 * @throws Exception
	 */
	public static Converter searchConverterForType(Class<?> clz) throws Exception {
		Converter converter = conMap.get(clz);
		if (null == converter) {
			converter = lookForConverterForType(clz);
		}
		return converter;
	}

	private static Converter lookForConverterForType(Class<?> clz) {
		// 根据名称，尝试加载这个类对应的转换器
		String converterClzName = String.format(CONVERT_IMPL, clz.getSimpleName());
		try {
			Class<?> converterClz = Class.forName(converterClzName);
			setConverter(converterClz);
		} catch (ClassNotFoundException e) {
			throw new UnsupportedOperationException(String.format("没有找到[%s]这个类的转换器。", clz.getName()), e);
		}
		return conMap.get(clz);
	}

	protected static void setConverter(Class<?> converterClz) {
		if (Converter.class.isAssignableFrom(converterClz)) {
			try {
				Converter converter = (Converter) converterClz.newInstance();
				// 转换器类加入找缓存中
				conClzSet.add(converterClz);
				for (Class<?> cls : converter.getTypes()) {
					// 转换器和起可以转换的对象类型加入到缓存中
					conMap.put(cls, converter);
					// 单值类型进行记录
					TypeUtil.add2SingaleValueMap(cls);
				}
			} catch (Exception e) {
				throw new UnsupportedOperationException(String.format("无法生成[%s]这个转换器。", converterClz.getName()));
			}
		} else {
			throw new UnsupportedOperationException(String.format("[%s]不是XBlink支持的转换器类型。", converterClz.getName()));
		}
	}
}
package org.xblink.core.convert;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.xblink.core.TransferInfo;
import org.xblink.util.TypeUtil;

/**
 * 转换器仓库，根据Class类型，返回对应的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ConverterWarehouse {

	private ConverterWarehouse() {
	}

	private static Map<Class<?>, Converter> converterMap = new ConcurrentHashMap<Class<?>, Converter>();

	private static Set<Class<?>> conClzSet = Collections.synchronizedSet(new HashSet<Class<?>>());

	private static final String CONVERT_IMPL = "org.xblink.core.convert.converters.%sConverter";

	/**
	 * 转换器仓库初始化，加载提供的转换器到缓存中。
	 */
	public static void init() {
		// 扫描Classpath路径的转换器类，加载到缓存中
		List<Class<?>> convertersClzs = ConverterScan.scanConverter();
		for (Iterator<Class<?>> iterator = convertersClzs.iterator(); iterator.hasNext();) {
			Class<?> converterClz = iterator.next();
			setConverter(converterClz);
		}
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
	 * @param transferInfo
	 *            相关信息
	 * @return 转换器
	 * @throws Exception
	 */
	public static Converter searchConverterForType(Class<?> clz, TransferInfo transferInfo) throws Exception {
		Converter converter = converterMap.get(clz);
		if (null == converter) {
			converter = lookForConverterForType(clz);
		}
		if (!converter.isSingleValueType()) {
			// 具有格式的转换器 需要transferInfo
			FormValueTypeConverter formValueTypeConverter = ((FormValueTypeConverter) converter).newInstance();
			formValueTypeConverter.setTransferInfo(transferInfo);
			converter = formValueTypeConverter;
		}
		return converter;
	}

	/**
	 * 获得当前仓库中所有的转换器。
	 * 
	 * @return
	 */
	public static Collection<Converter> getAllConverters() {
		return converterMap.values();
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
		return converterMap.get(clz);
	}

	protected static void setConverter(Class<?> converterClz) {
		if (Converter.class.isAssignableFrom(converterClz)) {
			try {
				Converter converter = (Converter) converterClz.newInstance();
				// 转换器类加入找缓存中
				conClzSet.add(converterClz);
				for (Class<?> cls : converter.getTypes()) {
					// 转换器和起可以转换的对象类型加入到缓存中
					converterMap.put(cls, converter);
					// 单值类型进行记录
					if (converter.isSingleValueType()) {
						TypeUtil.add2SingaleValueMap(cls);
					}
				}
			} catch (Exception e) {
				throw new UnsupportedOperationException(String.format("无法生成[%s]这个转换器。", converterClz.getName()));
			}
		} else {
			throw new UnsupportedOperationException(String.format("[%s]不是XBlink支持的转换器类型。", converterClz.getName()));
		}
	}
}

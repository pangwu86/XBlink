package org.xblink.core.convert;

import org.xblink.annotation.XBlinkConverter;
import org.xblink.util.TypeUtil;

/**
 * 提供一个方式，用户也可以注册自己的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ConverterRegistry {

	private ConverterRegistry() {
	}

	/**
	 * 注册一个转换器。
	 * 
	 * @param objClz
	 *            转换对象的类
	 * @param converterClz
	 *            转换器的类
	 */
	public static void register(Class<?> objClz, Class<? extends Converter> converterClz) {
		ConverterWarehouse.setConverter(converterClz, objClz);
		TypeUtil.add2SingaleValueMap(objClz);
	}

	/**
	 * 查看对象是否具有自定义转换器。
	 * 
	 * @param objClz
	 *            转换对象的类
	 * @return
	 */
	public static boolean hasCustomizedConverter(Class<?> objClz) {
		XBlinkConverter xBlinkConverter = objClz.getAnnotation(XBlinkConverter.class);
		if (null != xBlinkConverter) {
			Class<? extends Converter> converterClz = xBlinkConverter.value();
			register(objClz, converterClz);
			return true;
		}
		return false;
	}

}

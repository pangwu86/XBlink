package org.xblink.core.type;

import org.xblink.core.convert.Converter;
import org.xblink.core.convert.ConverterWarehouse;

/**
 * 提供几个共通方法
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractXType implements XType {

	/**
	 * 通过转换器，将对象转换成对应的文字值。
	 * 
	 * @param obj
	 * @param objClz
	 * @return
	 * @throws Exception
	 */
	public String getText(Object obj, Class<?> objClz) throws Exception {
		// 根据对象类型，寻找对应的转换器，完成转换操作
		Converter converter = ConverterWarehouse.searchConverterForType(objClz);
		return converter.obj2Text(obj);
	}

}

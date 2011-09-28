package org.xblink.core.convert.converters;

import static org.xblink.util.ReflectUtil.getField;

import java.lang.reflect.Field;

import org.xblink.core.TransferInfo;
import org.xblink.core.cache.AliasCache;
import org.xblink.core.convert.Converter;
import org.xblink.core.serial.Serialize;

/**
 * 一般来说是个自定义对象，通过反射的方式来完成转换工作。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ReflectConverter implements Converter {

	public static final ReflectConverter INSTANCE = new ReflectConverter();

	public boolean canConvert(Class<?> type) {
		// 任何类都可以，毕竟都用上反射了呀，嫩说是不。
		return true;
	}

	public void obj2Text(Object obj, String tagName, TransferInfo transferInfo) throws Exception {
		Class<?> objClz = obj.getClass();
		for (Field field : getField(objClz)) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			// 空的话不进行序列化
			Object fieldValue = field.get(obj);
			if (null == fieldValue) {
				continue;
			}
			Serialize.doIt(fieldValue, transferInfo, AliasCache.getFieldName(objClz, field));
		}
	}
}

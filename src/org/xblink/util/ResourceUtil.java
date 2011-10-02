package org.xblink.util;

import java.util.Arrays;
import java.util.List;

import org.xblink.core.convert.converters.DateConverter;
import org.xblink.core.convert.converters.IntegerConverter;
import org.xblink.core.convert.converters.StringConverter;

/**
 * 获取资源的一个帮助类。
 * 
 * 参考了Nutz的部分实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class ResourceUtil {

	private static final String FLT_CLASS = "^.+[.]class$";

	/**
	 * 搜索并返回给定包下所有的类（递归）
	 * 
	 * @param pkg
	 *            包名或者包路径
	 * @return
	 */
	public static List<Class<?>> scanPackage(String pkg) {
		return scanPackage(pkg, FLT_CLASS);
	}

	private static List<Class<?>> scanPackage(String pkg, String fltClass) {
		// FIXME 如果查找ClassPath路径下还有jar包中的这些class文件
		return Arrays.asList(new Class<?>[] { StringConverter.class, IntegerConverter.class, DateConverter.class });
	}
}

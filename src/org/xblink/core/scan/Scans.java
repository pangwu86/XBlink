package org.xblink.core.scan;

import java.util.List;

import org.xblink.XBlink;

/**
 * 提供了对一些资源的查找方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class Scans {

	private Scans() {
	}

	@SuppressWarnings("unused")
	private static final String FLT_CLASS = "^.+[.]class$";

	/**
	 * 扫描指定包下的所有Class。（递归调用，包含子目录）
	 * 
	 * @param pkg
	 * @return 类集合
	 */
	public static List<Class<?>> scanPackage(String pkg) {
		// TODO 尚未实现扫描
		throw new UnsupportedOperationException(String.format("当前版本[%s]未实现此功能，请不要使用。", XBlink.getVersion()));
	}
}

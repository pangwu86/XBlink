package org.xblink;

/**
 * XBlink运行时参数设置。<BR>
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBConfig {

	private static String INDENT_STRING = "\t";
	private static String BR = System.getProperty("line.separator");
	private static String ENCODING = "UTF-8";

	private XBConfig() {
		// TODO 设置默认参数
	}

	/**
	 * 生成一个XBlink运行时配置项。
	 * 
	 * @return 配置项
	 */
	public static XBConfig createXBConfig() {
		return new XBConfig();
	}

	// 这里可以设定各种需要的参数

}

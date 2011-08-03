package org.xblink;

/**
 * XBlink运行时参数设置。<BR>
 * 
 * 
 * @author pangwu86@gmail.com
 * 
 */
public class XBConfig {

	private static XBConfig defaultConfig = new XBConfig();

	private XBConfig() {
		// TODO 设置默认参数
	}

	protected static XBConfig getDefaultXBConfig() {
		return defaultConfig;
	}

	public static XBConfig createXBConfig() {
		return new XBConfig();
	}

	// 这里可以设定各种需要的参数

}

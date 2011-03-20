package org.xblink;

/**
 * XBlink运行时参数设置。<BR>
 * 
 * 
 * @author pangwu86@gmail.com
 * 
 */
public class XBConfig {

	/**
	 * 默认配置信息。
	 */
	private final static XBConfig DEFAULT_XBCONFIG = new XBConfig();

	/**
	 * 获得默认配置信息。
	 * 
	 * @return
	 */
	public static XBConfig getDefaultXBConfig() {
		return DEFAULT_XBCONFIG;
	}

	public XBConfig() {
		// 默认参数设置
	}

	// 这里可以设定各种需要的参数

}

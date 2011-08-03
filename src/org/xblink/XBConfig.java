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

	/**
	 * 获得默认配置信息。
	 * 
	 * @return
	 */
	static XBConfig getDefaultXBConfig() {
		return defaultConfig;
	}

	/**
	 * 私有的构造函数
	 */
	private XBConfig() {
		// TODO 设置默认参数
	}
	
	public static XBConfig createXBConfig(){
		return new XBConfig();
	}

	// 这里可以设定各种需要的参数
	
	

}

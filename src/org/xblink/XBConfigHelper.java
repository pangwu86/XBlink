package org.xblink;

/**
 * XBlink配置项小助手，通过她拿到当前需要的运行时配置信息。(应该是线程安全的)
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBConfigHelper {

	/**
	 * 默认的配置项，全局唯一。在没有设定临时配置项时，以此配置为主。
	 */
	private static XBConfig globalXbConfig = XBConfig.newXBConfig();

	/**
	 * 临时的配置项，设定后调用就会采用当前配置项，调用后即抛弃。
	 */
	private static ThreadLocal<XBConfig> transientXbConfig = new ThreadLocal<XBConfig>();

	/**
	 * 临时调用计数器。(可设定调用次数)
	 */
	private static ThreadLocal<Integer> count = new ThreadLocal<Integer>();

	/**
	 * 获得配置信息。
	 * 
	 * @return 配置信息
	 */
	protected static XBConfig getXbConfig() {
		// 先查看是否有临时配置项
		Integer current = count.get();
		if (null == current || 0 == current.intValue()) {
			// 没有的情况下，使用默认配置项
			return globalXbConfig;
		}
		XBConfig xbConfig = transientXbConfig.get();
		transientXbConfig.set(null);
		count.set(0);
		return xbConfig;
	}

	protected static void setTransientXBConfig(XBConfig xbConfig) {
		transientXbConfig.set(xbConfig);
		count.set(1);
	}

	protected static void setGlobalXBConfig(XBConfig xbConfig) {
		globalXbConfig = xbConfig;
	}

}

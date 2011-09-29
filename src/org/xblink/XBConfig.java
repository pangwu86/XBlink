package org.xblink;

/**
 * XBlink运行时参数设置。<BR>
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBConfig {

	private boolean useRelativePath = true;

	private boolean useCache = false;

	private XBConfig() {
		// TODO 设置默认参数
		useRelativePath = true;
	}

	/**
	 * 生成一个XBlink运行时配置项。
	 * 
	 * @return 配置项
	 */
	public static XBConfig createXBConfig() {
		return new XBConfig();
	}

	public boolean isUseRelativePath() {
		return useRelativePath;
	}

	public XBConfig setUseRelativePath(boolean use) {
		this.useRelativePath = use;
		return this;
	}

	public boolean isUseCache() {
		return useCache;
	}

	public XBConfig setUseCache(boolean useCache) {
		this.useCache = useCache;
		return this;
	}

	// 这里可以设定各种需要的参数

}

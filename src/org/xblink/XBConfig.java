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

	private boolean ignoreNull = true;

	private boolean ignoreTransient = true;

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

	/**
	 * 使用相对路径。
	 * 
	 * @param useRelativePath
	 * @return
	 */
	public XBConfig setUseRelativePath(boolean useRelativePath) {
		this.useRelativePath = useRelativePath;
		return this;
	}

	public boolean isUseCache() {
		return useCache;
	}

	/**
	 * 使用缓存。
	 * 
	 * @param useCache
	 * @return
	 */
	public XBConfig setUseCache(boolean useCache) {
		this.useCache = useCache;
		return this;
	}

	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	/**
	 * 忽略空字段。
	 * 
	 * @param ignoreNull
	 * @return
	 */
	public XBConfig setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
		return this;
	}

	public boolean isIgnoreTransient() {
		return ignoreTransient;
	}

	/**
	 * 忽略Transient字段。
	 * 
	 * @param ignoreNull
	 * @return
	 */
	public XBConfig setIgnoreTransient(boolean ignoreTransient) {
		this.ignoreTransient = ignoreTransient;
		return this;
	}

	// 这里可以设定各种需要的参数

}

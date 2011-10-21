package org.xblink;

/**
 * XBlink运行时参数设置。<BR>
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XBConfig {

	private boolean useRelativePath;

	private boolean ignoreNull;

	private boolean ignoreTransient;

	private boolean compact;

	private boolean useStringNull;

	private XBConfig() {
		useRelativePath = true;
		ignoreNull = true;
		ignoreTransient = true;
		compact = false;
		useStringNull = true;
	}

	/**
	 * 生成一个XBlink运行时配置项。
	 * 
	 * @return 配置项
	 */
	public static XBConfig newXBConfig() {
		return new XBConfig();
	}

	/**
	 * 是否使用了相对路径。
	 * 
	 * @return
	 */
	public boolean isUseRelativePath() {
		return useRelativePath;
	}

	/**
	 * 设置使用相对路径。
	 * 
	 * @param useRelativePath
	 * @return
	 */
	public XBConfig setUseRelativePath(boolean useRelativePath) {
		this.useRelativePath = useRelativePath;
		return this;
	}

	/**
	 * 是否忽略空字段。
	 * 
	 * @return
	 */
	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	/**
	 * 设置忽略空字段。
	 * 
	 * @param ignoreNull
	 * @return
	 */
	public XBConfig setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
		return this;
	}

	/**
	 * 是否使用字符串类型的Null。
	 * 
	 * @return
	 */
	public boolean isUseStringNull() {
		return useStringNull;
	}

	/**
	 * 设置使用字符串类型的Null。
	 * 
	 * @param useStringNull
	 * @return
	 */
	public XBConfig setUseStringNull(boolean useStringNull) {
		this.useStringNull = useStringNull;
		return this;
	}

	/**
	 * 是否忽略Transient字段。
	 * 
	 * @return
	 */
	public boolean isIgnoreTransient() {
		return ignoreTransient;
	}

	/**
	 * 设置忽略Transient字段。
	 * 
	 * @param ignoreTransient
	 * @return
	 */
	public XBConfig setIgnoreTransient(boolean ignoreTransient) {
		this.ignoreTransient = ignoreTransient;
		return this;
	}

	/**
	 * 是否使用了紧凑模式。
	 * 
	 * @return
	 */
	public boolean isCompact() {
		return compact;
	}

	/**
	 * 设置使用紧凑模式。
	 * 
	 * @param compact
	 * @return
	 */
	public XBConfig setCompact(boolean compact) {
		this.compact = compact;
		return this;
	}

}

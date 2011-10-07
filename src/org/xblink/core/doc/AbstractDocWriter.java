package org.xblink.core.doc;

import java.io.Writer;

import org.xblink.XBConfig;

/**
 * 提供一个必要的构造函数。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractDocWriter implements DocWriter {

	protected Writer writer;
	protected XBConfig xbConfig;

	protected int indentIndex;
	protected String indentString;
	protected String br;
	protected String encoding;

	public AbstractDocWriter(Writer writer) {
		this.writer = writer;
		this.indentIndex = 0;
		this.indentString = "  ";
		this.br = System.getProperty("line.separator");
		this.encoding = "UTF-8";
	}

	public void setXBConfig(XBConfig xbConfig) {
		this.xbConfig = xbConfig;
		// 采用XBConfig中的配置
		if (xbConfig.isCompact()) {
			this.indentString = "";
			this.br = "";
		}
	}

	public Writer getWriter() {
		return this.writer;
	}
}

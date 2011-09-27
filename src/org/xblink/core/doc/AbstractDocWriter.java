package org.xblink.core.doc;

import java.io.Writer;

/**
 * 提供一个必要的构造函数。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractDocWriter implements DocWriter {

	protected Writer writer;

	protected int indentIndex;
	protected String indentString;
	protected String br;
	protected String encoding;

	public AbstractDocWriter(Writer writer) {
		this.writer = writer;
		// TODO 这里如何根据XBConfig来设置
		this.indentIndex = 0;
		this.indentString = "  ";
		this.br = System.getProperty("line.separator");
		this.encoding = "UTF-8";
	}

	public Writer getWriter() {
		return this.writer;
	}
}

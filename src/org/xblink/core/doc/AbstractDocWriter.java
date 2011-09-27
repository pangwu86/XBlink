package org.xblink.core.doc;

import java.io.Writer;

/**
 * 提供一个必要的构造函数。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractDocWriter implements DocWriter {

	private Writer writer;

	public AbstractDocWriter(Writer writer) {
		this.writer = writer;
	}
}

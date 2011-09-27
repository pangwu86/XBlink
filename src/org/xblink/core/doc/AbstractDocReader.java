package org.xblink.core.doc;

import java.io.Reader;

/**
 * 提供一个必要的构造函数。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class AbstractDocReader implements DocReader {

	private Reader reader;

	public AbstractDocReader(Reader reader) {
		this.reader = reader;
	}
}

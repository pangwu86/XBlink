package org.xblink.core.doc.impl;

import java.io.Reader;

import org.xblink.core.doc.AbstractDocReader;

/**
 * XML格式Reader，基于JDK1.6中提供的javax.xml.stream包实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class JDK16Reader extends AbstractDocReader {

	public JDK16Reader(Reader reader) {
		super(reader);
	}

}

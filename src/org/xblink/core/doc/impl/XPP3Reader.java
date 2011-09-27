package org.xblink.core.doc.impl;

import java.io.Reader;

import org.xblink.core.doc.AbstractDocReader;

/**
 * XML格式Reader，基于XPP3的MXParser实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XPP3Reader extends AbstractDocReader {

	public XPP3Reader(Reader reader) {
		super(reader);
	}

}

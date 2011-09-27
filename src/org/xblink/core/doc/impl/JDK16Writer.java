package org.xblink.core.doc.impl;

import java.io.Writer;

import org.xblink.core.doc.AbstractDocWriter;

/**
 * XML格式Writer，基于JDK1.6中提供的javax.xml.stream包实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class JDK16Writer extends AbstractDocWriter {

	public JDK16Writer(Writer writer) {
		super(writer);
	}

}

package org.xblink.core.doc;

import java.io.Writer;

/**
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class DocWriter {

	private Writer writer;

	public DocWriter(Writer writer) {
		this.writer = writer;
	}
}

package org.xblink.core.doc;

import java.io.Reader;

/**
 * 
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class DocReader {

	private Reader reader;

	public DocReader(Reader reader) {
		this.reader = reader;
	}

}

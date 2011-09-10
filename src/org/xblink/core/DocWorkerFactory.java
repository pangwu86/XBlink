package org.xblink.core;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 生成DocReader与DocWriter的工厂。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class DocWorkerFactory {

	private DocWorkerFactory() {
	}

	public static DocWriter createDocWriter() {
		// TODO
		return null;
	}

	public static DocWriter createDocWriter(Writer out) {
		// TODO
		return null;
	}

	public static DocWriter createDocWriter(OutputStream out) {
		// TODO
		return null;
	}

	public static DocWriter createDocWriter(File file) {
		// TODO
		return null;
	}

	public static DocReader createDocReader(CharSequence cs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DocReader createDocReader(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DocReader createDocReader(InputStream in) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DocReader createDocReader(Reader reader) {
		// TODO Auto-generated method stub
		return null;
	}

}

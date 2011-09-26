package org.xblink.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/**
 * IO流的一些方法。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class IOUtil {

	private IOUtil() {
	}

	public static Writer createWriter() {
		// TODO
		return null;
	}

	public static Writer createWriter(Writer out) {
		// TODO
		return null;
	}

	public static Writer createWriter(OutputStream out) {
		// TODO
		return null;
	}

	public static Writer createWriter(File file) {
		// TODO
		return null;
	}

	public static Reader createReader(CharSequence cs) {
		StringReader stringReader = new StringReader(cs.toString());
		BufferedReader bufferedReader = new BufferedReader(stringReader);
		return bufferedReader;
	}

	public static Reader createReader(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Reader createReader(InputStream in) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Reader createReader(Reader reader) {
		// TODO Auto-generated method stub
		return null;
	}

}

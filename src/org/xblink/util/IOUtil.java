package org.xblink.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
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
		return new StringWriter();
	}

	public static Writer createWriter(Writer writer) {
		// TODO 需要处理吗？
		return addBuffered(writer);
	}

	public static Writer createWriter(OutputStream out) {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
		return addBuffered(outputStreamWriter);
	}

	public static Writer createWriter(File file) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException e) {
			throw new RuntimeException("输出文件出现异常", e);
		}
		return addBuffered(fileWriter);
	}

	private static Writer addBuffered(Writer writer) {
		return new BufferedWriter(writer);
	}

	public static Reader createReader(CharSequence cs) {
		StringReader stringReader = new StringReader(cs.toString());
		// 实验证明 如果在StringReader上包装一层BufferedReader 会降低10%左右的速度
		// BufferedReader bufferedReader = new BufferedReader(stringReader);
		return stringReader;
	}

	public static Reader createReader(File file) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("访问文件不存在。", e);
		}
		return addBuffered(fileReader);
	}

	public static Reader createReader(InputStream in) {
		InputStreamReader inputStreamReader = new InputStreamReader(in);
		return addBuffered(inputStreamReader);
	}

	public static Reader createReader(Reader reader) {
		// TODO 需要处理吗？
		return addBuffered(reader);
	}

	private static Reader addBuffered(Reader reader) {
		return new BufferedReader(reader);
	}

}

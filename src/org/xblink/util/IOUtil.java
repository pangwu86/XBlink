package org.xblink.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * IO流的一些方法。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class IOUtil {

	/**
	 * 获得输出流。
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 输出流
	 */
	public static OutputStream getOutputStream(String filePath) {
		try {
			// 两层装饰
			return new BufferedOutputStream(new FileOutputStream(getFile(filePath)));
		} catch (Exception e) {
			throw new RuntimeException("无法获得输出流。", e);
		}
	}

	/**
	 * 包装输出流，添加缓冲。
	 * 
	 * @param outputStream
	 *            输出流
	 * @return 输出流
	 */
	public static OutputStream getOutputStream(OutputStream outputStream) {
		// 增加缓冲
		return new BufferedOutputStream(outputStream);
	}

	/**
	 * 获得文件对象.
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件
	 * @throws IOException
	 */
	public static File getFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

}

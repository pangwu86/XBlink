package org.xblink.core.doc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.xblink.core.doc.impl.XPP3Reader;
import org.xblink.core.doc.impl.XPP3Writer;
import org.xblink.util.StringUtil;

/**
 * 生成DocReader与DocWriter的工厂。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class DocWorkerFactory {

	private DocWorkerFactory() {}

	private static Map<String, String> implMap = new HashMap<String, String>();

	private static String R = ".reader";
	private static String W = ".writer";

	static {
		// 添加几个默认的
		implMap.put("xml.writer", "XPP3Writer");
		implMap.put("xml.reader", "XPP3Reader");
		// 读取配置文件，加载其他实现类
		InputStream in = null;
		try {
			in = DocWorkerFactory.class.getResourceAsStream("/xblink.properties");
			if (null != in) {
				Properties prop = new Properties();
				prop.load(in);
				Enumeration<Object> em = prop.keys();
				while (em.hasMoreElements()) {
					String key = (String) em.nextElement();
					String value = prop.getProperty(key);
					implMap.put(key.toLowerCase(), value);
				}
			}
		}
		catch (IOException e) {}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Throwable e) {}
			}
		}
	}

	private final static String WRITER_IMPL_CLASS_NAME = "org.xblink.core.doc.impl.%s";

	private final static String READER_IMPL_CLASS_NAME = "org.xblink.core.doc.impl.%s";

	private static DocWriter findDocWriterByDocTypeName(Writer writer, String docTypeName) {
		String writerName = implMap.get(docTypeName.toLowerCase() + W);
		if (StringUtil.isBlankStr(writerName)) {
			throw new UnsupportedOperationException(String.format(	"Can't find the writer [%s]",
																	docTypeName));
		}
		return (DocWriter) getInstance(writer, Writer.class, writerName, WRITER_IMPL_CLASS_NAME);
	}

	private static DocReader findDocReaderByDocTypeName(Reader reader, String docTypeName) {
		String readerName = implMap.get(docTypeName.toLowerCase() + R);
		if (StringUtil.isBlankStr(readerName)) {
			throw new UnsupportedOperationException(String.format(	"Can't find the reader [%s]",
																	docTypeName));
		}
		return (DocReader) getInstance(reader, Reader.class, readerName, READER_IMPL_CLASS_NAME);
	}

	private static Object getInstance(	Object initarg,
										Class<?> paramClz,
										String wName,
										String implClassName) {
		String className = String.format(implClassName, wName);
		Constructor<?> constructor = getConstructor(className, paramClz);
		Object instance = null;
		try {
			instance = constructor.newInstance(initarg);
		}
		catch (Exception e) {
			throw new RuntimeException(String.format("[%s] can't instantiation", className), e);
		}
		return instance;
	}

	private static Constructor<?> getConstructor(String className, Class<?> paramClz) {
		Constructor<?> constructor = null;
		try {
			Class<?> clz = Class.forName(className);
			constructor = clz.getDeclaredConstructor(paramClz);
		}
		catch (Exception e) {
			throw new RuntimeException(String.format(	"Can't find the impl class for [%s]",
														className), e);
		}
		return constructor;
	}

	// ******************** 生成Writer ********************

	public static DocWriter createXmlWriter(Writer writer) {
		return new XPP3Writer(writer);
	}

	public static DocWriter createAnyWriter(Writer writer, String docTypeName) {
		return findDocWriterByDocTypeName(writer, docTypeName);
	}

	// ******************** 生成Reader ********************

	public static DocReader createXmlReader(Reader reader) {
		return new XPP3Reader(reader);
	}

	public static DocReader createAnyReader(Reader reader, String docTypeName) {
		return findDocReaderByDocTypeName(reader, docTypeName);
	}
}

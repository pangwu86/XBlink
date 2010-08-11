package org.xblink.writer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import org.xblink.Constants;
import org.xblink.XRoot;

/**
 * XML序列化工具类.
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLWriter implements Constants {

	/**
	 * 
	 * @param filePath
	 * @param obj
	 * @throws FileNotFoundException
	 */
	public void writeXML(String filePath, Object obj) throws FileNotFoundException {
		writeXML(filePath, obj, true, "UTF-8");
	}

	/**
	 * 
	 * @param filePath
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 * @throws FileNotFoundException
	 */
	public void writeXML(String filePath, Object obj, boolean formatXml, String encoding)
			throws FileNotFoundException {
		try {
			writeStart(new BufferedOutputStream(new FileOutputStream(getFile(filePath))), obj,
					formatXml, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param outputStream
	 * @param obj
	 */
	public void writeXML(OutputStream outputStream, Object obj) {
		writeXML(outputStream, obj, true, "UTF-8");
	}

	/**
	 * 
	 * @param outputStream
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 */
	public void writeXML(OutputStream outputStream, Object obj, boolean formatXml, String encoding) {
		try {
			writeStart(outputStream, obj, formatXml, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param out
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 * @throws Exception
	 */
	private void writeStart(OutputStream out, Object obj, boolean formatXml, String encoding)
			throws Exception {
		XMLWriterUtil writer = null;
		try {
			writer = new XMLWriterUtil(out, formatXml ? 2 : 1, encoding);
			Class<?> objClass = obj.getClass();
			XRoot root = new XRoot();
			// 判断是否是集合类型，是的话放入root对象中再进行序列化
			if (objClass.isArray()) {
				root.setArray((Object[]) obj);
				obj = root;
			} else if (XMLCollection.List.equals(getCollection(objClass))) {
				root.setList((List<?>) obj);
				obj = root;
			} else if (XMLCollection.Set.equals(getCollection(objClass))) {
				root.setSet((Set<?>) obj);
				obj = root;
			} else if (XMLCollection.Map.equals(getCollection(objClass))) {
				root.setMap((Map<?, ?>) obj);
				obj = root;
			}
			// 开始序列化
			writer.writeStartDocument();
			new XMLObjectWriter().write(obj, writer, null);
			writer.writeEndDocument();
		} finally {
			if (out != null) {
				out.close();
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断该Class是哪种集合类型.
	 * 
	 * @param clz
	 * @return
	 */
	private XMLCollection getCollection(Class<?> clz) {
		Class<?>[] interfaces = clz.getInterfaces();
		for (Class<?> inter : interfaces) {
			if (inter.equals(XMLCollection.List.getClz())) {
				return XMLCollection.List;
			} else if (inter.equals(XMLCollection.Set.getClz())) {
				return XMLCollection.Set;
			} else if (inter.equals(XMLCollection.Map.getClz())) {
				return XMLCollection.Map;
			}
		}
		return XMLCollection.NotCollection;
	}

	/**
	 * 获得文件对象.
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private File getFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
}

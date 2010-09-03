package org.xblink.writer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import org.xblink.Constants;
import org.xblink.XMLObject;
import org.xblink.XRoot;
import org.xblink.domdrivers.DomDriver;
import org.xblink.transfer.ReferenceObject;
import org.xblink.transfer.TransferInfo;

/**
 * XML序列化工具类.
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLWriter {

	/**
	 * 
	 * @param filePath
	 * @param obj
	 * @param domDriver
	 */
	public void writeXML(String filePath, Object obj, DomDriver domDriver) {
		writeXML(filePath, obj, true, "UTF-8", domDriver);
	}

	/**
	 * 
	 * @param filePath
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 * @param domDriver
	 */
	public void writeXML(String filePath, Object obj, boolean formatXml, String encoding,
			DomDriver domDriver) {
		try {
			writeStart(new BufferedOutputStream(new FileOutputStream(getFile(filePath))), obj,
					formatXml, encoding, domDriver);
		} catch (Exception e) {
			throw new RuntimeException(Constants.SERIALIZE_FAIL, e);
		}
	}

	/**
	 * 
	 * @param outputStream
	 * @param obj
	 * @param domDriver
	 */
	public void writeXML(OutputStream outputStream, Object obj, DomDriver domDriver) {
		writeXML(outputStream, obj, true, "UTF-8", domDriver);
	}

	/**
	 * 
	 * @param outputStream
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 * @param domDriver
	 */
	public void writeXML(OutputStream outputStream, Object obj, boolean formatXml, String encoding,
			DomDriver domDriver) {
		try {
			writeStart(outputStream, obj, formatXml, encoding, domDriver);
		} catch (Exception e) {
			throw new RuntimeException(Constants.SERIALIZE_FAIL, e);
		}
	}

	/**
	 * 
	 * @param out
	 * @param obj
	 * @param formatXml
	 * @param encoding
	 * @param domDriver
	 * @throws Exception
	 */
	private void writeStart(OutputStream out, Object obj, boolean formatXml, String encoding,
			DomDriver domDriver) throws Exception {
		// 解析过的对象，方便其他对象引用
		Map<Integer, ReferenceObject> referenceObjects = new HashMap<Integer, ReferenceObject>();
		// 传递信息对象
		TransferInfo transferInfo = new TransferInfo();
		transferInfo.setReferenceObjects(referenceObjects);
		// TODO domDriver的使用 替代这里的XMLWriterUtil 这里是重点
		XMLWriterHelper writer = null;
		try {
			writer = new XMLWriterHelper(out, formatXml ? 2 : 1, encoding);
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
			new XMLObjectWriter().write(obj, writer, null, transferInfo);
			writer.writeEndDocument();
			// 去掉XRoot的信息
			XMLObject.cleanXRoot();
		} finally {
			// 手动释放对象引用
			transferInfo = null;
			// 关闭相关流
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

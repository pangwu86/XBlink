package org.xblink.writer;

import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML文件输出工具类.
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLWriterUtil {

	XMLStreamWriter writer;
	int indentIndex;
	String indentString;
	String br;
	String encoding;

	int formatXml;
	/**
	 * FORMAT_BR 只包含换行的XML文件 FORMAT_INDENT 包含换行和缩进的XML文件 FORMAT_TRIM
	 */
	public static final int FORMAT_BR = 0x01;
	public static final int FORMAT_INDENT = 0x02;
	public static final int FORMAT_TRIM = 0x04;

	/**
	 * 
	 * @param out
	 * @param formatXml
	 * @param encoding
	 */
	public XMLWriterUtil(OutputStream out, int formatXml, String encoding) {

		this.formatXml = formatXml;
		this.encoding = encoding;
		this.indentIndex = 0;
		this.indentString = "  ";
		this.br = System.getProperty("line.separator");
		try {
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(out, encoding);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws XMLStreamException
	 * 
	 */
	public void writeStartDocument() throws XMLStreamException {
		writer.writeStartDocument(encoding, "1.0");
	}

	/**
	 * @throws XMLStreamException
	 * 
	 */
	public void writeEndDocument() throws XMLStreamException {
		writeBR();
		writer.writeEndDocument();
	}

	/**
	 * 
	 * @param localName
	 * @throws XMLStreamException
	 */
	public void writeStartElement(String localName) throws XMLStreamException {
		writeStartElement(localName, null, null);
	}

	/**
	 * 
	 * @param localName
	 * @param localNames
	 * @param values
	 * @throws XMLStreamException
	 */
	public void writeStartElement(String localName, String[] localNames, String[] values)
			throws XMLStreamException {
		writeBR();
		writeIndent();
		indentIndex++;
		writer.writeStartElement(localName);
		if (localNames != null && values != null) {
			for (int atr = 0; atr < localNames.length; atr++) {
				writeAttribute(localNames[atr], values[atr]);
			}
		}

	}

	/**
	 * @throws XMLStreamException
	 * 
	 */
	public void writeEndElement() throws XMLStreamException {
		writeBR();
		indentIndex--;
		writeIndent();
		writer.writeEndElement();
	}

	/**
	 * 
	 * @param localName
	 * @param value
	 * @throws XMLStreamException
	 */
	public void writeAttribute(String localName, String value) throws XMLStreamException {
		writer.writeAttribute(localName, value);
	}

	/**
	 * 
	 * @param localName
	 * @param text
	 * @throws XMLStreamException
	 */
	public void writeTextElement(String localName, String text) throws XMLStreamException {
		String current = text == null ? "" : text.trim();
		// if ((formatXml & FORMAT_TRIM) == FORMAT_TRIM)
		current = current.trim();
		writeBR();
		writeIndent();
		if (current.isEmpty()) {
			writer.writeEmptyElement(localName);
			return;
		}
		writer.writeStartElement(localName);
		writer.writeCharacters(current);
		writer.writeEndElement();

	}

	/**
	 * 
	 * @throws XMLStreamException
	 */
	private void writeIndent() throws XMLStreamException {
		// if ((formatXml & FORMAT_INDENT) != FORMAT_INDENT)
		// return;
		for (int idx = 0; idx < indentIndex; idx++) {
			writer.writeCharacters(indentString);
		}
	}

	/**
	 * 
	 * @throws XMLStreamException
	 */
	private void writeBR() throws XMLStreamException {
		writer.writeCharacters(br);
	}

	/**
	 * 
	 * @throws XMLStreamException
	 */
	public void close() throws XMLStreamException {
		writer.close();
	}

}

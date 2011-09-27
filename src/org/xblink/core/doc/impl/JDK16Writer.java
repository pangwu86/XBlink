package org.xblink.core.doc.impl;

import java.io.Writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.xblink.core.doc.AbstractDocWriter;
import org.xblink.util.StringUtil;

/**
 * XML格式Writer，基于JDK1.6中提供的javax.xml.stream包实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class JDK16Writer extends AbstractDocWriter {

	private XMLStreamWriter innerWriter;

	public JDK16Writer(Writer writer) {
		super(writer);
		try {
			innerWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
		} catch (Exception e) {
			throw new RuntimeException("JDK16Writer无法初始化。", e);
		}
	}

	private void writeIndent() throws Exception {
		for (int idx = 0; idx < indentIndex; idx++) {
			innerWriter.writeCharacters(indentString);
		}
	}

	private void writeBR() throws Exception {
		innerWriter.writeCharacters(br);
	}

	public void writeStartDocument() throws Exception {
		innerWriter.writeStartDocument(encoding, "1.0");
	}

	public void writeEndDocument() throws Exception {
		innerWriter.writeEndDocument();
	}

	public void writeStartTag(String tagName) throws Exception {
		writeBR();
		writeIndent();
		indentIndex++;
		innerWriter.writeStartElement(tagName);
	}

	public void writeEndTag(String tagName) throws Exception {
		writeBR();
		indentIndex--;
		writeIndent();
		innerWriter.writeEndElement();
	}

	public void writeAttribute(String name, String value) throws Exception {
		innerWriter.writeAttribute(name, value);
	}

	public void writeText(String text) throws Exception {
		innerWriter.writeCharacters(text);
	}

	public void writeElementText(String tagName, String text) throws Exception {
		String current = text == null ? "" : text.trim();
		writeBR();
		writeIndent();
		if (StringUtil.isBlankStr(current)) {
			innerWriter.writeStartElement(tagName);
			innerWriter.writeEndElement();
			return;
		}
		innerWriter.writeStartElement(tagName);
		innerWriter.writeCharacters(text);
		innerWriter.writeEndElement();
	}

	public void close() throws Exception {
		innerWriter.close();
	}

	public void flush() throws Exception {
		innerWriter.flush();
	}
}

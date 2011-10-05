package org.xblink.core.path;

import org.xblink.core.doc.DocWriter;

/**
 * 一个Writer的包装类，在DocWriter工作时，记录下当前的节点信息，方便计算路径。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class PathTrackingWriter implements DocWriter {

	private DocWriter docWriter;
	private PathTracker pathTracker;

	public PathTrackingWriter(DocWriter docWriter, PathTracker pathTracker) {
		this.docWriter = docWriter;
		this.pathTracker = pathTracker;
	}

	public void writeStartDocument() throws Exception {
		docWriter.writeStartDocument();
	}

	public void writeEndDocument() throws Exception {
		docWriter.writeEndDocument();
	}

	public void writeStartTag(String tagName) throws Exception {
		docWriter.writeStartTag(tagName);
		// 进去下一层
		pathTracker.push(tagName);
	}

	public void writeEndTag(String tagName) throws Exception {
		docWriter.writeEndTag(tagName);
		// 退到上一层
		pathTracker.pop();
	}

	public void writeEndTagNotWithNewLine(String tagName) throws Exception {
		// 这里虽然没有重新启动一行，但是在树形结构上还是要退到上一个节点
		docWriter.writeEndTagNotWithNewLine(tagName);
		// 退到上一层
		pathTracker.pop();
	}

	public void writeAttribute(String name, String value) throws Exception {
		docWriter.writeAttribute(name, value);
	}

	public void writeText(String text) throws Exception {
		docWriter.writeText(text);
	}

	public void writeElementText(String tagName, String text) throws Exception {
		docWriter.writeElementText(tagName, text);
	}

	public void close() throws Exception {
		docWriter.close();
	}

	public void flush() throws Exception {
		docWriter.flush();
	}

	public String getString() {
		return docWriter.getString();
	}

}

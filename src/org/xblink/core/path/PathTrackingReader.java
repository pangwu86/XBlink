package org.xblink.core.path;

import java.util.Iterator;

import org.xblink.core.doc.DocReader;

/**
 * 一个Reader的包装类，在DocReader工作时，记录下当前的节点信息，方便计算路径。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class PathTrackingReader implements DocReader {

	private DocReader docReader;
	private PathTracker pathTracker;

	public PathTrackingReader(DocReader docReader, PathTracker pathTracker) {
		this.docReader = docReader;
		this.pathTracker = pathTracker;
	}

	public boolean hasMoreChildren() {
		return docReader.hasMoreChildren();
	}

	public void moveDown() {
		docReader.moveDown();
		pathTracker.push(getNodeName());
	}

	public void moveUp() {
		docReader.moveUp();
		pathTracker.pop();
	}

	public String getNodeName() {
		return docReader.getNodeName();
	}

	public String getTextValue() {
		return docReader.getTextValue();
	}

	public int getAttributeCount() {
		return docReader.getAttributeCount();
	}

	public Iterator<String> getAttributeNames() {
		return docReader.getAttributeNames();
	}

	public String getAttribute(String name) {
		return docReader.getAttribute(name);
	}

	public String getAttribute(int index) {
		return docReader.getAttribute(index);
	}

	public void close() {
		docReader.close();
	}

}

package org.xblink.core.path;

import java.util.Iterator;

import org.xblink.core.doc.DocReader;

public class PathTrackingReader implements DocReader {

	private DocReader docReader;
	private PathTracker pathTracker;

	public PathTrackingReader(DocReader docReader, PathTracker pathTracker) {
		this.docReader = docReader;
		this.pathTracker = pathTracker;
	}

	public void close() {
		docReader.close();
	}

	public boolean hasMoreChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	public void moveDown() {
		// TODO Auto-generated method stub

	}

	public void moveUp() {
		// TODO Auto-generated method stub

	}

	public String getNodeName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTextValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getAttributeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterator<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttribute(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public void moveStart() {
		// TODO Auto-generated method stub

	}
}

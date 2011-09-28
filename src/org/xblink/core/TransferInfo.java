package org.xblink.core;

import java.util.HashMap;
import java.util.Map;

import org.xblink.XBConfig;
import org.xblink.core.doc.DocReader;
import org.xblink.core.doc.DocWriter;
import org.xblink.core.path.PathTracker;

/**
 * 用来传递在工作过程中的一些需要使用信息。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class TransferInfo {

	private final PathTracker pathTracker;
	private final XBConfig xbConfig;
	private final DocWriter docWriter;
	private final DocReader docReader;
	// TODO 这里究竟是用Object好呢，还是Integer好呢？
	private final Map<Object, ReferenceObject> refMap;

	public TransferInfo(PathTracker pathTracker, XBConfig xbConfig, DocWriter docWriter, DocReader docReader) {
		this.pathTracker = pathTracker;
		this.xbConfig = xbConfig;
		this.docWriter = docWriter;
		this.docReader = docReader;
		this.refMap = new HashMap<Object, ReferenceObject>();
	}

	public PathTracker getPathTracker() {
		return pathTracker;
	}

	public XBConfig getXbConfig() {
		return xbConfig;
	}

	public DocWriter getDocWriter() {
		return docWriter;
	}

	public DocReader getDocReader() {
		return docReader;
	}

	public Map<Object, ReferenceObject> getRefMap() {
		return refMap;
	}

}

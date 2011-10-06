package org.xblink.core.doc.impl;

import java.io.Reader;
import java.util.Iterator;

import org.xblink.core.doc.AbstractDocReader;
import org.xblink.rep.org.xmlpull.mxp1.MXParser;
import org.xblink.rep.org.xmlpull.v1.XmlPullParser;

/**
 * XML格式Reader，基于XPP3的MXParser实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XPP3Reader extends AbstractDocReader {

	protected static final int START_NODE = 1;
	protected static final int END_NODE = 2;
	protected static final int TEXT = 3;
	protected static final int COMMENT = 4;
	protected static final int OTHER = 0;
	private XmlPullParser xpp;

	public XPP3Reader(Reader reader) {
		super(reader);
		xpp = new MXParser();
		try {
			xpp.setInput(reader);
		} catch (Exception e) {
			throw new RuntimeException("XPP3Reader无法初始化。", e);
		}
	}

	public void close() {

	}

	public boolean hasMoreChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	public void moveStart() {
		// TODO Auto-generated method stub

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

}

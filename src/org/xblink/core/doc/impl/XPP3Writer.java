package org.xblink.core.doc.impl;

import java.io.Writer;

import org.xblink.core.doc.AbstractDocWriter;
import org.xblink.rep.org.xmlpull.mxp1_serializer.MXSerializer;
import org.xblink.rep.org.xmlpull.v1.XmlSerializer;

/**
 * XML格式Writer，基于XPP3的MXSerializer实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XPP3Writer extends AbstractDocWriter {

	private XmlSerializer serializer;

	public XPP3Writer(Writer writer) {
		super(writer);
		serializer = new MXSerializer();
		try {
			serializer.setOutput(writer);
		} catch (Exception e) {
			// TODO
			throw new RuntimeException("", e);
		}
	}

}

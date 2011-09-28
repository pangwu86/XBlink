package org.xblink.core.type;

import org.xblink.core.TransferInfo;

/**
 * Attribute类型。
 * 
 * XML特有的格式。其他文本格式，会以Element类型来执行。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XAttribute extends XBasicType {

	public static XAttribute INSTANCE = new XAttribute();

	public void writeBasicType(String tagName, String value, TransferInfo transferInfo) throws Exception {
		transferInfo.getDocWriter().writeAttribute(tagName, value);
	}

}

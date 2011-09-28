package org.xblink.core.type;

import org.xblink.core.TransferInfo;

/**
 * Element类型。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XElement extends XBasicType {

	public static XElement INSTANCE = new XElement();

	public void writeBasicType(String tagName, String value, TransferInfo transferInfo) throws Exception {
		transferInfo.getDocWriter().writeElementText(tagName, value);
	}
}

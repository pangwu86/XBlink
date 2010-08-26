package org.xblink.transfer;

import org.xblink.XMLObject;

/**
 * 解析过的类.
 * 
 * 
 * @author geor.wupeiwen
 * 
 */
public class AnalysisedClass {

	/** XML对象 */
	private XMLObject xmlObject;

	/** 是否是XBlink注解标示过的Class */
	private boolean isXBlinkClass;

	public XMLObject getXmlObject() {
		return xmlObject;
	}

	public void setXmlObject(XMLObject xmlObject) {
		this.xmlObject = xmlObject;
	}

	public boolean isXBlinkClass() {
		return isXBlinkClass;
	}

	public void setXBlinkClass(boolean isXBlinkClass) {
		this.isXBlinkClass = isXBlinkClass;
	}

}

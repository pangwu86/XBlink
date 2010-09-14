package org.xblink.collection.map;

import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsElement;

public class KeyObject {
	
	@XBlinkAsAttribute
	private String keyValue1;
	
	@XBlinkAsElement
	private String keyValue2;
	
	public String getKeyValue1() {
		return keyValue1;
	}
	public void setKeyValue1(String keyValue1) {
		this.keyValue1 = keyValue1;
	}
	public String getKeyValue2() {
		return keyValue2;
	}
	public void setKeyValue2(String keyValue2) {
		this.keyValue2 = keyValue2;
	}

	
}

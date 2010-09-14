package org.xblink.loop.loop3;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XBlinkAlias("objectC")
@XStreamAlias("objectC")
public class ObjectC {

	@XBlinkAsObject
	private ObjectA objectA;

	public ObjectA getObjectA() {
		return objectA;
	}

	public void setObjectA(ObjectA objectA) {
		this.objectA = objectA;
	}
	
	
}

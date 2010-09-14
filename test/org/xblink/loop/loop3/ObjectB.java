package org.xblink.loop.loop3;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XBlinkAlias("objectB")
@XStreamAlias("objectB")
public class ObjectB {
	@XBlinkAsObject
	private ObjectC objectC;

	public ObjectC getObjectC() {
		return objectC;
	}

	public void setObjectC(ObjectC objectC) {
		this.objectC = objectC;
	}
	
}

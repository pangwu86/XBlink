package org.xblink.model.loop3;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XBlinkAlias("Root")
@XStreamAlias("Root")
public class Root {

	@XBlinkAsObject
	private ObjectA objectA;
	@XBlinkAsObject
	private ObjectB objectB;
	public ObjectA getObjectA() {
		return objectA;
	}
	public void setObjectA(ObjectA objectA) {
		this.objectA = objectA;
	}
	public ObjectB getObjectB() {
		return objectB;
	}
	public void setObjectB(ObjectB objectB) {
		this.objectB = objectB;
	}
	
	
}

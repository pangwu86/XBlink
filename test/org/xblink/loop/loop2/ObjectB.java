package org.xblink.loop.loop2;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XBlinkAlias("objectB")
@XStreamAlias("objectB")
public class ObjectB {

	@XBlinkAsObject
	private ObjectA objectA;

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private String strB;

	public String getStrB() {
		return strB;
	}

	public void setStrB(String strB) {
		this.strB = strB;
	}

	public ObjectA getObjectA() {
		return objectA;
	}

	public void setObjectA(ObjectA objectA) {
		this.objectA = objectA;
	}

}

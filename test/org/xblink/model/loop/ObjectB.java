package org.xblink.model.loop;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XBlinkAlias("object_B")
@XStreamAlias("object_B")
public class ObjectB {

	@XBlinkAsObject
	private ObjectA objectA;

	@XBlinkAsAttribute
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

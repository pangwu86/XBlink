package org.xblink.model.loop;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XBlinkAlias("objectA")
@XStreamAlias("objectA")
public class ObjectA {

	@XBlinkAsObject
	private ObjectB objectB;

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private String strA;

	public String getStrA() {
		return strA;
	}

	public void setStrA(String strA) {
		this.strA = strA;
	}

	public ObjectB getObjectB() {
		return objectB;
	}

	public void setObjectB(ObjectB objectB) {
		this.objectB = objectB;
	}

}

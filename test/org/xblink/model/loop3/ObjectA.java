package org.xblink.model.loop3;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XBlinkAlias("objectA")
@XStreamAlias("objectA")
public class ObjectA {
	@XBlinkAsAttribute
	@XStreamAsAttribute
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

package org.xblink.model.loop2;

import java.util.List;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XBlinkAlias("objectA")
@XStreamAlias("objectA")
public class ObjectA {

	@XBlinkAsList
	private List<ObjectB> objectBs;

	public List<ObjectB> getObjectBs() {
		return objectBs;
	}

	public void setObjectBs(List<ObjectB> objectBs) {
		this.objectBs = objectBs;
	}
}

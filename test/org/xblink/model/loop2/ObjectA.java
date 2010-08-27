package org.xblink.model.loop2;

import java.util.List;
import java.util.Set;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsArray;
import org.xblink.annotations.XBlinkAsList;
import org.xblink.annotations.XBlinkAsSet;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XBlinkAlias("objectA")
@XStreamAlias("objectA")
public class ObjectA {

	@XBlinkAsList
	private List<ObjectB> objectBs_List;
	
	@XBlinkAsArray
	private ObjectB[] objectBs_Array;

	@XBlinkAsSet
	private Set<ObjectB> objectBs_Set;

	public List<ObjectB> getObjectBs_List() {
		return objectBs_List;
	}

	public void setObjectBs_List(List<ObjectB> objectBs_List) {
		this.objectBs_List = objectBs_List;
	}

	public ObjectB[] getObjectBs_Array() {
		return objectBs_Array;
	}

	public void setObjectBs_Array(ObjectB[] objectBs_Array) {
		this.objectBs_Array = objectBs_Array;
	}

	public Set<ObjectB> getObjectBs_Set() {
		return objectBs_Set;
	}

	public void setObjectBs_Set(Set<ObjectB> objectBs_Set) {
		this.objectBs_Set = objectBs_Set;
	}
	
	
	
}

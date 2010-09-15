package org.xblink.collection.set;

import java.util.Set;

import org.xblink.annotations.XBlinkAsSet;

public class SetContainer {
	
	@XBlinkAsSet
	private Set<String> set1;
	
	@XBlinkAsSet
	private Set<SetObject> set2;

	public Set<String> getSet1() {
		return set1;
	}

	public void setSet1(Set<String> set1) {
		this.set1 = set1;
	}

	public Set<SetObject> getSet2() {
		return set2;
	}

	public void setSet2(Set<SetObject> set2) {
		this.set2 = set2;
	}
}

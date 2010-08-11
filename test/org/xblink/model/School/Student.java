package org.xblink.model.School;

import org.xblink.annotations.XBlinkAsElement;

public class Student {
	
	@XBlinkAsElement
	private String name;

	@XBlinkAsElement
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

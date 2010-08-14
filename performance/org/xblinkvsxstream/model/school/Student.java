package org.xblinkvsxstream.model.school;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XBlinkAlias("student")
@XStreamAlias("student")
public class Student {

	@XStreamAsAttribute
	@XBlinkAsAttribute
	private String name;

	@XStreamAsAttribute
	@XBlinkAsAttribute
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

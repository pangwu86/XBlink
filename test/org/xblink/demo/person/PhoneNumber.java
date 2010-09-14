package org.xblink.demo.person;

import org.xblink.annotations.XBlinkAsElement;

public class PhoneNumber {
	@XBlinkAsElement
	private int code;
	@XBlinkAsElement
	private String number;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}

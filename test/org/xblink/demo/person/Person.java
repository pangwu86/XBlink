package org.xblink.demo.person;

import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsObject;

public class Person {
	@XBlinkAsAttribute
	private String firstname;
	@XBlinkAsAttribute
	private String lastname;
	@XBlinkAsObject
	private PhoneNumber phone;
	@XBlinkAsObject
	private PhoneNumber fax;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public PhoneNumber getFax() {
		return fax;
	}

	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}
}

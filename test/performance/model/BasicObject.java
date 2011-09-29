package performance.model;

import org.xblink.annotation.XBlinkAlias;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XStream")
@XBlinkAlias("XBlink")
public class BasicObject {

	private String name;

	private String address;

	private BasicObject basicObject;

	public BasicObject getBasicObject() {
		return basicObject;
	}

	public void setBasicObject(BasicObject basicObject) {
		this.basicObject = basicObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

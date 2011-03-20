package org.xblink;

import org.junit.Test;

public class XBlinkTest {

	@Test
	public void toAny() throws Exception {
		System.out.println(XBlink.toXML("sdf"));
		System.out.println(XBlink.toAny("any", null, "XML"));
		System.out.println(XBlink.toAny("any", null, "excel"));
	}

	@Test
	public void toXML() throws Exception {

	}
}

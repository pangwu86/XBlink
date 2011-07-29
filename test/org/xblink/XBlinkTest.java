package org.xblink;

import org.junit.Test;

public class XBlinkTest {

	@Test
	public void toAny() throws Exception {
		System.out.println(XBlink.toXML("sdf", null));
		System.out.println(XBlink.toAny("any", null, "XML"));
		System.out.println(XBlink.toAny("any", null, "excel"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void toXML() throws Exception {

	}
}

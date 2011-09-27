package org.xblink.core.doc.impl;

import org.junit.Test;
import org.xblink.util.WatchTimer;

public class JDK16vsXPP3Test {

	public static int num = 100;

	@Test
	public void jdk16WriterPerformance() throws Exception {
		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < num; i++) {
			new JDK16WriterTest().writeXml();
		}
		System.out.println("JDK16:" + timer.getTimer());
	}

	@Test
	public void XPP3WriterPerformance() throws Exception {
		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < num; i++) {
			new XPP3WriterTest().writeXml();
		}
		System.out.println("XPP3:" + timer.getTimer());
	}

}

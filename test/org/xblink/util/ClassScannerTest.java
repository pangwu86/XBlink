package org.xblink.util;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.types.XArray;
import org.xblink.types.XAttribute;

public class ClassScannerTest extends TestCase {

	@Test
	public void testPerformance() throws Exception {
		System.out.println("****第一次测试*****");
		for (int i = 0; i < 100; i++) {
			WatchTimer watchTimer = new WatchTimer();
			ClassScannerUtil.scan(XArray.class);
			System.out.println(watchTimer.getTimer());
		}
		System.out.println("****第二次测试****");
		for (int i = 0; i < 100; i++) {
			WatchTimer watchTimer = new WatchTimer();
			ClassScannerUtil.scan(XBlink.class);
			System.out.println(watchTimer.getTimer());
		}
	}

	@Test
	public void testResults() throws Exception {
		List<Class<?>> list = null;
		list = ClassScannerUtil.scan(XAttribute.class);
		for (Class<?> class1 : list) {
			System.out.println(class1.getName());
		}
	}
}

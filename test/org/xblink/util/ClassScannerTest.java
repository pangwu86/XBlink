package org.xblink.util;

import java.util.List;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.types.XArray;
import org.xblink.types.XAttribute;

import junit.framework.TestCase;

public class ClassScannerTest extends TestCase {

	@Test
	public void testPerformance() throws Exception{
		ClassScanner classScanner = new ClassScanner();
		System.out.println("****第一次测试*****");
		for(int i=0;i<100;i++){
			WatchTimer watchTimer = new WatchTimer();
			classScanner.scan(XArray.class);
			System.out.println(watchTimer.getTimer());
		}
		System.out.println("****第二次测试****");
		for(int i=0;i<100;i++){
			WatchTimer watchTimer = new WatchTimer();
			classScanner.scan(XBlink.class);
			System.out.println(watchTimer.getTimer());
		}
	}
	@Test
	public void testResults() throws Exception{
		List<Class<?>> list = null;
		ClassScanner classScanner = new ClassScanner();
		list=classScanner.scan(XAttribute.class);
		for (Class<?> class1 : list) {
			System.out.println(class1.getName());
		}
	}
}

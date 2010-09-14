package org.xblink.loop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.loop.loop1.ObjectA;
import org.xblink.loop.loop1.ObjectB;
import org.xblink.util.WatchTimer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class LoopTest1 {

	private ObjectA objectA;

	private ObjectB objectB;

	private ObjectA objectC;

	private ObjectB objectD;

	@Before
	public void setUp() {

		objectA = new ObjectA();
		objectB = new ObjectB();
		objectC = new ObjectA();
		objectD = new ObjectB();

		objectA.setObjectB(objectB);
		objectB.setObjectA(objectC);
		objectC.setObjectB(objectD);
		objectD.setObjectA(objectA);

		objectA.setStrA("a");
		objectB.setStrB("b");
		objectC.setStrA("c");
		objectD.setStrB("d");

	}

	@Test
	public void testLoop() throws Exception {
		System.out.println("***testLoop***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(new Class[] { ObjectA.class, ObjectB.class });
		xStream1.toXML(objectA, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/objectA_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(new Class[] { ObjectA.class, ObjectB.class });
		ObjectA objA_XS = (ObjectA) xStream2.fromXML(new BufferedInputStream(new FileInputStream(
				new File("C:/objectA_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/objectA_XBlink.xml", objectA);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		ObjectA objA_XB = (ObjectA) XBlink.fromXml("C:/objectA_XBlink.xml", ObjectA.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();

	}

}

package org.xblink;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.xblink.model.loop2.ObjectA;
import org.xblink.model.loop2.ObjectB;
import org.xblink.util.WatchTimer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class LoopTest2_Set {

	private ObjectA objectA;

	private ObjectB objectB1;

	private ObjectB objectB2;

	private ObjectB objectB3;

	private ObjectA objectC;

	@Before
	public void setUp() {
		objectA = new ObjectA();
		objectC = new ObjectA();
		objectB1 = new ObjectB();
		objectB2 = new ObjectB();
		objectB3 = new ObjectB();

		Set<ObjectB> objectBs = new HashSet<ObjectB>();
		objectA.setObjectBs_Set(objectBs);

		objectBs.add(objectB1);
		objectBs.add(objectB2);
		objectBs.add(objectB3);

		objectB1.setObjectA(objectC);
		objectB2.setObjectA(objectC);
		objectB3.setObjectA(objectC);

		objectB1.setStrB("b1");
		objectB2.setStrB("b2");
		objectB3.setStrB("b3");

		objectC.setObjectBs_Set(objectBs);
	}

	@Test
	public void testLoop2() throws Exception {
		System.out.println("***testLoop2***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(new Class[] { ObjectA.class, ObjectB.class });
		xStream1.toXML(objectA, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/objectA_XStream_Loop2_Set.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(new Class[] { ObjectA.class, ObjectB.class });
		ObjectA objA_XS = (ObjectA) xStream2.fromXML(new BufferedInputStream(new FileInputStream(
				new File("C:/objectA_XStream_Loop2_Set.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/objectA_XBlink_Loop2_Set.xml", objectA);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		ObjectA objA_XB = (ObjectA) XBlink
				.fromXml("C:/objectA_XBlink_Loop2_Set.xml", ObjectA.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();

	}

}

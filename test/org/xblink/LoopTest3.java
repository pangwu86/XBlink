package org.xblink;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.xblink.model.loop3.ObjectA;
import org.xblink.model.loop3.ObjectB;
import org.xblink.model.loop3.ObjectC;
import org.xblink.model.loop3.Root;
import org.xblink.util.WatchTimer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class LoopTest3 {

	private Root root;
	private ObjectA objectA;
	private ObjectB objectB;
	private ObjectC objectC;

	@Before
	public void setUp() {
		objectA = new ObjectA();
		objectA.setText("12");
		objectB = new ObjectB();
		objectC = new ObjectC();
		objectC.setObjectA(objectA);
		objectB.setObjectC(objectC);
		root = new Root();
		root.setObjectA(objectA);
		root.setObjectB(objectB);
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
		xStream1.toXML(root, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/objectA_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(new Class[] { ObjectA.class, ObjectB.class });
		Root root = (Root) xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/objectA_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/objectA_XBlink.xml", root);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		Root root2 = (Root) XBlink.fromXml("C:/objectA_XBlink.xml", Root.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();

	}
}

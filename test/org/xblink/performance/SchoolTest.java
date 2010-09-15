package org.xblink.performance;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.performance.school.Grade;
import org.xblink.performance.school.School;
import org.xblink.performance.school.SchoolFactory;
import org.xblink.performance.school.Student;
import org.xblink.util.WatchTimer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class SchoolTest {

	private static School singleSchool;

	private static School[] schoolArray;

	private static List<School> schoolList = new ArrayList<School>();

	private static Set<School> schoolSet = new HashSet<School>();

	private static Map<Integer, School> schoolMap = new HashMap<Integer, School>();

	private static int schoolNumber = 10;

	private static int gradeNumber = 10;

	private static int studentNumber = 10;

	private static Class[] clzs = new Class[] { School.class, Grade.class, Student.class };

	@BeforeClass
	public static void setUp() throws Exception {
		// 单一
		singleSchool = SchoolFactory.getSchool(1, 1);
		// 集合
		List<School> schools = new ArrayList<School>();
		for (int i = 0; i < schoolNumber; i++) {
			schools.add(SchoolFactory.getSchool(gradeNumber, studentNumber));
		}
		schoolArray = new School[schoolNumber];
		for (int i = 0; i < schoolNumber; i++) {
			School sch = schools.get(i);
			schoolArray[i] = sch;
			schoolList.add(sch);
			schoolSet.add(sch);
			schoolMap.put(i, sch);
		}
	}

	@Test
	public void testFirst() throws Exception {
		System.out.println("***第一次运行测试，主要是为了静态块加载，不算在测试结果内。***");
		testSingle_inner();
	}

	@Test
	public void testSingle() throws Exception {
		System.out.println("***testSingle***");
		testSingle_inner();
	}

	private void testSingle_inner() throws Exception {
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(clzs);
		xStream1.toXML(singleSchool, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/singleSchool_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(clzs);
		xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/singleSchool_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/singleSchool_XBlink.xml", singleSchool);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XBlink.fromXml("C:/singleSchool_XBlink.xml", School.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();
	}

	@Test
	public void testArray() throws Exception {
		System.out.println("***testArray***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(clzs);
		xStream1.toXML(schoolArray, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/schoolArray_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(clzs);
		xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/schoolArray_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/schoolArray_XBlink.xml", schoolArray);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XBlink.fromXml("C:/schoolArray_XBlink.xml", School.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();
	}

	@Test
	public void testList() throws Exception {
		System.out.println("***testList***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(clzs);
		xStream1.toXML(schoolList, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/schoolList_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(clzs);
		xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/schoolList_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/schoolList_XBlink.xml", schoolList);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XBlink.fromXml("C:/schoolList_XBlink.xml", School.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();
	}

	@Test
	public void testSet() throws Exception {
		System.out.println("***testSet***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(clzs);
		xStream1.toXML(schoolSet, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/schoolSet_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(clzs);
		xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/schoolSet_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/schoolSet_XBlink.xml", schoolSet);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XBlink.fromXml("C:/schoolSet_XBlink.xml", School.class);
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();
	}

	@Test
	public void testMap() throws Exception {
		System.out.println("***testMap***");
		WatchTimer timer = new WatchTimer();
		// XStream
		System.out.println("XStream:");
		timer.reset();
		XStream xStream1 = new XStream(new XppDriver());
		xStream1.processAnnotations(clzs);
		xStream1.toXML(schoolMap, new BufferedOutputStream(new FileOutputStream(new File(
				"C:/schoolMap_XStream.xml"))));
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		XStream xStream2 = new XStream(new XppDriver());
		xStream2.processAnnotations(clzs);
		xStream2.fromXML(new BufferedInputStream(new FileInputStream(new File(
				"C:/schoolMap_XStream.xml"))));
		System.out.println("反序列化：" + timer.getTimer());

		// XBlink
		System.out.println("XBlink:");
		timer.reset();
		XBlink.toXml("C:/schoolMap_XBlink.xml", schoolMap);
		System.out.println("序列化：" + timer.getTimer());
		timer.reset();
		Map<Integer, School> result = (Map) XBlink.fromXml("C:/schoolMap_XBlink.xml", new Class[] {
				Integer.class, School.class });
		System.out.println("反序列化：" + timer.getTimer());

		System.out.println();
	}

}

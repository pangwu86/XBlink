package org.xblink;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xblink.model.SchoolFactory;
import org.xblink.model.School.School;
import org.xblink.util.OsUtil;

public class XBlinkTest {

	static String xmlPath;

	static String xmlName;

	private School school;

	static int gruNum;

	static int stuNum;

	@BeforeClass
	public static void init() {
		xmlName = "Test.xml";
		if (OsUtil.isWindows()) {
			xmlPath = "C:/";
		}
		if (OsUtil.isLinux()) {
			xmlPath = "/tmp/";
		}
		xmlName = xmlPath + xmlName;
		gruNum = 5;
		stuNum = 3;
	}

	@Before
	public void setUp() throws Exception {
		school = SchoolFactory.getSchool(gruNum, stuNum);
	}

	@Test
	public void testToXML() throws Exception {
		XBlink.toXml(xmlName, school);
		File xml = new File(xmlName);
		Assert.assertTrue(xml.exists());
	}

	@Test
	public void testFromXML() throws Exception {
		School school = (School) XBlink.fromXml(xmlName, School.class);
		Assert.assertTrue(school != null);
		Assert.assertTrue(school.getGrades().size() == gruNum);
		if (school.getGrades().size() > 0) {
			Assert.assertTrue(school.getGrades().get(0).getStudents().length == stuNum);
		}
	}

}

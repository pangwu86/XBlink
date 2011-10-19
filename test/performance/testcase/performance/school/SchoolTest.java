package performance.testcase.performance.school;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.util.WatchTimer;

import performance.model.school.Book;
import performance.model.school.Grade;
import performance.model.school.School;
import performance.model.school.SchoolFactory;
import performance.model.school.Student;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class SchoolTest {

	private static XStream xstream;
	private static WatchTimer watchTimer;

	static {
		Class[] clzs = new Class[] { School.class, Grade.class, Student.class, Book.class };
		xstream = new XStream(new Xpp3Driver());
		xstream.processAnnotations(clzs);

		XBlink.registerClassesToBeUsed(clzs);

		watchTimer = new WatchTimer();
	}

	@Test
	public void s0() throws Exception {
		School s1 = SchoolFactory.getSchool(1, 1, 1);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	public void s1_1_1() throws Exception {
		School s1 = SchoolFactory.getSchool(1, 1, 1);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	public void s10_10_10() throws Exception {
		School s1 = SchoolFactory.getSchool(10, 10, 10);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	public void s20_20_20() throws Exception {
		School s1 = SchoolFactory.getSchool(20, 20, 20);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	@Test
	public void s1_1_1_10Times() throws Exception {
		int count = 10;
		System.out.println("次数测试： " + count);
		School s1 = SchoolFactory.getSchool(1, 1, 1);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	@Test
	public void s1_1_1_100Times() throws Exception {
		int count = 100;
		System.out.println("次数测试： " + count);
		School s1 = SchoolFactory.getSchool(1, 1, 1);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

	@Test
	public void s1_1_1_1000Times() throws Exception {
		int count = 1000;
		System.out.println("次数测试： " + count);
		School s1 = SchoolFactory.getSchool(1, 1, 1);
		String xs = null;
		String xb = null;
		System.out.println("序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xs = xstream.toXML(s1);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xb = XBlink.toXml(s1);
		System.out.println("XBlink: " + watchTimer.getTimer());

		System.out.println("");
		System.out.println("反序列化");
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			xstream.fromXML(xs);
		System.out.println("XStream: " + watchTimer.getTimer());
		watchTimer.reset();
		for (int i = 0; i < count; i++)
			XBlink.fromXml(xb);
		System.out.println("XBlink: " + watchTimer.getTimer());
		System.out.println();
	}

}

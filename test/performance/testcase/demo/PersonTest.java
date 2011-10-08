package performance.testcase.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.util.WatchTimer;

import performance.model.Person;
import performance.model.PhoneNumber;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class PersonTest {

	private static XStream xStream = new XStream(new Xpp3Driver());
	private static WatchTimer watchTimer = new WatchTimer();

	private static Person joe;
	private static String xbXML;
	private static String xsXML;

	static {
		Class[] clzs = { Person.class, PhoneNumber.class };
		XBlink.registerClassesToBeUsed(clzs);
		xStream.processAnnotations(clzs);

		joe = getPerson();
		xbXML = XBlink.toXml(joe);
		xsXML = xStream.toXML(joe);
	}

	public static Person getPerson() {
		PhoneNumber phone = new PhoneNumber();
		phone.setCode(123);
		phone.setNumber("1234-456");

		PhoneNumber fax = new PhoneNumber();
		fax.setCode(123);
		fax.setNumber("9999-999");

		Person joe = new Person();
		joe.setFirstname("Joe");
		joe.setLastname("Walnes");
		joe.setPhone(phone);
		joe.setFax(fax);
		return joe;
	}

	@Test
	public void testForFirstTime() throws Exception {
		// 预热一次
		XBlink.fromXml(XBlink.toXml(joe));
		xStream.fromXML(xStream.toXML(joe));
	}

	@Test
	public void XBlink() throws Exception {
		System.out.println("XBlink序列化反序列化Person对象测试\n");

		watchTimer.reset();
		XBlink.toXml(joe);
		String useTime = watchTimer.getTimer();
		System.out.println("XBlink序列化耗时：" + useTime);

		watchTimer.reset();
		Person anthorJoe = (Person) XBlink.fromXml(xbXML);
		useTime = watchTimer.getTimer();
		System.out.println("XBlink反序列化耗时：" + useTime);

		assertTrue(anthorJoe != null);
		assertTrue(anthorJoe.getFirstname().equals("Joe"));
		assertTrue(anthorJoe.getLastname().equals("Walnes"));
		assertTrue(anthorJoe.getPhone().getCode() == 123);
		assertTrue(anthorJoe.getPhone().getNumber().equals("1234-456"));
		assertTrue(anthorJoe.getFax().getCode() == 123);
		assertTrue(anthorJoe.getFax().getNumber().equals("9999-999"));
		System.out.println();
	}

	@Test
	public void XStream() throws Exception {
		System.out.println("XStream序列化反序列化Person对象测试\n");

		watchTimer.reset();
		xStream.toXML(joe);
		String useTime = watchTimer.getTimer();
		System.out.println("XStream序列化耗时：" + useTime);

		watchTimer.reset();
		Person anthorJoe = (Person) xStream.fromXML(xsXML);
		useTime = watchTimer.getTimer();
		System.out.println("XStream反序列化耗时：" + useTime);

		assertTrue(anthorJoe != null);
		assertTrue(anthorJoe.getFirstname().equals("Joe"));
		assertTrue(anthorJoe.getLastname().equals("Walnes"));
		assertTrue(anthorJoe.getPhone().getCode() == 123);
		assertTrue(anthorJoe.getPhone().getNumber().equals("1234-456"));
		assertTrue(anthorJoe.getFax().getCode() == 123);
		assertTrue(anthorJoe.getFax().getNumber().equals("9999-999"));
		System.out.println();
	}

	@Test
	public void XBlinkVsXStream() throws Exception {
		System.out.println("XBlink VS XStream 性能测试\n");

		System.out.println("序列化1次Person对象\n");
		testToXMLAnyTime(1);
		System.out.println("\n序列化10次Person对象\n");
		testToXMLAnyTime(10);
		System.out.println("\n序列化100次Person对象\n");
		testToXMLAnyTime(100);
		System.out.println("\n序列化1000次Person对象\n");
		testToXMLAnyTime(1000);
		System.out.println("\n序列化10000次Person对象\n");
		testToXMLAnyTime(10000);

		System.out.println("\n反序列化1次Person对象\n");
		testFromXMLAnyTime(1);
		System.out.println("\n反序列化10次Person对象\n");
		testFromXMLAnyTime(10);
		System.out.println("\n反序列化100次Person对象\n");
		testFromXMLAnyTime(100);
		System.out.println("\n反序列化1000次Person对象\n");
		testFromXMLAnyTime(1000);
		System.out.println("\n反序列化10000次Person对象\n");
		testFromXMLAnyTime(10000);

	}

	public void testToXMLAnyTime(int n) {
		watchTimer.reset();
		for (int i = 0; i < n; i++) {
			XBlink.toXml(joe);
		}
		String useTime = watchTimer.getTimer();
		System.out.println("XBlink序列化耗时：" + useTime);

		watchTimer.reset();
		for (int i = 0; i < n; i++) {
			xStream.toXML(joe);
		}
		String useTime2 = watchTimer.getTimer();
		System.out.println("XStream序列化耗时：" + useTime2);
	}

	public void testFromXMLAnyTime(int n) {
		watchTimer.reset();
		for (int i = 0; i < n; i++) {
			XBlink.fromXml(xbXML);
		}
		String useTime = watchTimer.getTimer();
		System.out.println("XBlink反序列化耗时：" + useTime);

		watchTimer.reset();
		for (int i = 0; i < n; i++) {
			xStream.fromXML(xsXML);
		}
		String useTime2 = watchTimer.getTimer();
		System.out.println("XStream反序列化耗时：" + useTime2);
	}

}

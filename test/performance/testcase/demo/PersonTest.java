package performance.testcase.demo;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.xblink.XBlink;

import performance.model.Person;
import performance.model.PhoneNumber;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class PersonTest {

	public Person getPerson() {
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
	public void testToXml() throws Exception {
		XBlink.registerClassToBeUsed(Person.class);
		XBlink.toXml(getPerson(), new File("C:/Joe_xb.xml"));
		XBlink.toAny(getPerson(), "XML", new File("C:/Joe_xb.xml"));
		assertTrue(new File("C:/Joe_xb.xml").exists());
	}

	@Test
	public void testFromXml() throws Exception {
		XBlink.registerClassToBeUsed(Person.class);
		Person joe = (Person) XBlink.fromXml(new File("C:/Joe_xb.xml"));
		assertTrue(joe != null);
		assertTrue(joe.getFirstname().equals("Joe"));
		assertTrue(joe.getLastname().equals("Walnes"));
		assertTrue(joe.getPhone().getCode() == 123);
		assertTrue(joe.getPhone().getNumber().equals("1234-456"));
		assertTrue(joe.getFax().getCode() == 123);
		assertTrue(joe.getFax().getNumber().equals("9999-999"));
	}

	@Test
	public void testXStream() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(Person.class);
		Person joe = (Person) xStream.fromXML(xStream.toXML(getPerson()));
	}

}

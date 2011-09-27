package performance.testcase.demo;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.xblink.XBlink;

import performance.model.Person;
import performance.model.PhoneNumber;

public class PersonTest {

	@Test
	public void testToXml() throws Exception {
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

		XBlink.toXml(joe, new File("C:/Joe_xb.xml"));
		assertTrue(new File("C:/Joe_xb.xml").exists());
	}

	@Test
	public void testFromXml() throws Exception {
		Person joe = (Person) XBlink.fromXml(new File("C:/Joe_xb.xml"), Person.class);
		assertTrue(joe != null);
		assertTrue(joe.getFirstname().equals("Joe"));
		assertTrue(joe.getLastname().equals("Walnes"));
		assertTrue(joe.getPhone().getCode() == 123);
		assertTrue(joe.getPhone().getNumber().equals("1234-456"));
		assertTrue(joe.getFax().getCode() == 123);
		assertTrue(joe.getFax().getNumber().equals("9999-999"));
	}

}

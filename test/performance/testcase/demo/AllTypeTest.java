package performance.testcase.demo;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.xblink.XBlink;

import performance.model.AllTypeObject;
import performance.model.EnumForSeason;
import performance.model.Person;
import performance.model.PhoneNumber;

public class AllTypeTest {

	@Test
	public void testName() throws Exception {
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

		List<String> strs = new LinkedList<String>();
		strs.add("a123");
		strs.add("b456");
		strs.add("c789");

		Set<Object> objs = new LinkedHashSet<Object>();
		objs.add("nnn");
		objs.add(UUID.randomUUID());
		objs.add(joe);

		Map<Integer, Object> clzMap = new ConcurrentHashMap<Integer, Object>();
		clzMap.put(1, "cvcvcv");
		clzMap.put(2, joe);
		clzMap.put(3, fax);
		clzMap.put(4, phone);

		AllTypeObject allTypeObject = new AllTypeObject();
		allTypeObject.setAbc("cnvdjfdf");
		allTypeObject.setaDate(new Date());
		allTypeObject.setI(34);
		allTypeObject.setEnumForSeason(EnumForSeason.SPRING);
		allTypeObject.setNum(new long[] { 3, 545, 676767 });
		allTypeObject.setStrs(strs);
		allTypeObject.setObjs(objs);
		allTypeObject.setClzMap(clzMap);

		String xml = XBlink.toXml(allTypeObject);
		System.out.println(xml);

		AllTypeObject b = (AllTypeObject) XBlink.fromXml(xml);

	}
}

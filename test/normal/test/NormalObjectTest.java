package normal.test;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import normal.model.NormalObject;

import org.junit.Test;

import performance.model.EnumForSeason;
import performance.model.Person;
import performance.model.PhoneNumber;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class NormalObjectTest {

	public NormalObject getNormalObject() {
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
		// strs.add("b456");
		// strs.add("c789");

		Set<Object> objs = new LinkedHashSet<Object>();
		// objs.add("nnn");
		// objs.add(UUID.randomUUID());
		objs.add(joe);

		Map<Integer, Object> objMap = new ConcurrentHashMap<Integer, Object>();
		objMap.put(1, "cvcvcv");
		objMap.put(2, joe);
		// objMap.put(3, fax);
		// objMap.put(4, phone);

		NormalObject no = new NormalObject();
		no.setAbc("cnvdjfdf");
		no.setaDate(new Date());
		no.setI(34);
		no.setEnumForSeason(EnumForSeason.SPRING);
		no.setNum(new long[] { 3, 545, 676767 });
		no.setStrList(strs);
		no.setObjSet(objs);
		no.setObjMap(objMap);
		return no;
	}

	@Test
	public void XStream() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { NormalObject.class });
		String noStr = xStream.toXML(getNormalObject());
		// System.out.println(noStr);
		xStream.fromXML(noStr);
	}

	@Test
	public void XBlink() throws Exception {
		System.out.println(org.xblink.XBlink.toXml(getNormalObject()));
	}
}

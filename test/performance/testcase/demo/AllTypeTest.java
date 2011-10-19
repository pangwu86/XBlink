package performance.testcase.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.xblink.XBlink;

import performance.model.AllTypeObject;
import performance.model.EnumForSeason;
import performance.model.Person;
import performance.model.PhoneNumber;

public class AllTypeTest {

	static Person joe = new Person();
	static PhoneNumber phone = new PhoneNumber();
	static PhoneNumber fax = new PhoneNumber();

	static {
		phone.setCode(123);
		phone.setNumber("1234-456");

		fax.setCode(999);
		fax.setNumber("9999-999");

		joe.setFirstname("Joe");
		joe.setLastname("Walnes");
		joe.setPhone(phone);
		joe.setFax(fax);

		XBlink.registerClassesToBeUsed(new Class[] { Person.class, PhoneNumber.class, AllTypeObject.class,
				EnumForSeason.class });
	}

	// 测试XBlink的正确性

	public AllTypeObject getTestObj() {

		List<String> strs = getList();

		Set<Object> objSet = getSet();

		Map<Integer, Object> objMap = getMap();

		AllTypeObject allTypeObject = new AllTypeObject();
		allTypeObject.setAbc("abc");
		allTypeObject.setaDate(new Date());
		allTypeObject.setI(34);
		allTypeObject.setEnumForSeason(EnumForSeason.SPRING);
		allTypeObject.setNum(new long[] { 3, 555, 676767 });
		allTypeObject.setStrs(strs);
		allTypeObject.setObjSet(objSet);
		allTypeObject.setObjMap(objMap);

		return allTypeObject;
	}

	public List<String> getList() {
		List<String> strs = new ArrayList<String>();
		strs.add("a123");
		strs.add("b456");
		strs.add("c789");
		return strs;
	}

	public Set<Object> getSet() {
		Set<Object> objSet = new LinkedHashSet<Object>();
		objSet.add("nnn");
		objSet.add(UUID.randomUUID());
		objSet.add(joe);
		return objSet;
	}

	public Map getMap() {
		Map<Integer, Object> objMap = new HashMap<Integer, Object>();
		objMap.put(1, "teststr");
		objMap.put(2, joe);
		objMap.put(3, fax);
		objMap.put(4, phone);
		return objMap;
	}

	@Test
	public void testPrimitive() throws Exception {
		int x = 7;

		// 序列化
		String xml = XBlink.toXml(x);

		// 反序列化
		int y = (Integer) XBlink.fromXml(xml);

		// 验证
		Assert.assertTrue(y == x);
	}

	@Test
	public void testEnum() throws Exception {
		EnumForSeason en = EnumForSeason.SUMMER;

		// 序列化
		String xml = XBlink.toXml(en);

		// 反序列化
		EnumForSeason en2 = (EnumForSeason) XBlink.fromXml(xml);

		// 验证
		Assert.assertTrue(en.equals(en2));
	}

	@Test
	public void testArray() throws Exception {
		// 对象类型数组
		Object[] objs = { 1, "Str", joe };

		// 序列化
		String xml1 = XBlink.toXml(objs);

		// 反序列化
		Object[] objs2 = (Object[]) XBlink.fromXml(xml1);

		// 验证
		Assert.assertTrue(objs[0].equals(objs2[0]));
		Assert.assertTrue(objs[1].equals(objs2[1]));
		Assert.assertTrue(((Person) objs[2]).getFirstname().equals(((Person) objs2[2]).getFirstname()));
		Assert.assertTrue(((Person) objs[2]).getLastname().equals(((Person) objs2[2]).getLastname()));
		Assert.assertTrue(((Person) objs[2]).getFax().getNumber().equals(((Person) objs2[2]).getFax().getNumber()));
		Assert.assertTrue(((Person) objs[2]).getPhone().getCode() == ((Person) objs2[2]).getPhone().getCode());

		// 基本类型数组
		int[] num = { 1, 2, 3 };
		// 序列化
		String xml2 = XBlink.toXml(num);

		// 反序列化
		int[] num2 = (int[]) XBlink.fromXml(xml2);

		// 验证
		for (int i = 0; i < num.length; i++) {
			Assert.assertTrue(num[i] == num2[i]);
		}

	}

	@Test
	public void testList() throws Exception {
		List<String> strs = getList();

		// 序列化
		String xml = XBlink.toXml(strs);

		// 反序列化
		List<String> strs2 = (List<String>) XBlink.fromXml(xml);

		// 验证
		for (int i = 0; i < strs2.size(); i++) {
			Assert.assertTrue(strs.contains(strs2.get(i)));
		}
	}

	@Test
	public void testSet() throws Exception {
		Set set = getSet();

		// 序列化
		String xml = XBlink.toXml(set);

		// 反序列化
		Set set2 = (Set) XBlink.fromXml(xml);

		// 验证
		for (Iterator iterator = set2.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if (object.getClass().equals(String.class)) {
				Assert.assertTrue("nnn".equals(object));
			}
			if (object.getClass().equals(Person.class)) {
				Assert.assertTrue(joe.getFirstname().equals(((Person) object).getFirstname()));
				Assert.assertTrue(joe.getLastname().equals(((Person) object).getLastname()));
				Assert.assertTrue(joe.getFax().getNumber().equals(((Person) object).getFax().getNumber()));
			}

		}

	}

	@Test
	public void testMap() throws Exception {
		Map objMap = getMap();

		// 序列化
		String xml = XBlink.toXml(objMap);

		// 反序列化
		Map objMap2 = (Map) XBlink.fromXml(xml);

		// 验证
		Assert.assertTrue(objMap.get(1).equals(objMap2.get(1)));
		Assert.assertTrue(((Person) objMap.get(2)).getFirstname().equals(((Person) objMap2.get(2)).getFirstname()));
		Assert.assertTrue(((PhoneNumber) objMap.get(3)).getNumber().equals(((PhoneNumber) objMap2.get(3)).getNumber()));
		Assert.assertTrue(((PhoneNumber) objMap.get(4)).getCode() == ((PhoneNumber) objMap2.get(4)).getCode());

	}

	@Test
	public void testAllTypeObj() throws Exception {
		// 序列化
		AllTypeObject testObj = getTestObj();
		String xml = XBlink.toXml(testObj);

		// 反序列化
		AllTypeObject testObj2 = (AllTypeObject) XBlink.fromXml(xml);

		// 验证
		Assert.assertTrue(testObj2.getAbc().endsWith("abc"));
		Assert.assertTrue(testObj2.getaDate() != null);
		Assert.assertTrue(testObj2.getI() == 34);
		Assert.assertTrue(testObj2.getEnumForSeason().equals(EnumForSeason.SPRING));
		long[] testArray = testObj2.getNum();
		Assert.assertTrue(testArray[0] == 3);
		Assert.assertTrue(testArray[1] == 555);
		Assert.assertTrue(testArray[2] == 676767);
		List<String> strs = testObj2.getStrs();
		Assert.assertTrue(strs.contains("a123"));
		Assert.assertTrue(strs.contains("b456"));
		Assert.assertTrue(strs.contains("c789"));
		Map<Integer, Object> objMap = testObj2.getObjMap();
		Assert.assertTrue(objMap.get(1).equals("teststr"));
		Assert.assertTrue(objMap.get(2) != null);
		Assert.assertTrue(objMap.get(3) != null);
		Assert.assertTrue(objMap.get(4) != null);
		Person joe = (Person) objMap.get(2);
		Assert.assertTrue(objMap.get(3) == joe.getFax());
		Assert.assertTrue(objMap.get(4) == joe.getPhone());
		Set<Object> objSet = testObj2.getObjSet();
		Assert.assertTrue(objSet.contains("nnn"));
		Assert.assertTrue(objSet.contains(joe));
	}
}

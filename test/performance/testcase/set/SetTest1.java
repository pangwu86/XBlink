package performance.testcase.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.xblink.XBlink;

public class SetTest1 {

	@Test
	public void set() throws Exception {
		Set<Object> objSet1 = new HashSet<Object>();
		Set<Object> objSet2 = new TreeSet<Object>();

		objSet1.add("123");
		objSet1.add(123);
		objSet1.add(true);

		objSet2.add("c");
		objSet2.add("b");
		objSet2.add("d");
		objSet2.add("a");

		System.out.println(XBlink.toXml(objSet1));
		System.out.println(XBlink.toXml(objSet2));
	}
}

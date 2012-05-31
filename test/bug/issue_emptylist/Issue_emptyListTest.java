package bug.issue_emptylist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xblink.XBlink;

public class Issue_emptyListTest {

	static {
		XBlink.registerClassToBeUsed(ObjWithList.class);
	}

	@Test
	public void toxml() throws Exception {
		ObjWithList o = new ObjWithList();
		String ostr = XBlink.toXml(o);
		System.out.println(ostr);

		ObjWithList o2 = (ObjWithList) XBlink.fromXml(ostr);

		o2.getLl().add(33l);
		o2.getDl().add(22.33d);
		o2.getSl().add("1");

		System.out.println(XBlink.toXml(o2));
	}

	public static void main(String[] args) {
		List<String> sl = new ArrayList<String>();
		List<Double> dl = new ArrayList<Double>();
		List<Long> ll = new ArrayList<Long>();

		System.out.println(sl.equals(dl));
		System.out.println(dl.hashCode());
		System.out.println(ll.hashCode());
	}

}

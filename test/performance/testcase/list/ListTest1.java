package performance.testcase.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xblink.XBlink;

import performance.model.ListA;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class ListTest1 {

	public ListA getListA() {
		ListA listA = new ListA();

		List<String> strs = new ArrayList<String>();
		List<Object> objs = new ArrayList<Object>();
		List unknows = new ArrayList();

		strs.add("123");
		strs.add("fgfgfg");

		objs.add("234");
		objs.add(new Integer(2));

		unknows.add("234");
		unknows.add(new Integer(2));

		listA.setStrs(strs);
		listA.setObjs(objs);
		listA.setUnknows(unknows);

		return listA;
	}

	@Test
	public void xstream() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { ListA.class });

		System.out.println(xStream.toXML(getListA()));

		System.out.println(xStream.toXML(getListA().getObjs()));
		System.out.println(xStream.toXML(getListA().getUnknows()));
	}

	@Test
	public void xblink() throws Exception {
		System.out.println(XBlink.toXml(getListA()));
		System.out.println(XBlink.toXml(getListA().getObjs()));
		System.out.println(XBlink.toXml(getListA().getUnknows()));
	}

}

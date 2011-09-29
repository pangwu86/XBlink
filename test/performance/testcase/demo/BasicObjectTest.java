package performance.testcase.demo;

import org.junit.Test;
import org.xblink.XBConfig;
import org.xblink.XBlink;

import performance.model.BasicObject;

public class BasicObjectTest {

	@Test
	public void testName() throws Exception {
		BasicObject basicObject = new BasicObject();
		basicObject.setName("wupw");
		basicObject.setAddress("87hao");

		System.out.println(XBlink.toXml(basicObject));
	}

	@Test
	public void loop() throws Exception {
		BasicObject b1 = new BasicObject();
		b1.setName("b1");
		b1.setAddress("87hao");

		BasicObject b2 = new BasicObject();
		b2.setName("b2");
		b2.setAddress("dfsdfo");

		BasicObject b3 = new BasicObject();
		b3.setName("b3");
		b3.setAddress("cvxcv");

		BasicObject b4 = new BasicObject();
		b4.setName("b4");
		b4.setAddress("cvcvc");

		b1.setBasicObject(b2);
		b2.setBasicObject(b3);
		b3.setBasicObject(b3);
		b4.setBasicObject(b1);

		XBConfig xbConfig = XBConfig.createXBConfig().setUseRelativePath(false);
		XBlink.setTransientXBConfig(xbConfig);
		System.out.println(XBlink.toXml(b1));
	}

}

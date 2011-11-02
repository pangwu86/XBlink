package bug.issue20;

import org.junit.Test;
import org.xblink.XBlink;

public class Issue14Test {

	@Test
	public void testName() throws Exception {
		GenericObject<String> go = new GenericObject<String>();
		go.setName("jay");
		go.setAbc("abc");

		String xml = XBlink.toXml(go);
		System.out.println(xml);

		// GenericObject<String> go2 = (GenericObject<String>)
		// XBlink.fromXml(xml);
		// TODO 这个暂时还不好解决
	}

}

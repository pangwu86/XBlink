package bug.issue13;

import org.junit.Assert;
import org.junit.Test;
import org.xblink.XBConfig;
import org.xblink.XBlink;

public class Issue13Test {

	static {
		XBlink.registerClassToBeUsed(Man.class);
		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(true));
	}

	private static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><man><name>xiaohei</name><hasJJ>true</hasJJ></man>";

	@Test
	public void testName() throws Exception {
		Man man = new Man();
		man.setHasJJ(true);
		man.setName("xiaohei");

		Assert.assertTrue(xml.equals(XBlink.toXml(man)));

	}

}

package bug.issue9;

import org.junit.Assert;
import org.junit.Test;
import org.xblink.XBConfig;
import org.xblink.XBlink;

public class Issue9Test {

	static {
		XBlink.registerClassToBeUsed(StringObject.class);
		XBlink.registerClassToBeUsed(StringObjectWithAttribute.class);
	}

	private String nullXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><stringObject><str1>123</str1><str2></str2><str3 /></stringObject>";
	private String nullStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><stringObject><str1>123</str1><str2></str2><str3>Null</str3></stringObject>";

	private String nullXMLWithAtt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><stringObjectWithAttribute str1=\"123\" str2=\"\"></stringObjectWithAttribute>";
	private String nullStrXMLWithAtt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><stringObjectWithAttribute str1=\"123\" str2=\"\" str3=\"Null\"></stringObjectWithAttribute>";

	@Test
	public void NullTest() throws Exception {
		StringObject so = new StringObject();

		so.setStr1("123");
		so.setStr2("");
		so.setStr3(null);

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(false).setIgnoreNull(false).setUseStringNull(false));
		System.out.println(XBlink.toXml(so));

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(true).setIgnoreNull(false).setUseStringNull(false));
		String soXML2 = XBlink.toXml(so);

		Assert.assertTrue(nullXML.equals(soXML2));
		System.out.println();

		StringObject so2 = (StringObject) XBlink.fromXml(soXML2);
		Assert.assertTrue(so2.getStr1().equals("123"));
		Assert.assertTrue(so2.getStr2().equals(""));
		// TODO 这里暂时有问题，没有彻底解决, <abc></abc> 与 </abc> 拿到的text都是"" 暂时无法区分
		Assert.assertTrue(so2.getStr3().equals(""));
	}

	@Test
	public void NullStrTest() throws Exception {
		StringObject so = new StringObject();

		so.setStr1("123");
		so.setStr2("");
		so.setStr3(null);

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(false).setIgnoreNull(false).setUseStringNull(true));
		System.out.println(XBlink.toXml(so));

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(true).setIgnoreNull(false).setUseStringNull(true));
		String soXML2 = XBlink.toXml(so);
		Assert.assertTrue(nullStrXML.equals(soXML2));
		System.out.println();

		StringObject so2 = (StringObject) XBlink.fromXml(soXML2);
		Assert.assertTrue(so2.getStr1().equals("123"));
		Assert.assertTrue(so2.getStr2().equals(""));
		Assert.assertTrue(so2.getStr3() == null);
	}

	@Test
	public void NullTestWithAttribute() throws Exception {
		StringObjectWithAttribute so = new StringObjectWithAttribute();

		so.setStr1("123");
		so.setStr2("");
		so.setStr3(null);

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(false).setIgnoreNull(false).setUseStringNull(false));
		System.out.println(XBlink.toXml(so));

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(true).setIgnoreNull(false).setUseStringNull(false));
		String soXML2 = XBlink.toXml(so);

		Assert.assertTrue(nullXMLWithAtt.equals(soXML2));
		System.out.println();

		StringObjectWithAttribute so2 = (StringObjectWithAttribute) XBlink.fromXml(soXML2);
		Assert.assertTrue(so2.getStr1().equals("123"));
		Assert.assertTrue(so2.getStr2().equals(""));
		Assert.assertTrue(so2.getStr3() == null);
	}

	@Test
	public void NullStrTestWithAttribute() throws Exception {
		StringObjectWithAttribute so = new StringObjectWithAttribute();

		so.setStr1("123");
		so.setStr2("");
		so.setStr3(null);

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(false).setIgnoreNull(false).setUseStringNull(true));
		System.out.println(XBlink.toXml(so));

		XBlink.setGlobalXBConfig(XBConfig.newXBConfig().setCompact(true).setIgnoreNull(false).setUseStringNull(true));
		String soXML2 = XBlink.toXml(so);

		Assert.assertTrue(nullStrXMLWithAtt.equals(soXML2));
		System.out.println();

		StringObjectWithAttribute so2 = (StringObjectWithAttribute) XBlink.fromXml(soXML2);
		Assert.assertTrue(so2.getStr1().equals("123"));
		Assert.assertTrue(so2.getStr2().equals(""));
		Assert.assertTrue(so2.getStr3() == null);
	}
}

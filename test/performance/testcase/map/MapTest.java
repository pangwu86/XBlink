package performance.testcase.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.xblink.XBlink;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;

public class MapTest {

	@Test
	public void map() throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("123", new Date());
		strMap.put("456", UUID.randomUUID());

		XStream xStream = new XStream(new Xpp3DomDriver());
		System.out.println(xStream.toXML(strMap));
		System.out.println(XBlink.toXml(strMap));
	}
}

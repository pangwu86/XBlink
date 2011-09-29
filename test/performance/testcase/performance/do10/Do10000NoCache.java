package performance.testcase.performance.do10;

import org.junit.Test;
import org.xblink.XBConfig;
import org.xblink.XBlink;
import org.xblink.util.WatchTimer;

import performance.model.BasicObject;
import performance.testcase.performance.DataCreater;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class Do10000NoCache {

	public static int number = 10000;
	public static BasicObject basicObject = DataCreater.getLoopBasicObject();

	@Test
	public void XBlink() throws Exception {
		XBConfig xbConfig = XBConfig.createXBConfig().setUseRelativePath(false);
		XBlink.setGlobalXBConfig(xbConfig);
		XBlink.closeCache();

		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < number; i++) {
			XBlink.toXml(basicObject);
		}
		String spendTime = timer.getTimer();
		System.out.println("XBlinkNoCache : " + number + " : " + spendTime);
	}

	@Test
	public void XStream() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { BasicObject.class });

		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < number; i++) {
			xStream.toXML(basicObject);
		}
		String spendTime = timer.getTimer();
		System.out.println("XStream : " + number + " : " + spendTime);

	}

}

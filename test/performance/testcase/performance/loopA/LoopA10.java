package performance.testcase.performance.loopA;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xblink.XBConfig;
import org.xblink.XBlink;
import org.xblink.util.WatchTimer;

import performance.model.BasicObject;
import performance.model.LoopA;
import performance.testcase.performance.DataCreater;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class LoopA10 {

	public static int number = 10;
	public static LoopA loopA = DataCreater.getLoopA();

	@Test
	public void XBlink() throws Exception {
		XBConfig xbConfig = XBConfig.createXBConfig().setUseRelativePath(false);
		XBlink.setGlobalXBConfig(xbConfig);

		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < number; i++) {
			XBlink.toXml(loopA);
		}
		String spendTime = timer.getTimer();
		System.out.println("XBlink : " + number + " : " + spendTime);
	}

	@Test
	public void XStream() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { BasicObject.class });

		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < number; i++) {
			xStream.toXML(loopA);
		}
		String spendTime = timer.getTimer();
		System.out.println("XStream : " + number + " : " + spendTime);

	}

	@Test
	public void Simple() throws Exception {
		Serializer serializer = new Persister();
		StringWriter stringWriter = new StringWriter();
		WatchTimer timer = new WatchTimer();
		for (int i = 0; i < number; i++) {
			serializer.write(loopA, stringWriter);
		}
		String spendTime = timer.getTimer();
		System.out.println("Simple : " + number + " : " + spendTime);
		System.out.println();
	}

}

package performance.testcase.loop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import performance.model.LoopA;
import performance.model.LoopB;
import performance.model.LoopC;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class XStreamLoopTest {

	public LoopA getLoopA() {

		LoopA loopA = new LoopA();
		loopA.setaName("AAAA");

		LoopB loopB = new LoopB();
		loopB.setbName("BBBB");

		LoopC loopC = new LoopC();
		loopC.setcName("iCCCC");

		loopA.setLoopBObj(loopB);
		loopA.setLoopCObj(loopC);

		loopB.setLoopCObj(loopC);
		loopC.setLoopAObj(loopA);

		return loopA;
	}

	public LoopA getLoopA2() {

		LoopA loopA = new LoopA();
		loopA.setaName("AAAA");

		LoopB loopB = new LoopB();
		loopB.setbName("BBBB");

		LoopC loopC = new LoopC();
		loopC.setcName("iCCCC");

		loopA.setLoopBObj(loopB);
		loopA.setLoopCObj(loopC);

		loopC.setLoopCObj(loopC);

		return loopA;
	}

	public LoopA getLoopA3() {

		LoopA loopA = new LoopA();
		loopA.setaName("AAAA");
		LoopA loopA2 = new LoopA();
		loopA2.setaName("AAAA2222222");
		LoopA loopA3 = new LoopA();
		loopA3.setaName("AAAA333333");

		List<LoopA> loopAs = new ArrayList<LoopA>();
		loopAs.add(loopA);
		loopAs.add(loopA2);
		loopAs.add(loopA3);

		LoopB loopB = new LoopB();
		loopB.setbName("BBBB");

		loopB.setLoopAList(loopAs);

		LoopC loopC = new LoopC();
		loopC.setcName("iCCCC");

		loopA.setLoopBObj(loopB);
		loopA.setLoopCObj(loopC);

		loopC.setLoopCObj(loopC);

		return loopA;
	}

	public LoopC getLoopC() {
		LoopC loopC = new LoopC();
		loopC.setcName("cdfdfd");

		loopC.setLoopAObj(getLoopA());

		return loopC;
	}

	@Test
	public void toXml1() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { LoopA.class, LoopB.class, LoopC.class });

		String xml = xStream.toXML(getLoopA());

		System.out.println(xml);
	}

	@Test
	public void toXml2() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { LoopA.class, LoopB.class, LoopC.class });

		String xml = xStream.toXML(getLoopA2());

		System.out.println(xml);
	}

	@Test
	public void toJSON1() throws Exception {
		String json = JSON.toJSONString(getLoopA(), SerializerFeature.PrettyFormat);
		System.out.println(json);
	}

	@Test
	public void toJSON1_2() throws Exception {
		String json = JSON.toJSONString(getLoopC(), SerializerFeature.PrettyFormat);
		System.out.println(json);

		XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
		xStream.processAnnotations(new Class[] { LoopA.class, LoopB.class, LoopC.class });

		String xsJson = xStream.toXML(getLoopC());
		System.out.println(xsJson);
	}

	@Test
	public void toJSON2() throws Exception {
		String json = JSON.toJSONString(getLoopA2(), SerializerFeature.PrettyFormat);
		System.out.println(json);

	}

	@Test
	public void toXml3() throws Exception {
		XStream xStream = new XStream(new Xpp3Driver());
		xStream.processAnnotations(new Class[] { LoopA.class, LoopB.class, LoopC.class });

		String xml = xStream.toXML(getLoopA3());

		System.out.println(xml);
	}

	@Test
	public void toJSON3() throws Exception {
		String json = JSON.toJSONString(getLoopA3(), SerializerFeature.PrettyFormat);
		System.out.println(json);

		XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
		xStream.processAnnotations(new Class[] { LoopA.class, LoopB.class, LoopC.class });

		String xsJson = xStream.toXML(getLoopA3());
		System.out.println(xsJson);
	}

}

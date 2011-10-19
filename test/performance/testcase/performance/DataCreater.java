package performance.testcase.performance;

import performance.model.BasicObject;
import performance.model.LoopA;
import performance.model.LoopB;
import performance.model.LoopC;

public class DataCreater {

	public static LoopA getLoopA() {

		LoopA loopA = new LoopA();
		loopA.setaName("AAAA");

		LoopB loopB = new LoopB();
		loopB.setbName("BBBB");

		LoopC loopC = new LoopC();
		loopC.setcName("iCCCC");

		loopA.setLoopBObj(loopB);
		loopA.setLoopCObj(loopC);
		// loopC.setLoopAObj(loopA);

		return loopA;
	}

	public static BasicObject getLoopBasicObject() {
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

		return b1;
	}

}

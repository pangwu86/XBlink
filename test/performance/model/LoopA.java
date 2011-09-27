package performance.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("loopA")
public class LoopA {

	@XStreamAsAttribute
	private String aName;

	private LoopB loopBObj;

	private LoopC loopCObj;

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public LoopB getLoopBObj() {
		return loopBObj;
	}

	public void setLoopBObj(LoopB loopBObj) {
		this.loopBObj = loopBObj;
	}

	public LoopC getLoopCObj() {
		return loopCObj;
	}

	public void setLoopCObj(LoopC loopCObj) {
		this.loopCObj = loopCObj;
	}

}

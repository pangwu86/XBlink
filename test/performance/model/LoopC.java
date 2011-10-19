package performance.model;

import org.simpleframework.xml.Element;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("loopC")
public class LoopC {
	@Element
	private String cName;
	@Element(required = false)
	private LoopB loopBObj;
	@Element(required = false)
	private LoopC loopCObj;
	@Element(required = false)
	private LoopA loopAObj;

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
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

	public LoopA getLoopAObj() {
		return loopAObj;
	}

	public void setLoopAObj(LoopA loopAObj) {
		this.loopAObj = loopAObj;
	}

}

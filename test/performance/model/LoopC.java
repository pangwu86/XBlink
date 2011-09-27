package performance.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("loopC")
public class LoopC {

	@XStreamAsAttribute
	private String cName;

	private LoopB loopBObj;

	private LoopC loopCObj;

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

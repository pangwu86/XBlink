package performance.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("loopB")
public class LoopB {

	@XStreamAsAttribute
	private String bName;

	private LoopC loopCObj;

	private List<LoopA> loopAList;

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public LoopC getLoopCObj() {
		return loopCObj;
	}

	public void setLoopCObj(LoopC loopCObj) {
		this.loopCObj = loopCObj;
	}

	public List<LoopA> getLoopAList() {
		return loopAList;
	}

	public void setLoopAList(List<LoopA> loopAList) {
		this.loopAList = loopAList;
	}

}

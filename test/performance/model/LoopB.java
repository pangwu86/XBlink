package performance.model;

import java.util.List;

import org.simpleframework.xml.Element;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("loopB")
public class LoopB {
	@Element
	private String bName;
	@Element(required = false)
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

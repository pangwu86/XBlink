package performance.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

// @XStreamAlias("")
public class ListA {

	@XStreamAlias("abc")
	private List<String> strs;

	private List<Object> objs;

	private List unknows;

	public List<String> getStrs() {
		return strs;
	}

	public void setStrs(List<String> strs) {
		this.strs = strs;
	}

	public List<Object> getObjs() {
		return objs;
	}

	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}

	public List getUnknows() {
		return unknows;
	}

	public void setUnknows(List unknows) {
		this.unknows = unknows;
	}

}

package bug.issue_emptylist;

import java.util.ArrayList;
import java.util.List;

import org.xblink.annotation.XBlinkAlias;

@XBlinkAlias("listContainer")
public class ObjWithList {

	private List<String> sl = new ArrayList<String>();

	private List<Double> dl = new ArrayList<Double>();

	private List<Long> ll = new ArrayList<Long>();

	public List<String> getSl() {
		return sl;
	}

	public void setSl(List<String> sl) {
		this.sl = sl;
	}

	public List<Double> getDl() {
		return dl;
	}

	public void setDl(List<Double> dl) {
		this.dl = dl;
	}

	public List<Long> getLl() {
		return ll;
	}

	public void setLl(List<Long> ll) {
		this.ll = ll;
	}

}

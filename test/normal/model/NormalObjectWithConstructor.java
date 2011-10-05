package normal.model;

import java.util.List;
import java.util.Set;

public class NormalObjectWithConstructor {

	private int i;

	private String abc;

	private List<String> strList;

	private Set<Object> objSet;

	public NormalObjectWithConstructor(int i, String abc, List<String> strList, Set<Object> objSet) {
		super();
		this.i = i;
		this.abc = abc;
		this.strList = strList;
		this.objSet = objSet;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public Set<Object> getObjSet() {
		return objSet;
	}

	public void setObjSet(Set<Object> objSet) {
		this.objSet = objSet;
	}

}

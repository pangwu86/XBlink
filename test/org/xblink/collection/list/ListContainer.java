package org.xblink.collection.list;

import java.util.List;

import org.xblink.annotations.XBlinkAsList;

public class ListContainer {
	
	@XBlinkAsList
	private List<String> list1;
	
	@XBlinkAsList
	private List<ListObject> list2;

	public List<String> getList1() {
		return list1;
	}

	public void setList1(List<String> list1) {
		this.list1 = list1;
	}

	public List<ListObject> getList2() {
		return list2;
	}

	public void setList2(List<ListObject> list2) {
		this.list2 = list2;
	}
}

package org.xblink.model.doubleList;

import java.util.List;

import org.xblink.annotations.XBlinkAsList;

public class DoubleList {

	@XBlinkAsList
	private List<Double> doubles;

	public List<Double> getDoubles() {
		return doubles;
	}

	public void setDoubles(List<Double> doubles) {
		this.doubles = doubles;
	}

}

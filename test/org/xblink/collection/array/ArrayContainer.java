package org.xblink.collection.array;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.annotations.XBlinkAsArray;

public class ArrayContainer {

	@XBlinkAsArray
	private int[] array1;

	@XBlinkAsArray
	private Integer[] array2;

	@XBlinkAsArray
	private ArrayObject[] array3;

	@XBlinkAsArray
	private List<String>[] array4;

	@XBlinkAsArray
	private Set<String>[] array5;

	@XBlinkAsArray
	private Map<Integer, String>[] array6;

	public int[] getArray1() {
		return array1;
	}

	public void setArray1(int[] array1) {
		this.array1 = array1;
	}

	public Integer[] getArray2() {
		return array2;
	}

	public void setArray2(Integer[] array2) {
		this.array2 = array2;
	}

	public ArrayObject[] getArray3() {
		return array3;
	}

	public void setArray3(ArrayObject[] array3) {
		this.array3 = array3;
	}

	public List<String>[] getArray4() {
		return array4;
	}

	public void setArray4(List<String>[] array4) {
		this.array4 = array4;
	}

	public Set<String>[] getArray5() {
		return array5;
	}

	public void setArray5(Set<String>[] array5) {
		this.array5 = array5;
	}

	public Map<Integer, String>[] getArray6() {
		return array6;
	}

	public void setArray6(Map<Integer, String>[] array6) {
		this.array6 = array6;
	}

}

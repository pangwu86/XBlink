package org.xblink;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xblink.model.doubleList.DoubleList;

public class DoubleListTest {

	private DoubleList doubleList;

	@Before
	public void setUp() {
		doubleList = new DoubleList();
		List<Double> doubles = new ArrayList<Double>();
		doubleList.setDoubles(doubles);
		doubles.add(33.444d);
		doubles.add(-97.5d);
	}

	@Test
	public void testDoubleList() throws Exception {
		// XBlink
		System.out.println("XBlink:");
		XBlink.toXml("C:/DoubleList_XBlink.xml", doubleList);
		DoubleList doubleList = (DoubleList) XBlink.fromXml("C:/DoubleList_XBlink.xml",
				DoubleList.class);
		List<Double> doubles = doubleList.getDoubles();
		Assert.assertTrue(doubles.get(0) == 33.444d);
		Assert.assertTrue(doubles.get(1) == -97.5d);
	}
}

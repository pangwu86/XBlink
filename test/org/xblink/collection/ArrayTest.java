package org.xblink.collection;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.collection.array.ArrayContainer;

public class ArrayTest {

	private String ac1 = "C:/Array1.xml";
	private String ac2 = "C:/Array2.xml";
	private String ac3 = "C:/Array3.xml";
	private String ac4 = "C:/Array4.xml";

	public static ArrayContainer getArrayContainer() {
		return new ArrayContainer();
	}

	@Test
	public void testArray1() throws Exception {

		ArrayContainer ac = getArrayContainer();

		int[] array1 = new int[] { 1, 2, 3 };

		ac.setArray1(array1);

		XBlink.toXml(ac1, ac);

		ArrayContainer re = (ArrayContainer) XBlink.fromXml(ac1, ArrayContainer.class);

		assertTrue(re.getArray1() != null);
		assertTrue(re.getArray1()[0] == 1);
		assertTrue(re.getArray1()[1] == 2);
		assertTrue(re.getArray1()[2] == 3);

	}

	@Test
	public void testArray2() throws Exception {

		ArrayContainer ac = getArrayContainer();

		Integer[] array2 = new Integer[] { 1, 2, 3 };

		ac.setArray2(array2);

		XBlink.toXml(ac2, ac);

		ArrayContainer re = (ArrayContainer) XBlink.fromXml(ac2, ArrayContainer.class);

		assertTrue(re.getArray2() != null);
		assertTrue(re.getArray2()[0] == 1);
		assertTrue(re.getArray2()[1] == 2);
		assertTrue(re.getArray2()[2] == 3);

	}
}

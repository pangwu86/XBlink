package org.xblink.collection;

import java.lang.reflect.Array;

import org.junit.Test;

public class SetTest {

	@Test
	public void testSet1() throws Exception {

	}

	public static void main(String args[]) {
		int[] ints = new int[]{1,2};
		printType(ints);
	}

	private static void printType(Object object) {
		Class type = object.getClass();
		if (type.isArray()) {
			Class elementType = type.getComponentType();
			if(int.class == elementType){
				System.out.println("It's int");
			}
			System.out.println(elementType.getName());
			System.out.println(elementType.getSimpleName());
			System.out.println("Array of: " + elementType);
			System.out.println(" Length: " + Array.getLength(object));
		}
	}

}

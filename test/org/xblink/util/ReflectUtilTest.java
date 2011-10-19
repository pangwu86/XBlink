package org.xblink.util;

import java.lang.reflect.Field;

import org.junit.Test;

import performance.model.BasicObject;

public class ReflectUtilTest {

	@Test
	public void testname() throws Exception {
		System.out.println("getDeclaredFields");
		for (Field f : BasicObject.class.getDeclaredFields()) {
			System.out.println(f.getName());
		}
		System.out.println("getFields");
		for (Field f : BasicObject.class.getFields()) {
			System.out.println(f.getName());
		}
	}
}

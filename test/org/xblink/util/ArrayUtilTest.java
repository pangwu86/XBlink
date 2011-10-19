package org.xblink.util;

import junit.framework.Assert;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testGetClass() throws Exception {

		Assert.assertTrue(int.class == ArrayUtil.getComponentClass(new int[0].getClass().getName()));
		Assert.assertTrue(byte.class == ArrayUtil.getComponentClass(new byte[0].getClass().getName()));
		Assert.assertTrue(short.class == ArrayUtil.getComponentClass(new short[0].getClass().getName()));
		Assert.assertTrue(long.class == ArrayUtil.getComponentClass(new long[0].getClass().getName()));
		Assert.assertTrue(float.class == ArrayUtil.getComponentClass(new float[0].getClass().getName()));
		Assert.assertTrue(double.class == ArrayUtil.getComponentClass(new double[0].getClass().getName()));
		Assert.assertTrue(boolean.class == ArrayUtil.getComponentClass(new boolean[0].getClass().getName()));
		Assert.assertTrue(char.class == ArrayUtil.getComponentClass(new char[0].getClass().getName()));

		Assert.assertTrue(String.class == ArrayUtil.getComponentClass(new String[0].getClass().getName()));
		Assert.assertTrue(Object.class == ArrayUtil.getComponentClass(new Object[0].getClass().getName()));

		// 多维数组
		Assert.assertTrue(String.class == ArrayUtil.getComponentClass(new String[0][0][0].getClass().getName()));
		Assert.assertTrue(Object.class == ArrayUtil.getComponentClass(new Object[0][0].getClass().getName()));
	}

	@Test
	public void testGetComponentClassByArrayName() throws Exception {
		String n1 = "array-java.lang.String";
		Assert.assertTrue(String.class == ArrayUtil.getComponentClassByArrayName(n1));

	}

}

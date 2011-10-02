package org.xblink.util;

import static org.xblink.util.TypeUtil.isCollectionType;
import static org.xblink.util.TypeUtil.isMapType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class TypeUtilTest {

	@Test
	public void testName1() throws Exception {
		int[] ints = new int[10];
		long[] longs = new long[10];
		short[] shorts = new short[10];
		byte[] bytes = new byte[10];
		boolean[] booleans = new boolean[10];
		char[] chars = new char[10];
		float[] floats = new float[10];
		double[] doubles = new double[10];

		System.out.println(ints.getClass());
		System.out.println(longs.getClass());
		System.out.println(shorts.getClass());
		System.out.println(bytes.getClass());
		System.out.println(booleans.getClass());
		System.out.println(chars.getClass());
		System.out.println(floats.getClass());
		System.out.println(doubles.getClass());

		System.out.println(ints.getClass().getComponentType());

	}

	@Test
	public void testName2() throws Exception {
		Integer[] ints = new Integer[10];
		Long[] longs = new Long[10];
		Short[] shorts = new Short[10];
		Byte[] bytes = new Byte[10];
		Boolean[] booleans = new Boolean[10];
		Character[] chars = new Character[10];
		Float[] floats = new Float[10];
		Double[] doubles = new Double[10];

		System.out.println(ints.getClass());
		System.out.println(longs.getClass());
		System.out.println(shorts.getClass());
		System.out.println(bytes.getClass());
		System.out.println(booleans.getClass());
		System.out.println(chars.getClass());
		System.out.println(floats.getClass());
		System.out.println(doubles.getClass());
	}

	@Test
	public void testName3() throws Exception {
		Object[] objs = new Object[10];
		Class[] clzs = new Class[10];
		List<Object> objList = new ArrayList<Object>();
		Set<String> strs = new HashSet<String>();
		Map<String, String> strMap = new HashMap<String, String>();
		System.out.println(objs.getClass());
		System.out.println(clzs.getClass());
		System.out.println(objList.getClass());
		System.out.println(strs.getClass());
		System.out.println(strMap.getClass());
	}

	@Test
	public void isCollectionTypeTest() throws Exception {
		Object[] objs = new Object[10];
		Class[] clzs = new Class[10];
		long[] longs = new long[10];
		short[] shorts = new short[10];
		List<Object> objList = new ArrayList<Object>();
		List<Class> clzList = new ArrayList<Class>();
		Set<String> strs = new HashSet<String>();
		Map<String, String> strMap = new HashMap<String, String>();
		String abd = "123";
		Integer int1 = 34;

		Assert.assertTrue(isCollectionType(objs.getClass()));
		Assert.assertTrue(isCollectionType(clzs.getClass()));
		Assert.assertTrue(isCollectionType(longs.getClass()));
		Assert.assertTrue(isCollectionType(shorts.getClass()));
		Assert.assertTrue(isCollectionType(objList.getClass()));
		Assert.assertTrue(isCollectionType(clzList.getClass()));
		Assert.assertTrue(isCollectionType(strs.getClass()));

		Assert.assertTrue(!isCollectionType(strMap.getClass()));
		Assert.assertTrue(!isCollectionType(abd.getClass()));
		Assert.assertTrue(!isCollectionType(int1.getClass()));
	}

	@Test
	public void isMapTypeTest() throws Exception {
		Object[] objs = new Object[10];
		Class[] clzs = new Class[10];
		long[] longs = new long[10];
		short[] shorts = new short[10];
		List<Object> objList = new ArrayList<Object>();
		List<Class> clzList = new ArrayList<Class>();
		Set<String> strs = new HashSet<String>();
		Map<String, String> strMap = new HashMap<String, String>();
		String abd = "123";
		Integer int1 = 34;

		Assert.assertTrue(!isMapType(objs.getClass()));
		Assert.assertTrue(!isMapType(clzs.getClass()));
		Assert.assertTrue(!isMapType(longs.getClass()));
		Assert.assertTrue(!isMapType(shorts.getClass()));
		Assert.assertTrue(!isMapType(objList.getClass()));
		Assert.assertTrue(!isMapType(clzList.getClass()));
		Assert.assertTrue(!isMapType(strs.getClass()));
		Assert.assertTrue(!isMapType(int1.getClass()));
		Assert.assertTrue(!isMapType(abd.getClass()));

		Assert.assertTrue(isMapType(strMap.getClass()));
	}

}

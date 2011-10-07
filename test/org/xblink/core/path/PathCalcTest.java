package org.xblink.core.path;

import junit.framework.Assert;

import org.junit.Test;

public class PathCalcTest {

	@Test
	public void calc1() throws Exception {
		String[] p1 = new String[] { "a", "b", "c", "d" };
		String[] p2 = new String[] { "a", "b", "c", "d", "e", "f", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../../..".equals(path));
	}

	@Test
	public void calc2() throws Exception {
		String[] p1 = new String[] { "a", "b", "c", "d" };
		String[] p2 = new String[] { "a", "g", "g", "b", "e", "f", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../../../../../../b/c/d".equals(path));
	}

	@Test
	public void calc3() throws Exception {
		String[] p1 = new String[] { "a", "g", "t", "b", "e", "f", "d" };
		String[] p2 = new String[] { "a", "r", "c", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../../../g/t/b/e/f/d".equals(path));
	}

	@Test
	public void calc4() throws Exception {
		String[] p1 = new String[] { "a", "r", "c", "d" };
		String[] p2 = new String[] { "a", "t", "b", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../../../r/c/d".equals(path));
	}

	@Test
	public void calc5() throws Exception {
		String[] p1 = new String[] { "a", "r", "c", "d" };
		String[] p2 = new String[] { "a", "r", "b", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../../c/d".equals(path));
	}

	@Test
	public void calc6() throws Exception {
		String[] p1 = new String[] { "a", "r", "c", "d" };
		String[] p2 = new String[] { "a", "r", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../c/d".equals(path));
	}

	@Test
	public void calc7() throws Exception {
		String[] p1 = new String[] { "a", "r", "c", "d" };
		String[] p2 = new String[] { "a", "r", "c", "d" };
		String path = PathCalc.calcRelativePath(p2, p1);
		Assert.assertTrue("../d".equals(path));
	}

	@Test
	public void calc8() throws Exception {
		String[] p1 = new String[] { "a", "b", "c", "d" };
		String[] p2 = new String[] { "..", "..", "e", "f" };
		String path = PathCalc.calcAbsolutePath(p1, p2);
		Assert.assertTrue("/a/b/e/f".equals(path));
	}

	@Test
	public void calc9() throws Exception {
		String[] p1 = new String[] { "a", "b", "c", "d" };
		String[] p2 = new String[] { "..", "..", ".." };
		String path = PathCalc.calcAbsolutePath(p1, p2);
		Assert.assertTrue("/a".equals(path));
	}

	@Test
	public void calc10() throws Exception {
		String[] p1 = new String[] { "a", "b", "c", "d" };
		String[] p2 = new String[] { "..", "..", "..", ".." };
		String path = PathCalc.calcAbsolutePath(p1, p2);
		Assert.assertTrue("/".equals(path));
	}
}

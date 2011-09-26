package org.xblink.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

	@Test
	public void isBlankStr() throws Exception {
		String str1 = null;
		Assert.assertTrue(StringUtil.isBlankStr(str1));

		String str2 = "";
		Assert.assertTrue(StringUtil.isBlankStr(str2));

		String str3 = "     ";
		Assert.assertTrue(StringUtil.isBlankStr(str3));
	}

	@Test
	public void lowerFirst() throws Exception {
		String s1 = "Xml";
		String s2 = "xMl";
		String s3 = "xml";
		String s4 = "XML";
		String result = "Xml";

		Assert.assertTrue(result.equals(StringUtil.upperFirstLowerOther(s1)));
		Assert.assertTrue(result.equals(StringUtil.upperFirstLowerOther(s2)));
		Assert.assertTrue(result.equals(StringUtil.upperFirstLowerOther(s3)));
		Assert.assertTrue(result.equals(StringUtil.upperFirstLowerOther(s4)));

	}

}

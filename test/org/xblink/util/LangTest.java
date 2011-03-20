package org.xblink.util;

import org.junit.Assert;
import org.junit.Test;

public class LangTest {

	@Test
	public void isBlankStr() throws Exception {
		String str1 = null;
		Assert.assertTrue(Lang.isBlankStr(str1));

		String str2 = "";
		Assert.assertTrue(Lang.isBlankStr(str2));

		String str3 = "     ";
		Assert.assertTrue(Lang.isBlankStr(str3));
	}

}

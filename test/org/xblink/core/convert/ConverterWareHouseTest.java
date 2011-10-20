package org.xblink.core.convert;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.xblink.core.convert.converters.singleValue.StringConverter;
import org.xblink.core.convert.converters.singleValue.UUIDConverter;

public class ConverterWareHouseTest {

	@Test
	public void find1() throws Exception {
		ConverterWarehouse.init();
		Converter sConverter = ConverterWarehouse.searchConverterForType(String.class, null);
		Assert.assertTrue(sConverter.getClass() == StringConverter.class);
	}

	@Test
	public void find2() throws Exception {
		Converter sConverter = ConverterWarehouse.searchConverterForType(UUID.class, null);
		Assert.assertTrue(sConverter.getClass() == UUIDConverter.class);
	}

	@Test
	public void findNotExist() throws Exception {
		boolean notExist = false;
		try {
			ConverterWarehouse.searchConverterForType(java.sql.Date.class, null);
		} catch (Exception e) {
			notExist = true;
		}
		Assert.assertTrue(notExist);
	}
}

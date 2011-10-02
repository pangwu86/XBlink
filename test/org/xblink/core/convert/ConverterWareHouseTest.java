package org.xblink.core.convert;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.xblink.core.convert.converters.StringConverter;
import org.xblink.core.convert.converters.UUIDConverter;

public class ConverterWareHouseTest {

	@Test
	public void init() throws Exception {
		Converter sConverter = ConverterWarehouse.searchConverterForType(String.class);
		Assert.assertTrue(sConverter.getClass() == StringConverter.class);
	}

	@Test
	public void find1() throws Exception {
		Converter sConverter = ConverterWarehouse.searchConverterForType(UUID.class);
		Assert.assertTrue(sConverter.getClass() == UUIDConverter.class);
	}

	@Test
	public void findNotExist() throws Exception {
		boolean notExist = false;
		try {
			Converter sConverter = ConverterWarehouse.searchConverterForType(java.sql.Date.class);
		} catch (Exception e) {
			notExist = true;
		}
		Assert.assertTrue(notExist);
	}
}

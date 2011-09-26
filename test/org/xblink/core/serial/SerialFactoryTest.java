package org.xblink.core.serial;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xblink.core.serial.Deserializer;
import org.xblink.core.serial.SerialFactory;

public class SerialFactoryTest {

	@Test
	public void createSerializer() throws Exception {
		boolean error = false;
		try {
			SerialFactory.createSerializer(null);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
	}

	@Test
	public void createDeserializer1() throws Exception {
		boolean error = false;
		try {
			SerialFactory.createDeserializer(null, null);
		} catch (Exception e) {
			error = true;
		}
		assertTrue(error);
	}

	@Test
	public void createDeserializer2() throws Exception {
		Deserializer deserializer = SerialFactory.createDeserializer("123", null);
		assertNotNull(deserializer);
	}

	@Test
	public void createDeserializer3() throws Exception {
		Deserializer deserializer = SerialFactory.createDeserializer(null, String.class);
		assertNotNull(deserializer);
	}
}

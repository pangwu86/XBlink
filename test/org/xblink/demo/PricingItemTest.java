package org.xblink.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xblink.XBlink;

public class PricingItemTest {
	
	@Test
	public void testCreateSetting() {
		PricingItem item = new PricingItem();
		item.setId("P1");
		item.setWeight(5);
		item.setDesc("P1 item");
		
		List<String> list = new ArrayList<String>();
		list.add("1001");
		list.add("1002");
		list.add("1003");
		list.add("1004");
		item.setValues(list);
		
		XBlink.toXml("C:/setting.xml", item);
	}
	
	@Test
	public void testLoadSetting() {
		PricingItem item = (PricingItem) XBlink.fromXml("C:/setting.xml", PricingItem.class);
		System.out.println(item);
	}
	
}

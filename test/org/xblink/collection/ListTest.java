package org.xblink.collection;

import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.xblink.XBlink;
import org.xblink.collection.list.ListContainer;
import org.xblink.collection.map.MapContainer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class ListTest {

    private String lc1 = "C:/List1.xml";
	
	static private ListContainer getListContainer(){
		return new ListContainer();
	}
	
	@Test
	public void testList1() throws Exception {
		ListContainer lc = getListContainer();
		
		List<String> list1 = new ArrayList<String>();
		list1.add("This is 1");
		list1.add("This is 2");
		
		lc.setList1(list1);
		
		XBlink.toXml(lc1, lc);
		
		ListContainer re = (ListContainer) XBlink.fromXml(lc1, ListContainer.class);

		assertTrue(re.getList1() != null);
		List<String> relist1 = re.getList1();
		assertTrue(relist1.get(0).equals("This is 1"));
		assertTrue(relist1.get(1).equals("This is 2"));
	}

}

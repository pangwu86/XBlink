package org.xblink.adapter;

import junit.framework.TestCase;

import org.junit.Test;
import org.xblink.domdrivers.impl.Dom4jDomDriver;
import org.xblink.domdrivers.impl.XppDomDriver;

public class AdapterFactoryTest extends TestCase {

	@Test
	public void testXMLAdapterFactory() throws Exception{
		System.out.println("系统默认查找结果");
		System.out.println(XMLAdapterFactory.getAdapter().getClass().getName());
		System.out.println("指定xpp查找结果");
		System.out.println(XMLAdapterFactory.getAdapter(new XppDomDriver()).getClass().getName());
		System.out.println("指定dom4j查找结果");
		System.out.println(XMLAdapterFactory.getAdapter(new Dom4jDomDriver()).getClass().getName());
	}
}

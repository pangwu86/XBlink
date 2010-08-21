package org.xblink.domdrivers.impl;

import org.xblink.adapter.XMLAdapter;
import org.xblink.adapter.impl.Dom4jXMLAdapter;
import org.xblink.domdrivers.DomDriver;

public class Dom4jDomDriver implements DomDriver {
	/** Dom4j参考类名*/
	public static final String DOM4J_CLASS_NAME = "org.dom4j.Document";

	@Override
	public boolean canWork() {
		try {
			Class.forName(DOM4J_CLASS_NAME,true,Thread.currentThread().getContextClassLoader());
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	@Override
	public XMLAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new Dom4jXMLAdapter();
	}
	
}

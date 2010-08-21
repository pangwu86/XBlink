package org.xblink.domdrivers.impl;

import org.xblink.adapter.XMLAdapter;
import org.xblink.adapter.impl.SystemXMLAdapter;
import org.xblink.domdrivers.DomDriver;

public class SystemDomDriver implements DomDriver {

	@Override
	public boolean canWork() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public XMLAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new SystemXMLAdapter();
	}

	
}

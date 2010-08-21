package org.xblink.domdrivers.impl;

import org.xblink.adapter.XMLAdapter;
import org.xblink.adapter.impl.XppXMLAdapter;
import org.xblink.domdrivers.DomDriver;

public class XppDomDriver implements DomDriver {
	/** XPP参考类名 */
	public static final String XPP_CLASS_NAME = "org.xmlpull.mxp1.MXParser";

	@Override
	public boolean canWork() {
		try {
			Class.forName(XPP_CLASS_NAME,true,Thread.currentThread().getContextClassLoader());
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	@Override
	public XMLAdapter getAdapter() {
		// TODO Auto-generated method stub
		return new XppXMLAdapter();
	}

}

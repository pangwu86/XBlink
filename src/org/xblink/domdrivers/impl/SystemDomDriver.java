package org.xblink.domdrivers.impl;

import org.xblink.adapter.XMLAdapter;
import org.xblink.adapter.impl.SystemXMLAdapter;
import org.xblink.domdrivers.DomDriver;

/**
 * JDK自带DOM驱动.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class SystemDomDriver implements DomDriver {

	public boolean canWork() {
		// TODO 根据JDK版本去判断,1.5之前的貌似没有
		return true;
	}

	public XMLAdapter getAdapter() {
		return new SystemXMLAdapter();
	}

}

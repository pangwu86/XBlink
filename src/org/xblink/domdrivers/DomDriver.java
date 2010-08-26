package org.xblink.domdrivers;

import org.xblink.adapter.XMLAdapter;

/**
 * DOM驱动.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public interface DomDriver {

	/**
	 * 是否可以工作.
	 * 
	 * @return 是否可以
	 */
	boolean canWork();

	/**
	 * 获得适配器.
	 * 
	 * @return 适配器
	 */
	XMLAdapter getAdapter();
}

package org.xblink.adapter;

import org.xblink.adapter.impl.Dom4jXMLAdapter;
import org.xblink.adapter.impl.SystemXMLAdapter;
import org.xblink.adapter.impl.XppXMLAdapter;
import org.xblink.domdrivers.DomDriver;
import org.xblink.domdrivers.impl.Dom4jDomDriver;
import org.xblink.domdrivers.impl.XppDomDriver;

/**
 * 
 * XML适配器工厂类.
 * 
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class XMLAdapterFactory {

	/**
	 * 获得当前可用的XML适配器 使用优先级为XPP3,DOM4J,系统自带的XML库.<br>
	 * 如果参考类存在,则认为该库可用.
	 * 
	 * @return XML适配器
	 */
	public static XMLAdapter getAdapter() {
		if (new XppDomDriver().canWork()) {
			return new XppXMLAdapter();
		}
		if (new Dom4jDomDriver().canWork()) {
			return new Dom4jXMLAdapter();
		}
		return new SystemXMLAdapter();
	}

	/**
	 * 根据指定驱动程序获得适配器<br>
	 * 如果指定驱动器找不到合适的适配器，则抛出异常
	 * @param domDrivers 指定的驱动程序
	 * @return
	 * @throws Exception
	 */
	public static XMLAdapter getAdapter(DomDriver domDriver) throws Exception {

		if (domDriver.canWork()) {
			return domDriver.getAdapter();
		}
		throw new Exception("指定的驱动加载失败，系统将采用默认方式获得适配器");
	}

	/**
	 * 根据指定驱动程序获得适配器<br>
	 * @param domDrivers 指定的驱动程序
	 * @param canThrow 在指定的驱动不可用情况下，是否抛出异常<br>
	 * 不抛出异常，则按照系统默认方式进行适配
	 * @return
	 * @throws Exception
	 */
	public static XMLAdapter getAdapter(DomDriver domDriver,Boolean canThrow) throws Exception{

		if (domDriver.canWork()) {
			return domDriver.getAdapter();
		}if(canThrow){
				throw new Exception("指定的驱动加载失败，系统将采用默认方式获得适配器");
		}else{
			return getAdapter();
		}
	}
}
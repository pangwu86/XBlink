package org.xblink.adapter;

import org.xblink.adapter.impl.Dom4jXMLAdapter;
import org.xblink.adapter.impl.SystemXMLAdapter;
import org.xblink.adapter.impl.XppXMLAdapter;
/**
 * XML适配器工厂类
 * @author E-Hunter(xjf1986518@gmail.com)
 *
 */
public class XMLAdapterFactory {

	/** Dom4j参考类名*/
	public static final String DOM4J_CLASS_NAME = "org.dom4j.Document";
	/** XPP参考类名*/
	public static final String XPP_CLASS_NAME = "org.xmlpull.mxp1.MXParser";
	/** XML适配器*/
	public static XMLAdapter xmlAdapter = null;
	//初始化，检查当前可用的适配器
	//使用优先级为XPP3,DOM4J,系统自带的XML库
	//如果参考类存在,则认为该库可用
	static {
		if(isXppCanWork()){
			xmlAdapter = new XppXMLAdapter();
		}
		else{
			if(isDom4jCanWork()){
				xmlAdapter = new Dom4jXMLAdapter();
			}
			else{
				xmlAdapter = new SystemXMLAdapter();
			}
		}
	}
	/**
	 * 获得当前可用的XML适配器
	 * @return
	 */
	public static XMLAdapter getInstance(){
		return xmlAdapter;
	}

	/**
	 * 检查Dom4j适配器是否可用
	 * @return
	 */
	private static boolean isDom4jCanWork(){
		try {
			Class.forName(DOM4J_CLASS_NAME,true,Thread.currentThread().getContextClassLoader());
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	/**
	 * 检查Xpp适配器是否可用
	 * @return
	 */
	private static boolean isXppCanWork() {
		try {
			Class.forName(XPP_CLASS_NAME,true,Thread.currentThread().getContextClassLoader());
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		XMLAdapterFactory.getInstance();
	}
}

package org.xblink.transfer;

import java.util.Map;

import org.xblink.adapter.XMLAdapter;

/**
 * 需要传递的信息.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class TransferInfo {

	/** 类加载器切换器 */
	private ClassLoaderSwitcher classLoaderSwitcher;

	/** 实现类 */
	private ImplClasses xmlImplClasses;

	/** 对象引用 */
	private Map<Integer, ReferenceObject> referenceObjects;

	/** XML解析适配器 */
	private XMLAdapter xmlAdapter;

	public XMLAdapter getXmlAdapter() {
		return xmlAdapter;
	}

	public void setXmlAdapter(XMLAdapter xmlAdapter) {
		this.xmlAdapter = xmlAdapter;
	}

	public ClassLoaderSwitcher getClassLoaderSwitcher() {
		return classLoaderSwitcher;
	}

	public void setClassLoaderSwitcher(ClassLoaderSwitcher classLoaderSwitcher) {
		this.classLoaderSwitcher = classLoaderSwitcher;
	}

	public ImplClasses getXmlImplClasses() {
		return xmlImplClasses;
	}

	public void setXmlImplClasses(ImplClasses xmlImplClasses) {
		this.xmlImplClasses = xmlImplClasses;
	}

	public Map<Integer, ReferenceObject> getReferenceObjects() {
		return referenceObjects;
	}

	public void setReferenceObjects(Map<Integer, ReferenceObject> referenceObjects) {
		this.referenceObjects = referenceObjects;
	}

}

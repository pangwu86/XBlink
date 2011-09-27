package org.xblink.core.serial;

import org.xblink.XBConfig;
import org.xblink.core.doc.DocWriter;

/**
 * 分析对象，通过DocWriter，输出对应的文本格式信息。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class Serializer {

	private Object obj = null;

	/**
	 * 构造器，放入需要分析的对象。
	 * 
	 * @param obj
	 *            分析对象
	 */
	public Serializer(Object obj) {
		this.obj = obj;
	}

	public abstract void doIt(XBConfig xbConfig, DocWriter docWriter);

}

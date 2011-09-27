package org.xblink.core.serial.impl;

import org.xblink.XBConfig;
import org.xblink.core.doc.DocWriter;
import org.xblink.core.serial.Serializer;

/**
 * 默认实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class DefaultSerializer extends Serializer {

	public DefaultSerializer(Object obj) {
		super(obj);
	}

	@Override
	public void doIt(XBConfig xbConfig, DocWriter docWriter) {
		// 解析当前对象

		// 记录当前对象的引用信息

	}

}

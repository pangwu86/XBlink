package org.xblink.writer;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * XBlink支持的集合类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public enum XMLCollection {

	List(List.class), Map(Map.class), Set(Set.class), NotCollection(Object.class);

	private Class<?> clz;

	private XMLCollection(Class<?> clz) {
		this.clz = clz;
	}

	public Class<?> getClz() {
		return clz;
	}
}
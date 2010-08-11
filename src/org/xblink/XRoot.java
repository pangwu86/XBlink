package org.xblink;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsArray;
import org.xblink.annotations.XBlinkAsList;
import org.xblink.annotations.XBlinkAsMap;
import org.xblink.annotations.XBlinkAsSet;

/**
 * 根对象.<br>
 * 为了序列化反序列化集合对象使用.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
@XBlinkAlias("root")
public class XRoot {

	@XBlinkAsList
	@XBlinkAlias("root-list")
	private List<?> list;

	@XBlinkAsArray
	@XBlinkAlias("root-array")
	private Object[] array;

	@XBlinkAsSet
	@XBlinkAlias("root-set")
	private Set<?> set;

	@XBlinkAsMap
	@XBlinkAlias("root-map")
	private Map<?, ?> map;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public Set<?> getSet() {
		return set;
	}

	public void setSet(Set<?> set) {
		this.set = set;
	}

	public Map<?, ?> getMap() {
		return map;
	}

	public void setMap(Map<?, ?> map) {
		this.map = map;
	}
}
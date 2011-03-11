package org.xblink.collection.map;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.annotations.XBlinkAsMap;

public class MapContainer {

	@XBlinkAsMap
	private Map<Integer, String> map1;
	
	@XBlinkAsMap
	private Map<KeyObject, String> map2;

	@XBlinkAsMap
	private Map<Integer, ValueObject> map3;

	@XBlinkAsMap
	private Map<KeyObject, ValueObject> map4;
	
	@XBlinkAsMap
	private Map<List<KeyObject>,Set<ValueObject>> map5;
	
	@XBlinkAsMap
	private Map<Map<KeyObject, String>,Map<Integer, ValueObject>> map6;
	
	@XBlinkAsMap
	private Map<?,?> map7;

	@XBlinkAsMap
	private Map<String ,List<ValueObject>> map8;
	
	

	public Map<String, List<ValueObject>> getMap8() {
		return map8;
	}

	public void setMap8(Map<String, List<ValueObject>> map8) {
		this.map8 = map8;
	}

	public Map<Integer, String> getMap1() {
		return map1;
	}

	public void setMap1(Map<Integer, String> map1) {
		this.map1 = map1;
	}

	public Map<KeyObject, String> getMap2() {
		return map2;
	}

	public void setMap2(Map<KeyObject, String> map2) {
		this.map2 = map2;
	}

	public Map<Integer, ValueObject> getMap3() {
		return map3;
	}

	public void setMap3(Map<Integer, ValueObject> map3) {
		this.map3 = map3;
	}

	public Map<KeyObject, ValueObject> getMap4() {
		return map4;
	}

	public void setMap4(Map<KeyObject, ValueObject> map4) {
		this.map4 = map4;
	}

	public Map<List<KeyObject>, Set<ValueObject>> getMap5() {
		return map5;
	}

	public void setMap5(Map<List<KeyObject>, Set<ValueObject>> map5) {
		this.map5 = map5;
	}

	public Map<Map<KeyObject, String>, Map<Integer, ValueObject>> getMap6() {
		return map6;
	}

	public void setMap6(Map<Map<KeyObject, String>, Map<Integer, ValueObject>> map6) {
		this.map6 = map6;
	}

	public Map<?, ?> getMap7() {
		return map7;
	}

	public void setMap7(Map<?, ?> map7) {
		this.map7 = map7;
	}
	
}

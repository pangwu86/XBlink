package org.xblink.demo;

import java.util.List;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsList;

/**
 * 定价配置项
 * 
 * @author geor.yuli
 * 
 */
@XBlinkAlias("item")
public class PricingItem {

	@XBlinkAsAttribute
	private String id;

	@XBlinkAsAttribute
	private String desc;

	@XBlinkAsAttribute
	private int weight;

	@XBlinkAsAttribute
	private String type;

	@XBlinkAsList
	private List<String> values;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "id:" + id + ", desc:" + desc + ", weight:" + weight + ", type:" + type
				+ ", values:" + values;
	}

}

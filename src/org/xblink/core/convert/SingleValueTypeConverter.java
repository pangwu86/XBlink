package org.xblink.core.convert;


/**
 * 单值类型的共同基类。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class SingleValueTypeConverter implements Converter {

	public boolean isSingleValueType() {
		return true;
	}

}

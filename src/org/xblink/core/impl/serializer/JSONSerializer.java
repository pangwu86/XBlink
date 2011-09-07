package org.xblink.core.impl.serializer;

import org.xblink.core.Serializer;

/**
 * JSON格式构建器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class JSONSerializer extends Serializer {

	public JSONSerializer(Object obj) {
		super(obj);
	}

	public String getPrimitiveTypeStr() {
		// TODO
		return "";
	}

	public String getObjectTypeStr() {
		// TODO
		return "";
	}

	public String getCollectionTypeStr() {
		// TODO
		return "";
	}

	public String getMapTypeStr() {
		// TODO
		return "";
	}

	public String getArrayTypeStr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEnumTypeStr() {
		// TODO Auto-generated method stub
		return null;
	}

}

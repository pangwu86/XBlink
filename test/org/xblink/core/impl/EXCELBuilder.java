package org.xblink.core.impl;

import org.xblink.core.Serializer;

/**
 * 测试用
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class EXCELBuilder extends Serializer {

	public EXCELBuilder(Object obj) {
		super(obj);
	}

	public String getPrimitiveTypeStr() {
		// TODO
		return "excel-primititeType\n";
	}

	public String getObjectTypeStr() {
		// TODO
		return "excel-objectType\n";
	}

	public String getCollectionTypeStr() {
		// TODO
		return "excel-collectionType\n";
	}

	public String getMapTypeStr() {
		// TODO
		return "excel-mapType\n";
	}

	@Override
	public String getArrayTypeStr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnumTypeStr() {
		// TODO Auto-generated method stub
		return null;
	}

}

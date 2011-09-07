package org.xblink.core.impl.serializer;

import org.xblink.core.Serializer;

/**
 * XML格式构建器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public class XMLSerializer extends Serializer {

	public XMLSerializer(Object obj) {
		super(obj);
	}

	public String getPrimitiveTypeStr() {
		// TODO
		return "xml-primititeType\n";
	}

	public String getObjectTypeStr() {
		// TODO
		return "xml-objectType\n";
	}

	public String getCollectionTypeStr() {
		// TODO
		return "xml-collectionType\n";
	}

	public String getMapTypeStr() {
		// TODO
		return "xml-mapType\n";
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

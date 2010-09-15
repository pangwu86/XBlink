package org.xblink;

/**
 * 8种基本类型.
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public enum EightBasicTypes {

	Int(int.class), 
	Double(double.class), 
	Float(float.class), 
	Long(long.class), 
	Boolean(boolean.class), 
	Byte(byte.class), 
	Short(short.class), 
	Char(char.class), 
	Null(null);

	private Class<?> type;

	private EightBasicTypes(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return this.type;
	}
}

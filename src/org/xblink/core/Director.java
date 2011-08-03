package org.xblink.core;

import org.xblink.XBConfig;

/**
 * 引导者。<BR>
 * 
 * 指引Builder一步步生成文件。<BR>
 * 
 * 这里采用了设计模式中的Build模式。<BR>
 * 
 * 这里对生成各种格式文件的实际操作委托到了Builder中。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class Director {

	/**
	 * 序列化。
	 * 
	 * @return 文件内容
	 */
	public static String serialize(Builder builder, XBConfig xbConfig) {
		StringBuilder sb = new StringBuilder();
		// 分析
		builder.analysis();
		// 构建
		sb.append(builder.getPrimitiveTypeStr());
		sb.append(builder.getObjectTypeStr());
		sb.append(builder.getArrayTypeStr());
		sb.append(builder.getCollectionTypeStr());
		sb.append(builder.getMapTypeStr());
		sb.append(builder.getEnumTypeStr());
		// 返回结果
		return sb.toString();
	}

	/**
	 * 反序列化。
	 * 
	 * @return 文件内容
	 */
	public static Object deserialize(Builder builder) {
		// TODO 过程
		return null;
	}

}

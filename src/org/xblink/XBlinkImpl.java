package org.xblink;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import org.xblink.core.Builder;
import org.xblink.core.Director;
import org.xblink.core.impl.JSONBuilder;
import org.xblink.core.impl.XMLBuilder;
import org.xblink.core.impl.YAMLBuilder;
import org.xblink.util.Lang;

/**
 * XBlink功能实现类。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public class XBlinkImpl {

	private XBlinkImpl() {
	}

	/**
	 * builder的类名模板
	 */
	private final static String BUILDER_TEMPLATE = "org.xblink.core.impl.%SBuilder";

	// 序列化通用方法

	protected static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream, String docTypeName) {
		return serialize(findBuilderByDocTypeName(obj, docTypeName), xbConfig, outputStream);
	}

	// 序列化默认方法

	protected static String toJSON(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new JSONBuilder(obj), xbConfig, outputStream);
	}

	protected static String toXML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new XMLBuilder(obj), xbConfig, outputStream);
	}

	protected static String toYAML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new YAMLBuilder(obj), xbConfig, outputStream);
	}

	// 私有方法

	@SuppressWarnings("unchecked")
	private static Builder findBuilderByDocTypeName(Object obj, String docTypeName) {
		// 格式名称
		if (Lang.isBlankStr(docTypeName)) {
			throw new RuntimeException("没有输入需要转换的格式名称,无法执行后续操作。");
		}
		// 找到对应的Builder
		String builderClzName = String.format(BUILDER_TEMPLATE, docTypeName.toUpperCase());
		Builder builder;
		try {
			Class<? extends Builder> builderClz;
			Constructor<? extends Builder> builderCons;
			builderClz = (Class<? extends Builder>) Class.forName(builderClzName);
			// 实例化Builder
			builderCons = builderClz.getDeclaredConstructor(Object.class);
			builder = builderCons.newInstance(obj);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类,无法执行后续操作。", docTypeName), e);
		} catch (Exception e) {
			throw new RuntimeException(String.format("%s实例化失败。", builderClzName), e);
		}
		return builder;
	}

	private static String serialize(Builder builder, XBConfig xbConfig, OutputStream outputStream) {
		// 生成字符串
		String str = Director.serialize(builder, null == xbConfig ? XBConfig.getDefaultXBConfig() : xbConfig);
		// 写入文件
		if (null != outputStream) {
			try {
				outputStream.write(str.getBytes());
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				throw new RuntimeException("无法生成文件。", e);
			}
		}
		// 返回生成结果
		return str;
	}

}

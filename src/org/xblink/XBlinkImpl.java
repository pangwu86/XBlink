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
public abstract class XBlinkImpl {

	/**
	 * builder的类名模板
	 */
	private final static String BUILDER_TEMPLATE = "org.xblink.core.impl.%SBuilder";

	// 序列化通用方法

	static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream, String docTypeName) {
		return serialize(findBuilder(obj, docTypeName), xbConfig, outputStream);
	}

	// 序列化默认方法

	static String toJSON(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new JSONBuilder(obj), xbConfig, outputStream);
	}

	static String toXML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new XMLBuilder(obj), xbConfig, outputStream);
	}

	static String toYAML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new YAMLBuilder(obj), xbConfig, outputStream);
	}

	// 内部方法

	/**
	 * 根据类型名称查找对应的Builder实现类。
	 * 
	 * @param obj
	 *            待处理对象
	 * @param docTypeName
	 *            文档类型名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Builder findBuilder(Object obj, String docTypeName) {
		// 格式名称
		if (Lang.isBlankStr(docTypeName)) {
			throw new RuntimeException("没有输入需要转换的格式名称,无法执行后续操作。");
		}
		// 找到对应的Builder
		String builderClzName = String.format(BUILDER_TEMPLATE, docTypeName.toUpperCase());
		Class<? extends Builder> builderClz;
		Constructor<? extends Builder> builderCons;
		Builder builder;
		try {
			builderClz = (Class<? extends Builder>) Class.forName(builderClzName);
			// 实例化Builder
			builderCons = builderClz.getDeclaredConstructor(Object.class);
			builder = builderCons.newInstance(obj);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类,无法执行后续操作。", docTypeName), e);
		} catch (Exception e) {
			throw new RuntimeException("Builder实例化失败。", e);
		}
		return builder;
	}

	/**
	 * 序列化。
	 * 
	 * @param builder
	 *            构造器
	 * @param xbConfig
	 *            XBlink配置信息
	 * @param outputStream
	 *            输入流
	 * @return 序列化字符串
	 */
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

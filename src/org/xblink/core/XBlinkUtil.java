package org.xblink.core;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import org.xblink.XBConfig;
import org.xblink.core.impl.JSONBuilder;
import org.xblink.core.impl.XMLBuilder;
import org.xblink.core.impl.YAMLBuilder;
import org.xblink.util.Lang;

/**
 * XBlink辅助类。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class XBlinkUtil {

	private final static String BUILDER_TEMPLATE = "org.xblink.core.impl.%SBuilder";

	// 序列化通用方法

	@SuppressWarnings("unchecked")
	public static String toAny(Object obj, XBConfig xbConfig, OutputStream outputStream,
			String wanted) {
		// 格式名称
		if (Lang.isBlankStr(wanted)) {
			throw new RuntimeException("没有输入需要转换的格式名称,无法执行后续操作。");
		}
		// 找到对应的Builder
		String builderClzName = String.format(BUILDER_TEMPLATE, wanted.toUpperCase());
		Class<? extends Builder> builderClz;
		Constructor<? extends Builder> builderCons;
		Builder builder;
		try {
			builderClz = (Class<? extends Builder>) Class.forName(builderClzName);
			// 实例化Builder
			builderCons = builderClz.getDeclaredConstructor(Object.class);
			builder = builderCons.newInstance(obj);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("没有找到%s格式的实现类,无法执行后续操作。", wanted), e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return serialize(builder, xbConfig, outputStream);
	}

	// 序列化默认方法

	public static String toJSON(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new JSONBuilder(obj), xbConfig, outputStream);
	}

	public static String toXML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new XMLBuilder(obj), xbConfig, outputStream);
	}

	public static String toYAML(Object obj, XBConfig xbConfig, OutputStream outputStream) {
		return serialize(new YAMLBuilder(obj), xbConfig, outputStream);
	}

	// 内部方法

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
		String str = Director.serialize(builder, null == xbConfig ? XBConfig.getDefaultXBConfig()
				: xbConfig);
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

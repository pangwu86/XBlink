package org.xblink.core.convert.converters.singleValue;

import java.io.File;

import org.xblink.core.convert.SingleValueTypeConverter;

/**
 * 文件类型转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class FileConverter extends SingleValueTypeConverter {

	public Class<?>[] getTypes() {
		return new Class<?>[] { File.class };
	}

	public boolean canConvert(Class<?> type) {
		return File.class == type;
	}

	public String obj2Text(Object obj) throws Exception {
		return ((File) obj).getPath();
	}

	public Object text2Obj(String text) throws Exception {
		return new File(text);
	}

}

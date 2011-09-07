package org.xblink.util;

/**
 * 一些常用方法。<BR>
 * 
 * 这里参照了Nutz的一部分实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 * 
 */
public abstract class Lang {

	/**
	 * 判断是否是空字符串
	 * 
	 * @param cs
	 *            字符串
	 * @return 是不是为空白字符串
	 */
	public static boolean isBlankStr(CharSequence cs) {
		if (null == cs)
			return true;
		int length = cs.length();
		for (int i = 0; i < length; i++) {
			if (!(Character.isWhitespace(cs.charAt(i))))
				return false;
		}
		return true;
	}
}

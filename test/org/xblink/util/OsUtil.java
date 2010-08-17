package org.xblink.util;

/**
 * 判断当前系统类型.
 * 
 * 
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class OsUtil {

	private static String osname = System.getProperty("os.name");

	private static boolean isWindows = false;

	private static boolean isLinux = false;

	private static boolean isMacintosh = false;

	static {
		if (osname.startsWith("Windows")) {
			isWindows = true;
		} else if (osname.startsWith("Linux")) {
			isLinux = true;
		} else if (osname.startsWith("Macintosh")) {
			isMacintosh = true;
		}
	}

	/**
	 * 当前系统是Linux系统吗？
	 * 
	 * @return 是否
	 */
	public static boolean isLinux() {
		return isLinux;
	}

	/**
	 * 当前系统是Windows系统吗？
	 * 
	 * @return 是否
	 */
	public static boolean isWindows() {
		return isWindows;
	}

	/**
	 * 当前系统是Macintosh系统吗？
	 * 
	 * @return 是否
	 */
	public static boolean isMacintosh() {
		return isMacintosh;
	}
}

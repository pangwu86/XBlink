package org.xblink.util;

public class OsUtil {

	static String osname = System.getProperty("os.name");

	public static boolean isWindows() {
		if (osname.startsWith("Windows")) {
			return true;
		}
		return false;
	}

	public static boolean isLinux() {
		if (osname.startsWith("Linux")) {
			return true;
		}
		return false;
	}
}

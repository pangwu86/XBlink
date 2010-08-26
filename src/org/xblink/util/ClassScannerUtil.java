package org.xblink.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 包扫描工具.
 * 
 * 
 * @author E-Hunter(xjf1986518@gmail.com)
 * @author pangwu86(pangwu86@gmail.com)
 * 
 */
public class ClassScannerUtil {

	/**
	 * 扫描并得到指定包下的所有类（不进行递归）
	 * 
	 * @param clazzs
	 *            参考类
	 * @return 类列表
	 * @throws Exception
	 */
	public static List<Class<?>> scan(Class<?>... clazzs) throws Exception {
		List<Class<?>> fileList = new ArrayList<Class<?>>();
		StringBuffer path = new StringBuffer();
		for (Class<?> clazz : clazzs) {
			path.delete(0, path.length());
			String packageName = clazz.getPackage().getName();// 获得包名
			path.append(clazz.getResource("").getPath());// 获得参考类所在的根目录
			path.deleteCharAt(0);
			addToClassList(path.toString(), fileList, packageName);
		}
		return fileList;
	}

	/**
	 * 加载指定包下面的所有类
	 * 
	 * @param path
	 *            指定位置
	 * @param filelist
	 *            用于存储类的列表
	 * @param packageName
	 *            包名
	 * @throws Exception
	 */
	private static void addToClassList(String path, List<Class<?>> filelist, String packageName)
			throws Exception {
		// 此设计最初为支持递归，如果需要递归则不需进行太多改动
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".class") && !files[i].isDirectory()) {
				// 把路径转换成类名
				if (files[i].getName().endsWith(".class")) {
					String strFileName = files[i].getAbsolutePath()
							.replace(path.replace('/', '\\'), "").replace(".class", "");
					strFileName = packageName + "." + strFileName;
					Class<?> cls;
					try {
						cls = Class.forName(strFileName);
						filelist.add(cls);
					} catch (ClassNotFoundException e) {
						throw new Exception("没有找到此类：" + strFileName);
					}
				}
			}
		}
	}
}
